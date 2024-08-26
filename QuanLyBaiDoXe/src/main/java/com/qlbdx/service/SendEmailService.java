/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlbdx.service;

import com.qlbdx.pojo.Baidoxe;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author admin
 */
public interface SendEmailService {

    void sendEmail(String to, String subject, String message);
    void sendEmailWithAttachment(String to, String subject, String message, MultipartFile file);

    
}
