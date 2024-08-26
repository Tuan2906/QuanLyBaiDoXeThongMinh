/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.controllers;

import com.qlbdx.dto.EmailRequest;
import com.qlbdx.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author tuanc
 */
@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private SendEmailService sendEmailService;

    @PostMapping(value = "/send-email", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            sendEmailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getMessage());
            return new ResponseEntity<>("Email sent successfully!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to send email.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/send-email-with-attachment")
    public ResponseEntity<String> sendEmailWithAttachment(
            @RequestParam("to") String to,
            @RequestParam("subject") String subject,
            @RequestParam("message") String message,
            @RequestParam("file") MultipartFile file) {
        try {
            System.out.println("ssasa"+file);
            // Gửi email với file đính kèm
            sendEmailService.sendEmailWithAttachment(to, subject, message, file);
            return ResponseEntity.ok("Email with attachment sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email with attachment.");
        }
    }
}
