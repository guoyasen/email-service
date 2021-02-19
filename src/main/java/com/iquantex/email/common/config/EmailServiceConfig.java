package com.iquantex.email.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author gys
 * @date 2020/11/12
 */
@Configuration
public class EmailServiceConfig {
    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private Integer port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${mail.mime.splitlongparameters:false}")
    private String splitlongparameters;

    private static String gHost;

    private static Integer gPort;

    private static String gUsername;

    private static String gPassword;

    @PostConstruct
    public void refreshValue(){
        setgHost(host);
        setgPort(port);
        setgUsername(username);
        setgPassword(password);
        System.out.println("====test splitlongparameters==== " + splitlongparameters);
        System.setProperty("mail.mime.splitlongparameters", splitlongparameters);
    }

    public static String getgHost() {
        return gHost;
    }

    public static void setgHost(String gHost) {
        EmailServiceConfig.gHost = gHost;
    }

    public static Integer getgPort() {
        return gPort;
    }

    public static void setgPort(Integer gPort) {
        EmailServiceConfig.gPort = gPort;
    }

    public static String getgUsername() {
        return gUsername;
    }

    public static void setgUsername(String gUsername) {
        EmailServiceConfig.gUsername = gUsername;
    }

    public static String getgPassword() {
        return gPassword;
    }

    public static void setgPassword(String gPassword) {
        EmailServiceConfig.gPassword = gPassword;
    }
}
