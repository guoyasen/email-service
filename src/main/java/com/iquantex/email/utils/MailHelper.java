package com.iquantex.email.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.iquantex.email.common.content.FileTypeConst;
import com.iquantex.email.dao.mapper.CsMailFileMapper;
import com.iquantex.email.dao.model.CsMailFile;
import com.iquantex.email.service.impl.MyJavaMailSenderImpl;
import com.iquantex.event.email.EmailEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
@EnableAsync  //开始异步支持
public class MailHelper {

    //注入邮件工具类
    @Autowired
    private MyJavaMailSenderImpl mailSender;

    @Autowired
    private CsMailFileMapper csMailFileMapper;

    public final static String FILE_NAME = "指令信息导出";

    @Async("asyncServiceExecutor")
    public ListenableFuture<ListenableFuture> sendMail(EmailEvent emailEvent) throws Exception {

        String serverFrom = (String) mailSender.getJavaMailProperties().get("from");

        MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true, "UTF-8");//true表示支持复杂类型
        setMessagePro(emailEvent, serverFrom, messageHelper);
        //正式发送邮件
        mailSender.send(messageHelper.getMimeMessage());

        return new AsyncResult<>(new AsyncResult<>(true));
    }

    private void setMessagePro(EmailEvent emailEvent, String serverFrom, MimeMessageHelper messageHelper) throws MessagingException, UnsupportedEncodingException, IOException {
        String fromMail = emailEvent.getSender();
        if (StringUtils.isBlank(fromMail)) {
            fromMail = serverFrom;
        }
        messageHelper.setFrom(new InternetAddress(serverFrom, emailEvent.getFromName(), "UTF-8"));
        messageHelper.setReplyTo(fromMail, emailEvent.getFromName());// 回信地址
        messageHelper.setTo(emailEvent.getReceivers().split(","));//邮件收信人
        messageHelper.setSubject(emailEvent.getSubject());//邮件主题
        messageHelper.setText(emailEvent.getContent(), true);//邮件内容
        if (emailEvent.getFiles() != null && !emailEvent.getFiles().isEmpty()) {
            for (int i = 0; i < emailEvent.getFiles().size(); i++) {
                byte[] file = emailEvent.getFiles().get(i);
                String fileName = emailEvent.getFileNames().get(i);
                InputStream input = new ByteArrayInputStream(file);
                File fileTemp = new File(fileName);
                inputStream2File(input, fileTemp);
                CsMailFile csMailFile = new CsMailFile();
                csMailFile.setCreateTime(new Date());
                csMailFile.setMailId(emailEvent.getMailId());
                csMailFile.setFileId(UUID.randomUUID().toString());
                csMailFile.setFileContent(file);
                csMailFile.setFileName(fileName);
                csMailFileMapper.insert(csMailFile);
                messageHelper.addAttachment(fileName, fileTemp);
            }
        }
        if (!StringUtils.isEmpty(emailEvent.getCcopys())) {//抄送
            messageHelper.setCc(emailEvent.getCcopys().split(","));
        }
        /*if (!StringUtils.isEmpty(emailEvent.getBccMail())) {//密送
            messageHelper.setCc(mailDTO.getBccMail().split(","));
        }*/
    }


    /**
     * 将inputStream转化为file
     *
     * @param is
     * @param file 要输出的文件目录
     */
    public void inputStream2File(InputStream is, File file) throws IOException {
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            int len = 0;
            byte[] buffer = new byte[8192];

            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
        } finally {
            os.close();
            is.close();
        }


    }
}