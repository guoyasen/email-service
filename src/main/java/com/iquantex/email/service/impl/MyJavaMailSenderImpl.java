package com.iquantex.email.service.impl;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class MyJavaMailSenderImpl extends JavaMailSenderImpl {

    private String HOST = "crmail.crc.com.cn";

    private Integer PORT = 25;

    private String USERNAME = "crct_securities@crctrust.com";

    private String PASSWORD = "Mkt2021!";

    private String FROMUSER = "crct_securities@crctrust.com";

    public MyJavaMailSenderImpl() {
        this.setHost(HOST);
        this.setPort(PORT);//端口
        this.setUsername(USERNAME);
        this.setPassword(PASSWORD);
        Properties properties365 = new Properties();
        properties365.setProperty("from", FROMUSER);
        properties365.setProperty("mail.smtp.starttls.enable", "true");
        properties365.setProperty("mail.smtp.socketFactory.fallback", "true");
        properties365.setProperty("mail.smtp.connectiontimeout", "50000");
        properties365.setProperty("mail.smtp.timeout","50000");
        properties365.setProperty("mail.smtp.writetimeout","50000");
        this.setJavaMailProperties(properties365);
    }

}
