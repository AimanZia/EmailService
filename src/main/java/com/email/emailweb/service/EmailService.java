package com.email.emailweb.service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.email.emailweb.model.EmailRequest;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    
    @Value("${spring.mail.username}")
    private String sender;

    public Boolean sendSimpleMail(EmailRequest emailRequest)
    {
        try {
            // SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            
            // simpleMailMessage.setFrom(sender);
            // simpleMailMessage.setTo(emailRequest.getTo());
            // simpleMailMessage.setText(emailRequest.getBody());
            // simpleMailMessage.setSubject(emailRequest.getSubject());
            
            // MimeMessage message = javaMailSender.createMimeMessage();
            // MimeMessageHelper helper = new MimeMessageHelper(message);

            // String bounceAddress = "amanansari879510@gmail.com";

            // helper.setFrom(sender);
            // helper.setTo(emailRequest.getTo());
            // helper.setText(emailRequest.getBody());
            // helper.setSubject(emailRequest.getSubject());

            // message.setHeader("Return-Path",bounceAddress);

            String bounceAddress = "amanansari879510@gmail.com";

            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                
                public void prepare(MimeMessage mimeMessage) throws Exception{

                    mimeMessage.setFrom(new InternetAddress(sender));
                    mimeMessage.setRecipients(Message.RecipientType.TO, emailRequest.getTo());
                    // mimeMessage.setHeader("Return-Path", sender);
                    mimeMessage.setSubject(emailRequest.getSubject());
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                    message.setText(emailRequest.getBody(),true);
                }
            };

            

            javaMailSender.send(preparator);

            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println(e);
            return false;
        }
    }
    
}
