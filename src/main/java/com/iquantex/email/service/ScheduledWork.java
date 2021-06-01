package com.iquantex.email.service;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iquantex.email.dao.mapper.CsMailFileMapper;
import com.iquantex.email.dao.mapper.CsMailInfoMapper;
import com.iquantex.email.dao.model.CsMailFile;
import com.iquantex.email.dao.model.CsMailInfo;
import com.iquantex.email.utils.BeanUtil;
import com.iquantex.event.email.EmailEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ScheduledWork {

    @Autowired
    private YbKafkaProducer ybKafkaProducer;
    @Autowired
    private CsMailInfoMapper csMailInfoMapper;
    @Autowired
    private CsMailFileMapper csMailFileMapper;


    @Scheduled(cron = "0/10 * * * * *")
    public void scheduled() throws ParseException {

        int num = new Random().nextInt(2) + 1;
        String serverFlag = "114";
        if (num == 1) {
            serverFlag = "365";
        }

        EmailEvent emailEvent = new EmailEvent();
        emailEvent.setMailId(UUID.randomUUID().toString());
        // 收件人别名
        emailEvent.setFromName("crct_securities@crctrust.com");
        // 发件人邮箱
        emailEvent.setSender("crct_securities@crctrust.com");
        // 收件人邮箱
        emailEvent.setReceivers("yasen.guo@iquantex.cn");
//            mailDTO.setPlanSendTime(parseDateStr("2019-07-26 16:50:52", "yyyy-MM-dd HH:mm:ss"));
        // 主题
        emailEvent.setSubject("测试邮件2021年2月25日");

        Map<String, String> map = new HashMap<>();
        map.put("sname", "邮件模板测试内容，测试时间2021年2月25日");
        map.put("secClass1Value", "邮件模板测试主题，测试时间2021年2月25日");
        map.put("subject", "测试邮件2021年2月25日");

        // 测试正文
        emailEvent.setContent(JSON.toJSONString(map));
        emailEvent.setEmailCode("NS_ASSET_UPDATE");
        emailEvent.setCcopys("yasen.guo@iquantex.cn,1121034610@qq.com");

        /*File file = new File("test.txt");

        List<File> files = new ArrayList<>();
        BufferedWriter out =null;
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "GBK"));
            out.write("123");

        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if (out != null) {
                                try {
                                        out.flush();
                                         out.close();
                                    } catch (IOException e) {
                                         e.printStackTrace();
                                    }
                            }
        }

        files.add(file);
        emailEvent.setFiles(files);*/

        ybKafkaProducer.sendToKafkaStandardMessageAsync(emailEvent);

    }

    public static Date parseDateStr(String dateStr, String simpleDataType) throws ParseException {
        DateFormat format = new SimpleDateFormat(simpleDataType);
        return format.parse(dateStr);
    }

    /**
     * 定时邮件处理
     * 5分钟扫描一次
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void planSendMail() {
        synchronized (this) {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar nowTimeEnd = Calendar.getInstance();
            Date endTime = nowTimeEnd.getTime();

            QueryWrapper<CsMailInfo> wrapper = new QueryWrapper<>();
            wrapper.eq(CsMailInfo.STATUS, 0);
            wrapper.le(CsMailInfo.PLAN_TIME, endTime);
            // 查找已到计划发送时间的邮件：状态为0=未处理，发送时间小于当前系统时间
           List<CsMailInfo> list = csMailInfoMapper.selectList(wrapper);

            for (CsMailInfo dto : list) {
                EmailEvent emailEvent = BeanUtil.copyProperties(dto, EmailEvent.class);
                emailEvent.setContent(new String(dto.getContent()));
                if(dto.getFileCount() != 0){
                    QueryWrapper<CsMailFile> fileQueryWrapper = new QueryWrapper<>();
                    fileQueryWrapper.eq(CsMailFile.MAIL_ID, dto.getMailId());
                    List<CsMailFile> csMailFiles = csMailFileMapper.selectList(fileQueryWrapper);
                    List<String> fileNames = new ArrayList<>();
                    List<byte[]> fileContent = new ArrayList<>();
                    for(CsMailFile csMailFile : csMailFiles){
                        fileNames.add(csMailFile.getFileName());
                        fileContent.add(csMailFile.getFileContent());
                    }
                    emailEvent.setFileNames(fileNames);
                    emailEvent.setFiles(fileContent);
                }
                // 生产消息
                ybKafkaProducer.sendToKafkaStandardMessageAsync(emailEvent);

                // 邮件发送中
                CsMailInfo csMailInfo = BeanUtil.copyProperties(dto, CsMailInfo.class);
                csMailInfo.setStatus(1);
                csMailInfoMapper.updateById(csMailInfo);
            }

        }
    }

    /*public byte[] createFile(){
        try{
            String data = " This content will append to the end of the file";

            File file =new File("javaio-appendfile.txt");

            //if file doesnt exists, then create it
            if(!file.exists()){
                file.createNewFile();
            }

            //true = append file
            FileWriter fileWritter = new FileWriter(file.getName(),true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(data);
            bufferWritter.close();

            System.out.println("Done");

        }catch(IOException e){
            e.printStackTrace();
        }
    }*/

}
