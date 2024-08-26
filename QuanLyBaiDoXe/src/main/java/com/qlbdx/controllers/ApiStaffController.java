/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.controllers;

import com.qlbdx.pojo.Baidoxe;
import com.qlbdx.service.StaffService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author quang
 */
@Controller
@RequestMapping("/api")
@CrossOrigin
public class ApiStaffController {
    @Autowired
    private StaffService staffService;
//    @GetMapping(path = "/staffBaiDoXe", produces = MediaType.APPLICATION_JSON_VALUE)
//    @CrossOrigin
//    public ResponseEntity<Baidoxe> baiDoXeStaff(Principal p) {
//        return new ResponseEntity<Baidoxe>((Baidoxe) this.staffService.getBaiXeByIdStaff(p.getName()), HttpStatus.OK);
//    }
}
