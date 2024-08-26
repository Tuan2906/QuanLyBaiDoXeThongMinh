/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.controllers;

import com.qlbdx.pojo.Userhoantien;
import com.qlbdx.service.HoanTienService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author quang
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiUserHoanTienController {

    @Autowired
    private HoanTienService hoantienService;

    @PostMapping(path = "/userHoanTien",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<?> addUserHoanTIen(
            @RequestParam("hdid") int hdid,
            @RequestParam("uid") int uid) {
        try {
            Userhoantien uht = new Userhoantien();
            this.hoantienService.addHoanTien(uht, hdid, uid);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (RuntimeException ex) {
            System.err.println("Lỗi khác: " + ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
