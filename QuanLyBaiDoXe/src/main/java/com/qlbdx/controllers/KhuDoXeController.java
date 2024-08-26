package com.qlbdx.controllers;

import com.qlbdx.pojo.Chitietkhudo;
import com.qlbdx.pojo.Khudoxe;
import com.qlbdx.service.ChiTietKhuDoXeService;
import com.qlbdx.service.KhuDoXeService;
import java.util.Date;
import java.util.Map;
import javax.validation.Valid;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author tuanc
 */
@Controller
@PropertySource("classpath:messages.properties")
public class KhuDoXeController {

    @Autowired
    private KhuDoXeService khuSer;

    @Autowired
    private ChiTietKhuDoXeService chiTietkhuSer;
    @Autowired
    private Environment env;

    
    @RequestMapping("/khudoxeView")
    public String KhuDoindex(Model model, @RequestParam Map<String, String> params) {

       
        model.addAttribute("khuDoXe", this.khuSer.getKhuDoXe(params));
        return "khudoxeView";
    }
     @RequestMapping("/chiTietKhuDoXeView")
    public String ChiTietKhuDoIndex(Model model, @RequestParam Map<String, String> params) {

       
        model.addAttribute("chiTietkhuDoXe", this.chiTietkhuSer.getChiTietKhuDoXe(params));
        return "chiTietKhuDoXeView";
    }
    
    
    @GetMapping("/khuDoXe")
    public String baiDoView(Model model) {
        model.addAttribute("khudoxe", new Khudoxe());
        return "khuDoXe";
    }

    @GetMapping("/khuDo/{khuDoId}")
    public String detailsView(Model model, @PathVariable(value = "khuDoId") int id) {
        model.addAttribute("khudoxe", this.khuSer.getKhuDoXeByID(id));
        return "khuDoXe";
    }

    @PostMapping("/khuDoXe")
    public String createView(Model model,
            @ModelAttribute(value = "khudoxe") @Valid Khudoxe p,
            BindingResult rs) {
        if (rs.hasErrors()) {
            return "khuDoXe";
        }

        try {
            String successMessage = "Lỗi";
            if (p.getId() != null) {
                successMessage = env.getProperty("mess.sucess");

            } else {
                successMessage = env.getProperty("add.sucess");

            }
            this.khuSer.add_or_update(p);

            model.addAttribute("messSucess", successMessage);

            return "forward:/khudoxeView";
        } catch (Exception ex) {
            System.out.println("loi co the vao day" + ex.getCause());
            String errorMessage = null;
            System.out.println("check da vo");
            errorMessage = env.getProperty("error.khudoxe.duplicate");

            model.addAttribute("errMsg", errorMessage);

            return "khuDoXe";
        }
    }

    @GetMapping("/ChiTietKhuDoXe")
    public String ChiTietKhuDoView(Model model) {
        model.addAttribute("ChiTietKhuDoXe", new Chitietkhudo());
        return "ChiTietKhuDoXe";
    }

    @GetMapping("/chiTietkhuDo/{chiTietkhuDoId}")
    public String detailsKhuDoView(Model model, @PathVariable(value = "chiTietkhuDoId") int id) {
        model.addAttribute("ChiTietKhuDoXe", this.chiTietkhuSer.getChiTietKhuDoXeByID(id));
        return "ChiTietKhuDoXe";
    }

    @PostMapping("/chiTietKhuDoXe")
    public String createChiTietView(Model model,
            @ModelAttribute(value = "ChiTietKhuDoXe") @Valid Chitietkhudo p,
            BindingResult rs) {
        if (p.getId() == null) {
            p.setNgayCapNhat(new Date()); // Hoặc LocalDateTime
        }
        System.out.println("vo nao" + p.getGiaId() + p.getNgayCapNhat());
        System.out.println("vo nao" + p.getKhuDoId() + p.getPhuongTienId());
        System.out.println("loi"+rs.getAllErrors());
        if (rs.hasErrors()) {
            System.out.println("vao day r");
            return "ChiTietKhuDoXe";
        }
        try {
            String successMessage = "Lỗi";
            if (p.getId() != null) {
                successMessage = env.getProperty("mess.sucess");

            } else {
                successMessage = env.getProperty("add.sucess");

            }
            this.chiTietkhuSer.add_or_update(p);

            model.addAttribute("messSucess", successMessage);

            return "forward:/chiTietKhuDoXeView";
        } catch (Exception ex) {
            System.out.println("loi co the vao day" + ex.getCause());
            String errorMessage = null;
            System.out.println("check da vo");
            errorMessage = env.getProperty("error.khudoxe.duplicate");

            model.addAttribute("errMsg", errorMessage);

            return "ChiTietKhuDoXe";
        }
    }

    private class khuDoService {

        public khuDoService() {
        }
    }

}
