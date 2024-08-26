/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.controllers;

import com.qlbdx.pojo.Hoadon;
import com.qlbdx.pojo.User;
import com.qlbdx.service.HoaDonService;
import com.qlbdx.service.ThongTinDangKyService;
import java.util.Date;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author quang
 */
@Controller
public class HoaDonController {

    @Autowired
    private HoaDonService hoadonService;

    @Autowired
    private ThongTinDangKyService TTDKService;

    @GetMapping("/hoadon")
    public String index(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("hoadonData", this.hoadonService.getHoaDon(params));
        return "hoadon";
    }

    @ModelAttribute
    public void commonAttribute(Model model) {
        model.addAttribute("TTDKData", this.TTDKService.getThongTinDangKy(null));
    }

    @GetMapping("/hoadonAdd")
    public String createView(Model model) {
        Hoadon u = new Hoadon();
        u.setNgayCapNhat(new Date());
        u.setUid("11111111111111111");
        model.addAttribute("hoadon", u);
        return "hoadonAdd";
    }

    @PostMapping("/hoadonAdd")
    public String create(Model model, @ModelAttribute(value = "hoadon") @Valid Hoadon u, BindingResult rs) {

        if (rs.hasErrors()) {
//            model.addAttribute("infoMessage", rs.getAllErrors());
            return "hoadonAdd";
        }
        try {
            this.hoadonService.addHoaDon(u);
            return "redirect:/hoadon";
        } catch (Exception ex) {
//            if (ex.getCause() instanceof java.sql.SQLIntegrityConstraintViolationException) {
//                if (ex.getCause().toString().contains("user.email_UNIQUE")) {
//                    model.addAttribute("errEmailMsg", env.getProperty("user.email.uniqueMsg"));
//                }
//                if (ex.getCause().toString().contains("user.phone_UNIQUE")) {
//                    model.addAttribute("errPhoneMsg", env.getProperty("user.phone.uniqueMsg"));
//                }
//                if (ex.getCause().toString().contains("user.username_UNIQUE")) {
//                    model.addAttribute("errUsernameMsg", env.getProperty("user.username.uniqueMsg"));
//                }
//            } 
//            else if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
//                model.addAttribute("infoMessage", "Không thể cập nhật, kiểm tra lại các trường đã nhập đã tồn tại");
//            }
            return "hoadonAdd";
        }
    }

    @GetMapping("/hoadon/{id}")
    public String UserView(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("hoadon", this.hoadonService.getHoaDonById(id));
        return "hoadonAdd";
    }
}
