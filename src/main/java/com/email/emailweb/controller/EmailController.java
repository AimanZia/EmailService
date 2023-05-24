package com.email.emailweb.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.emailweb.model.EmailRequest;
import com.email.emailweb.service.EmailService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;
    
    @GetMapping("/testEmail")
    public String testEmail(){

        return "this is tets";
    }

    @PostMapping(value="/sendEmail")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest email) {
        //TODO: process POST request
        System.out.println(email);

        Boolean status = this.emailService.sendSimpleMail(email);
        System.out.println(status);
        if(status)
        {
            return ResponseEntity.ok("Donee..");
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some error Ocurred while sending email");
        }
        
    }
    
}
