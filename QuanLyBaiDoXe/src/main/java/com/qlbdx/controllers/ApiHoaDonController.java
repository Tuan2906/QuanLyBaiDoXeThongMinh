/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.controllers;

import com.qlbdx.Mapper.HoadonMapper;
import com.qlbdx.dto.HoaDonDTO;
import com.qlbdx.pojo.Hoadon;
import com.qlbdx.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author quang
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiHoaDonController {
    @Autowired
    private HoaDonService hoadonService;
    
    
    @GetMapping(path = "/Hoadon/{ttdk_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<HoaDonDTO> getHoadonByTTDK(@PathVariable(value = "ttdk_id") int id) {
//        this.khudoService.getKhuDoXeByBaiDoXeId(baidoxeid).forEach(obj -> {
//            System.out.println(obj[0]);
//            System.out.println(obj[1]);
//            System.out.println(obj[2]);
//            System.out.println(obj[3].getClass().getName());
//            System.out.println(obj[4]);
//
//        });
        HoaDonDTO hddto = HoadonMapper.toDTO(this.hoadonService.getHoaDonByThongTinDangKyId(id)) ;
        return new ResponseEntity<>(hddto, HttpStatus.OK);
    }
    
    @DeleteMapping("/hoadon/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHoaDon(@PathVariable(value = "id") int id) {
            System.out.println("Bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");

        try {
            this.hoadonService.deleteHoaDon(id);
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
