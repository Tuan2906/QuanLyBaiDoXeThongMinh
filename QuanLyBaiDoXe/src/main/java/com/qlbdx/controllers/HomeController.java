/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.controllers;

import com.qlbdx.pojo.User;
import com.qlbdx.service.UserService;
import java.util.Date;
import java.util.Map;
import javax.persistence.Query;
import javax.validation.Valid;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author admin
 */
@PropertySource("classpath:messages.properties")
@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private Environment env;

    @GetMapping("/")
    public String index(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("usersData", this.userService.getUsers(params));
        return "home";  
    }

    @GetMapping("/user")
    public String createView(Model model) {
        User u = new User();
        u.setDateJoined(new Date());
        u.setActive(true);
        u.setFile(null);
        model.addAttribute("user", u);
        return "user";
    }

    @PostMapping("/user")
    public String create(Model model, @ModelAttribute(value = "user") @Valid User u, BindingResult rs) {

        if (rs.hasErrors()) {
//            model.addAttribute("infoMessage", rs.getAllErrors());
            return "user";
        }
        try {
            this.userService.addorUpdateUser(u);
            return "redirect:/";
        } catch (Exception ex) {
            if (ex.getCause() instanceof java.sql.SQLIntegrityConstraintViolationException) {
                if (ex.getCause().toString().contains("user.email_UNIQUE")) {
                    model.addAttribute("errEmailMsg", env.getProperty("user.email.uniqueMsg"));
                }
                if (ex.getCause().toString().contains("user.phone_UNIQUE")) {
                    model.addAttribute("errPhoneMsg", env.getProperty("user.phone.uniqueMsg"));
                }
                if (ex.getCause().toString().contains("user.username_UNIQUE")) {
                    model.addAttribute("errUsernameMsg", env.getProperty("user.username.uniqueMsg"));
                }
            } 
            else if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                model.addAttribute("infoMessage", "Không thể cập nhật, kiểm tra lại các trường đã nhập đã tồn tại");
            }
            return "user";
        }
    }

    @GetMapping("/user/{user_id}")
    public String UserView(Model model, @PathVariable(value = "user_id") int id) {
        model.addAttribute("user", this.userService.getUserById(id));
        return "user";
    }

}
