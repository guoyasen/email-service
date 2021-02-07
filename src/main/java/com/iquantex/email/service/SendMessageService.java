package com.iquantex.email.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iquantex.email.dao.mapper.CsMailInfoMapper;
import com.iquantex.email.dao.model.CsMailFile;
import com.iquantex.email.dao.model.CsMailInfo;
import com.iquantex.email.enums.SendStatusEnum;
import com.iquantex.email.utils.BeanUtil;
import com.iquantex.email.utils.MailHelper;
import com.iquantex.event.email.EmailEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.bouncycastle.asn1.iana.IANAObjectIdentifiers.mail;

@Service("sendMailService")
@Slf4j
@EnableAsync  //开始异步支持
public class SendMessageService {

    @Autowired
    private MailHelper mailHelper;
    @Autowired
    private YbKafkaProducer ybKafkaProducer;
    @Autowired
    private CsMailInfoMapper csMailInfoMapper;

    @Value("${com.example.mail.sendNumber}")
    private Integer sendNumber;

    @Value("${com.example.mail.threadKillTime}")
    private Integer threadKillTime;


    @Async("asyncServiceExecutor")
    public void sendMessages(EmailEvent emailEvent) {
        // 检查消息必要参数
        if (checkMail(emailEvent)) {
            return;
        }

        CsMailInfo mailInfo = BeanUtil.copyProperties(emailEvent, CsMailInfo.class);
        mailInfo.setContent(emailEvent.getContent().getBytes());

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq(CsMailInfo.MAIL_ID, emailEvent.getMailId());
        // 数据库同 mail_uid 的邮件数量
        int count = csMailInfoMapper.selectCount(wrapper);

        Date now = new Date();
        Date afterDate = new Date(now.getTime() + 60000);
        mailInfo.setStatus(SendStatusEnum.SEND_STATUS_PLAN.getStatus());// 默认未发送
        if(emailEvent.getFiles() != null && !emailEvent.getFiles().isEmpty()){
            mailInfo.setFileCount(emailEvent.getFiles().size());
        }else {
            mailInfo.setFileCount(0);
        }

        // 计划时间在当前时间 1 分钟之后的，首次只入库，不发邮件（等定时计划任务发送）
//        if (mail.getPlanSendTime() != null && mail.getPlanSendTime().after(afterDate)) {
//            if (count == 0) {
//                mail.setStatus(SendStatusEnum.SEND_STATUS_PLAN.getStatus());// 默认未发送
//                mailMapper.insert(mail);
//                mailInfoMapper.insert(mailInfo);
//            }
//        }
//        // 即时邮件发送（即时邮件 或 计划发送时间 比 当前时间+1分钟 小的）
//        else {
            ListenableFuture<ListenableFuture> listenableFuture = new AsyncResult<>(new AsyncResult<>(true));
            try {
                // 首次发送，存入数据库
                if (count == 0) {
                    csMailInfoMapper.insert(mailInfo);
                }

                // 发送邮件（阻塞）
                listenableFuture = mailHelper.sendMail(emailEvent);
                mailAddCallback(emailEvent, mailInfo, listenableFuture);

            } catch (Exception e) {
                if (listenableFuture != null) {
                    listenableFuture.cancel(true);
                    // 将当前消息重新生产到队列
                    log.info("发送出错,异常信息{}{}", e.getStackTrace(), e.getSuppressed());
                    tryAgain(emailEvent);
                } else {
                    log.info("listenableFuture未赋值，空指针异常");
                }
            }
//        }
    }

    private void mailAddCallback(EmailEvent emailEvent, CsMailInfo csMailInfo, ListenableFuture<ListenableFuture> listenableFuture) throws InterruptedException, java.util.concurrent.ExecutionException, java.util.concurrent.TimeoutException {

        listenableFuture.addCallback(listenableFuture1 -> {
            csMailInfo.setStatus(SendStatusEnum.SEND_STATUS_SUCCESS.getStatus());// 发送成功
            csMailInfo.setCreateTime(new Date());
            // 更新数据库消息发送状态
            QueryWrapper<CsMailInfo> csMailInfoQueryWrapper = new QueryWrapper<>();
            csMailInfoQueryWrapper.eq(CsMailInfo.MAIL_ID, csMailInfo.getMailId());
            csMailInfo.setSendNumber(emailEvent.getSendNum() + 1);
            csMailInfoMapper.updateById(csMailInfo);
//            mailMapper.updateByExampleSelective(mail, successExample);
            log.info("发送邮件成功{}：{}->{}", emailEvent.getMailId(), emailEvent.getSender(), emailEvent.getReceivers());
        }, throwable -> {
            // 发送失败:状态置为2，设置mail的重试次数，mailDTO的sendnum是0-3，所以0/1的情况，mail的发送次数都为1；其他情况为dto的sendnum值

            QueryWrapper<CsMailInfo> wrapper = new QueryWrapper<>();
            wrapper.eq(CsMailInfo.MAIL_ID, csMailInfo.getMailId());
            if (emailEvent.getSendNum() == 0) {
                emailEvent.setSendNum(1);
            } else {
                csMailInfo.setSendNumber(emailEvent.getSendNum());
            }
            csMailInfo.setStatus(SendStatusEnum.SEND_STATUS_FAIL.getStatus());
            // 更新数据库消息发送状态
            csMailInfoMapper.updateById(csMailInfo);
        });

        listenableFuture.get(threadKillTime, TimeUnit.SECONDS);
    }

    /**
     * 发送异常，将重新写入队列
     *
     * @param emailEvent
     */
    private void tryAgain(EmailEvent emailEvent) {
        int num = emailEvent.getSendNum();
        // 如果，当前发送次数 < 邮件失败重试次数
        if (num < sendNumber) {
            emailEvent.setSendNum(num + 1);
            // 将当前消息重新生产到队列
            ybKafkaProducer.sendToKafkaStandardMessageAsync(emailEvent);
            log.info("{}出现异常，第 {} 次重新发送", emailEvent.getEmailCode(), num);
        }
    }

    /**
     * 检查邮件
     *
     * @param emailEvent
     */
    private boolean checkMail(EmailEvent emailEvent) {
        boolean check = false;

        //收件人不能为空
        if (StringUtils.isEmpty(emailEvent.getReceivers())) {
            log.info("出现异常，收件人不能为空：{}", emailEvent);
            check = true;
        }

        //邮件主题不能为空
        if (StringUtils.isEmpty(emailEvent.getSubject())) {
            log.info("出现异常，邮件主题不能为空：{}", emailEvent);
            check = true;
        }

        return check;
    }
}
