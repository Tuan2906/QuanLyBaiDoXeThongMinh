/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.controllers;

import com.qlbdx.Mapper.ListMapper;
import com.qlbdx.Mapper.ThongTinDangKyMapper;
import com.qlbdx.dto.ThongTinDangKyDTO;
import com.qlbdx.service.ThongTinDangKyService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author quang
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiThongTinDangKyController {

    @Autowired
    private ThongTinDangKyService ttdkService;
    // Cho Nhan vien
    @GetMapping(path = "/thongtindangky", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<ThongTinDangKyDTO>> getbaiDoXeStaff(@RequestParam int choDoId, @RequestParam Map<String, String> params) {
        List<Object[]> ttdk = this.ttdkService.getThongTinChoDaDangKy(choDoId,params);
        List<ThongTinDangKyDTO> ttdkdto = ListMapper.mapListObject(ttdk, ThongTinDangKyMapper::toDTO);
        return new ResponseEntity<>(ttdkdto, HttpStatus.OK);
    }
    
    @PostMapping(path = "/thongtindangky/{id}")
    @CrossOrigin
    public ResponseEntity<?> UpdateThongTinDangKy( @PathVariable(value = "id") int id) {
        try {
            System.out.println("huy"+id);
            this.ttdkService.HuyDangKy(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
