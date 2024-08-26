package com.qlbdx.controllers;

import com.qlbdx.pojo.Chitietkhudo;
import com.qlbdx.pojo.Chodo;
import com.qlbdx.pojo.ChodoMany;
import com.qlbdx.pojo.User;
import com.qlbdx.service.BaiDoXeService;
import com.qlbdx.service.ChiTietKhuDoService;
import com.qlbdx.service.ChoDoXeService;
import com.qlbdx.service.KhuDoXeService;
import com.qlbdx.service.StateService;
import java.util.Date;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author quang
 */
@Controller
@ControllerAdvice
@PropertySource("classpath:messages.properties")

public class ChoDoXeController {
    
    @Autowired
    private ChoDoXeService choDoSer;
    @Autowired
    private BaiDoXeService baiDoSe;
    @Autowired
    private KhuDoXeService khuDoSe;
    @Autowired
    private StateService stateSer;
    @Autowired
    private ChiTietKhuDoService chitietSer;
    @Autowired
    private Environment env;
    
    @ModelAttribute
    public void publicController(Model model) {
        model.addAttribute("baidoData", baiDoSe.getBaiDoXe(null));
                        System.out.println(baiDoSe.getBaiDoXe(null));

        model.addAttribute("khudoData", khuDoSe.getKhuDoXe(null));
        model.addAttribute("stateData", stateSer.getState());
    }
    
    @GetMapping("/chodoxe")
    public String index(Model model, @RequestParam Map<String, String> params) {
        System.out.println("AAAAAAAAAAAAAAAA");
                System.out.println(choDoSer.getChoDoXe(params));

        model.addAttribute("chodoData", choDoSer.getChoDoXe(params));
        return "chodoxe";
    }
    
    @GetMapping("/chodoxe/add")
    public String createView(Model model) {
        model.addAttribute("chodoxe", new Chodo());
        
        return "chodoxeAdd";
    }
    
//    @GetMapping("/chodoxe/addMany")
//    public String createManyView(Model model) {
//        model.addAttribute("chodoxeMany", new ChodoMany());
//        return "chodoxeAddMany";
//    }
    
    @PostMapping("/chodoxe/add")
    public String create(Model model, @ModelAttribute(value = "chodoxe") @Valid Chodo cd, BindingResult rs) {
        
        if (rs.hasErrors()) {
            model.addAttribute("infoMessage", rs.getAllErrors());
            
            return "chodoxeAdd";
        }
        try {
            
            Long idKhu = cd.getKdxId();
            Long idGia = cd.getGiaId();
            Long idPT = cd.getPtId();
            System.out.println("1111111");
            System.out.println(idKhu);
            System.out.println(idGia);
            System.out.println(idPT);
            System.out.println("2222222222222");
            Chitietkhudo ctkd = this.chitietSer.GetOrCreateCTKD(idKhu, idGia, idPT);
            System.out.println(ctkd);
            System.out.println("33333");
            
            cd.setKhuDoXeid(ctkd);
            this.choDoSer.addorUpdateChoDo(cd);
            return "redirect:/chodoxe";
        } catch (Exception ex) {
            model.addAttribute("infoMessage", ex.getCause());
            
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
            } else if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                model.addAttribute("infoMessage", "Không thể cập nhật, kiểm tra lại các trường đã nhập đã tồn tại");
            }
            System.out.println(ex.getMessage());
            return "chodoxeAdd";
        }
    }
    
//    @PostMapping("/chodoxe/addMany")
//    public String createMany(Model model, @ModelAttribute(value = "chodoxeMany") @Valid ChodoMany cd, BindingResult rs) {
//        System.out.println("111111111111111111111111");
//        System.out.println(cd.getGiaId());
//        System.out.println(cd.getBaiDoXeid());
//        System.out.println(cd.getKdxId());
//        System.out.println(cd.getStateId());
//        if (cd.getVitri() != null) {
//            System.out.println("Vitri:");
//            for (Integer v : cd.getVitri()) {
//                System.out.println(v);
//            }
//        }
//
//        // In từng phần tử của mảng khoangCach
//        if (cd.getKhoangCach() != null) {
//            System.out.println("KhoangCach:");
//            for (Double k : cd.getKhoangCach()) {
//                System.out.println(k);
//            }
//        } 
//        
//        
//
//        
//        return "chodoxeAddMany";
////        if (rs.hasErrors()) {
////            model.addAttribute("infoMessage", rs.getAllErrors());
////
////            return "chodoxeAddMany";
////        }
////        try {
////
////            Long idKhu = cd.getKdxId();
////            Long idGia = cd.getGiaId();
////            Long idPT = cd.getPtId();
////            System.out.println("1111111");
////            System.out.println(idKhu);
////            System.out.println(idGia);
////            System.out.println(idPT);
////            System.out.println("2222222222222");
////            Chitietkhudo ctkd = this.chitietSer.GetOrCreateCTKD(idKhu, idGia, idPT);
////            System.out.println(ctkd);
////            System.out.println("33333");
////            
////            cd.setKhuDoXeid(ctkd);
////            this.choDoSer.addorUpdateChoDo(cd);
////            return "redirect:/chodoxe";
////        } catch (Exception ex) {
////            model.addAttribute("infoMessage", ex.getCause());
////
////            if (ex.getCause() instanceof java.sql.SQLIntegrityConstraintViolationException) {
////                if (ex.getCause().toString().contains("user.email_UNIQUE")) {
////                    model.addAttribute("errEmailMsg", env.getProperty("user.email.uniqueMsg"));
////                }
////                if (ex.getCause().toString().contains("user.phone_UNIQUE")) {
////                    model.addAttribute("errPhoneMsg", env.getProperty("user.phone.uniqueMsg"));
////                }
////                if (ex.getCause().toString().contains("user.username_UNIQUE")) {
////                    model.addAttribute("errUsernameMsg", env.getProperty("user.username.uniqueMsg"));
////                }
////            } else if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
////                model.addAttribute("infoMessage", "Không thể cập nhật, kiểm tra lại các trường đã nhập đã tồn tại");
////            }
////            System.out.println(ex.getMessage());
////            return "chodoxeAdd";
////        }
//    }
    
    @GetMapping("/chodoxe/{chodoxe_id}")
    public String ChoDoView(Model model, @PathVariable(value = "chodoxe_id") int id) {
        model.addAttribute("chodoxe", this.choDoSer.getChoDoById(id));
        return "chodoxeAdd";
    }
    
}
