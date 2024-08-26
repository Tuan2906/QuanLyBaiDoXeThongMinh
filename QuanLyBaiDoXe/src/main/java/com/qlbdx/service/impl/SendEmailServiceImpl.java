/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.qlbdx.pojo.Baidoxe;
import com.qlbdx.pojo.Baidoxepic;
import com.qlbdx.pojo.Gia;
import com.qlbdx.repository.BaiDoXeRepository;
import com.qlbdx.repository.ChoDoXeRepository;
import com.qlbdx.repository.GiaRepository;
import com.qlbdx.service.BaiDoXeService;
import com.qlbdx.service.ChoDoXeService;
import com.qlbdx.service.GiaService;
import com.qlbdx.service.SendEmailService;
import com.qlbdx.utils.DateUtils;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataSource;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author admin
 */
@Service
@PropertySource("classpath:ServiceThree.properties")
public class SendEmailServiceImpl implements SendEmailService {

    @Autowired
    private Environment env;

    @Override
    public void sendEmail(String to, String subject, String message) {
        try {
            System.out.println("moi truong" + env.getProperty("smtp.host"));
            String smtpHost = env.getProperty("smtp.host");
            System.out.println("moi truong" + env.getProperty("smtp.port"));

            int smtpPort = Integer.parseInt(env.getProperty("smtp.port"));
            String smtpUser = env.getProperty("smtp.user");
            String smtpPassword = env.getProperty("smtp.password");

            Email email = new HtmlEmail();
            email.setHostName(smtpHost);
            email.setSmtpPort(smtpPort);
            email.setAuthentication(smtpUser, smtpPassword);
            email.setStartTLSEnabled(true);

            email.setFrom(smtpUser, "Tuan Nguyen");
            email.addTo(to);
            email.setSubject(subject);
            email.setMsg(message);

            email.send();
        } catch (EmailException e) {
            e.printStackTrace(); // Xử lý lỗi gửi email
        }
    }

    @Override
    public void sendEmailWithAttachment(String to, String subject, String message, MultipartFile file) {
        try {
            String smtpHost = env.getProperty("smtp.host");
            int smtpPort = Integer.parseInt(env.getProperty("smtp.port"));
            String smtpUser = env.getProperty("smtp.user");
            String smtpPassword = env.getProperty("smtp.password");

            HtmlEmail email = new HtmlEmail();
            email.setHostName(smtpHost);
            email.setSmtpPort(smtpPort);
            email.setAuthentication(smtpUser, smtpPassword);
            email.setStartTLSEnabled(true);
            email.setFrom(smtpUser, "Your Name");
            email.addTo(to);
            email.setSubject(subject);
            email.setMsg(message);

            if (file != null && !file.isEmpty()) {
                // Lưu tệp vào tệp tạm thời
                File tempFile = File.createTempFile("attachment", "." + file.getOriginalFilename().split("\\.")[1]);
                file.transferTo(tempFile);

                // Tạo EmailAttachment và đính kèm tệp
                EmailAttachment attachment = new EmailAttachment();
                attachment.setPath(tempFile.getAbsolutePath());
                attachment.setDisposition(EmailAttachment.ATTACHMENT);
                attachment.setName(file.getOriginalFilename());

                email.attach(attachment);
            }
            System.out.println("email" + email.getText());

            email.send();
        } catch (Exception e) {
            System.out.println("e" + e);
        }
    }
}
