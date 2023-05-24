package com.email.emailweb.config;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class EmailHandler {
    
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public void readBouncedMail() throws MessagingException
    {
        Session session = javaMailSender.getSession();
        Store store = session.getStore("imaps");
        store.connect();
        

    }
}
