package com.example.service;


import com.example.auth.UserIdentity;
import com.example.model.SendMailRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;


import javax.annotation.PreDestroy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MailService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final UserIdentity userIdentity;

    private final JavaMailSenderImpl mailSender;
    private final long tag;
    private final List<String> mailMessages;
    private final String LOG_EMAIL;

    public MailService(JavaMailSenderImpl mailSender,UserIdentity userIdentity) {
        this.mailSender = mailSender;
        this.userIdentity = userIdentity;
        this.tag = System.currentTimeMillis();
        this.mailMessages = new ArrayList<>();
        this.LOG_EMAIL = mailSender.getUsername();
    }

    public void sendMail(SendMailRequest request) {
        sendMail(request.getSubject(), request.getContent(), request.getReceivers());
    }

    public void sendMail(String subject, String content, List<String> receivers) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailSender.getUsername());
        message.setTo(receivers.toArray(new String[0]));
        message.setSubject(subject);
        message.setText(content);

        try {
            mailSender.send(message);
            mailMessages.add(content);
            printMessages();
        } catch (MailAuthenticationException e) {
            LOGGER.error(e.getMessage());
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public void sendNewProductMail(String productId) {
        String content = String.format("There's a new created product (%s).", productId);
        sendMail("New Product", content,
                Collections.singletonList(userIdentity.getEmail()));
    }

    public void sendDeleteProductMail(String productId) {
        String content = String.format("There's a product deleted (%s).", productId);
        sendMail("Product Deleted", content,
                Collections.singletonList(userIdentity.getEmail()));
    }

    private void printMessages() {
        System.out.println("----------");
        mailMessages.forEach(System.out::println);
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("##########");
        System.out.printf("Spring Boot is about to destroy Mail Service %d.\n\n", tag);
    }
}