/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.controllers;

import com.qlbdx.dto.LichSuDangKyChoDoDTO;
import com.qlbdx.dto.ThongTinDangKyDTO;
import com.qlbdx.dto.ThongTinDangKyDTO_v2;
import com.qlbdx.service.ThongTinDangKyService;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tuanc
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiThongTinDangKyChoDo {
    @Autowired
    private ThongTinDangKyService thongTinDangKiService;

    // API để lưu hoặc cập nhật thông tin đăng ký
    @PostMapping(path="/saveThongTinDangKy",  produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> saveThongTinDangKi(@RequestBody ThongTinDangKyDTO_v2 thongTinDangKyDTO) {
        try {
            System.out.println("dl"+thongTinDangKyDTO.getThoiGianVoBai());
            thongTinDangKiService.add_or_update(thongTinDangKyDTO);
            return ResponseEntity.ok("Lưu thông tin đăng ký thành công!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Lỗi khi lưu thông tin đăng ký: " + e.getMessage());
        }
    }
    @GetMapping("/lichsudangky")
    public ResponseEntity<List<LichSuDangKyChoDoDTO>> getActiveRegistrationsByUserId(@RequestParam("userId") Long userId) {
        System.out.println("d"+userId);
        List<LichSuDangKyChoDoDTO> registrations = thongTinDangKiService.getActiveRegistrationsByUserId(userId);
        System.out.println("test"+registrations);
        return ResponseEntity.ok(registrations);
    }
}
