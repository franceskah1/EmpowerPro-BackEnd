package com.example.empowerprobackend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.Properties;

@Configuration
public class EmailConfiguration {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("empowerpro.assistance@gmail.com");
        javaMailSender.setPassword("ujsm ktis ppxl yamr");
        Properties properties = javaMailSender.getJavaMailProperties();

        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.post", "587");
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        return javaMailSender;
    }
}
