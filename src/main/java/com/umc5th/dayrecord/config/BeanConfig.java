package com.umc5th.dayrecord.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class BeanConfig {
    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private boolean auth;

    @Value("${spring.mail.protocol}")
    private String protocol;


    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(host);
        sender.setPort(port);
        sender.setUsername(username);
        sender.setPassword(password);
        sender.setProtocol(protocol);
        sender.setDefaultEncoding("UTF-8");

        Properties prop = new Properties();
        // 올바른 프로퍼티 키 사용
        prop.put("mail.smtp.auth", String.valueOf(auth));
        prop.put("mail.smtp.starttls.enable", "true");
        // 필요하다면 STARTTLS가 필수적으로 요구되게 설정
        prop.put("mail.smtp.starttls.required", "true");
        sender.setJavaMailProperties(prop);

        return sender;
    }
}
