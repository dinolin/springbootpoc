package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.SendMailRequest;
import com.example.service.MailService;

import io.swagger.v3.oas.annotations.Hidden;

import javax.validation.Valid;

@Hidden
@RestController
@RequestMapping(value = "/mail", produces = MediaType.APPLICATION_JSON_VALUE)
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping
    public ResponseEntity<Void> sendMail(@Valid @RequestBody SendMailRequest request) {
        mailService.sendMail(request);
        return ResponseEntity.noContent().build();
    }

}
