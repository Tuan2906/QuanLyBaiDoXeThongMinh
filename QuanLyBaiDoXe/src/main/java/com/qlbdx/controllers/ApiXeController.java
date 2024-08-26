/*
 * Click nbfs: nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs: nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.controllers;

import com.qlbdx.components.JwtService;
import com.qlbdx.dto.XeDTO;
import com.qlbdx.pojo.User;
import com.qlbdx.service.BaiDoXeService;
import com.qlbdx.service.UserService;
import com.qlbdx.service.XeService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author tuan
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Cấu hình CORS toàn cầu nếu cần
public class ApiXeController {

    @Autowired
    private XeService xeService;

//    @DeleteMapping("/baiDo/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteBaiDo(@PathVariable(value = "id") int id) {
//            System.out.println("Bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
//
//        try {
//            this.baidoService.deleteBaiDoXe(id);
//            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//        } catch (RuntimeException e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }
//   ;
    @GetMapping("/xe")
    public ResponseEntity<List<XeDTO>> getAllXe() {
        System.out.println("vao day r");
        List<XeDTO> xeList = xeService.getXe(); // Gọi service để lấy danh sách xe
        return ResponseEntity.ok(xeList);
    }

    @PostMapping(path = "/xeAdd",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addOrUpdateXe(
            @RequestParam(value = "tenXe") String tenXe,
            @RequestParam(value = "bienSo") String bienSo,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        System.out.println("Vào xe với thông tin: id = " + ", tenXe = " + tenXe
                + ", bienSo = " + bienSo + ", image = " + file + ", userId = ");

        try {
            // Tạo XeDTO từ các tham số
            XeDTO xeDTO = new XeDTO();
            xeDTO.setTenXe(tenXe);
            xeDTO.setBienSo(bienSo);
            xeDTO.setFile(file);

            // Xử lý tệp tin nếu có
            if (file != null && !file.isEmpty()) {
                // Xử lý tệp tin (ví dụ: lưu trữ file, lấy đường dẫn file, v.v.)
            }

            xeService.add_or_update(xeDTO);
            return ResponseEntity.ok("Xe saved or updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while saving or updating Xe: " + e.getMessage());
        }
    }

    @GetMapping("/xe/{id}")
    public ResponseEntity<XeDTO> getXeById(@PathVariable("id") long id) {

        try {
            XeDTO xeDTO = xeService.getXeById(id);
            if (xeDTO == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null); // Xe không tìm thấy
            }
            return ResponseEntity.ok(xeDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // Lỗi hệ thống
        }
    }

    @DeleteMapping("/xe/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteXe(@PathVariable(value = "id") long id) {
        System.out.println("Bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");

        try {
            this.xeService.deleteXe(id);
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping(path = "/xe/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> partialUpdateXe(@PathVariable("id") long id, @RequestParam Map<String, String> updates,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            // Lấy xe hiện tại từ cơ sở dữ liệu
            XeDTO existingXeDTO = xeService.getXeById(id);
            if (existingXeDTO == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Xe không tìm thấy"); // Xe không tìm thấy
            }
            System.out.println("dawdawdawdaw");

            System.out.println(updates.get("tenXe"));
            System.out.println(updates.get("tenXe"));
            // Cập nhật các thuộc tính cần thiết từ yêu cầu
            existingXeDTO.setTenXe((String) updates.get("tenXe"));
            existingXeDTO.setBienSo((String) updates.get("bienSo"));

            if (file != null && !file.isEmpty()) {
                existingXeDTO.setFile(file);
            }

            // Lưu lại thông tin đã cập nhật
            xeService.add_or_update(existingXeDTO);

            return ResponseEntity.ok("Xe đã được cập nhật thành công.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Đã xảy ra lỗi khi cập nhật xe: " + e.getMessage()); // Lỗi hệ thống
        }
    }

}
