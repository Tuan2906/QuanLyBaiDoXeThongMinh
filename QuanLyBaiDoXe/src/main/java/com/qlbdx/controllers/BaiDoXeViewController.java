/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.controllers;

import com.qlbdx.service.BaiDoXeService;
import java.text.SimpleDateFormat;
import java.util.Map;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author admin
 */
@Controller
@ControllerAdvice
public class BaiDoXeViewController {

    @Autowired
    private BaiDoXeService baiDoService;
//    @Autowired
//    private UserService userSerivice;

//    @ModelAttribute
//    public void commonAttribute(Model model){
//         model.addAttribute("cates", this.cateService.getCates());
//    }
    @RequestMapping("/baido")
    public String index(Model model, @RequestParam Map<String, String> params) {
//         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Format date as needed
//
//        this.userSerivice.getUsers().forEach(user -> {
//            String formattedDate = dateFormat.format(user.getDateJoined());
//            System.out.printf("User joined on: %s\n", formattedDate);
//        });
        model.addAttribute("baiDoXe", this.baiDoService.getBaiDoXe(params));
        return "baiDo";
    }
}
