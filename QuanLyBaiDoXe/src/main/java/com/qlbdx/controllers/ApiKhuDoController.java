/*
 * Click nbfs: nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs: nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.controllers;

import com.qlbdx.Mapper.ChoDoMapper;
import com.qlbdx.Mapper.ListMapper;
import com.qlbdx.Mapper.PictureMapper;
import com.qlbdx.components.JwtService;
import com.qlbdx.dto.ChoDoDTO;
import com.qlbdx.dto.KhuDoXeDTO;
import com.qlbdx.pojo.User;
import com.qlbdx.service.BaiDoXeService;
import com.qlbdx.service.ChoDoXeService;
import com.qlbdx.service.KhuDoXeService;
import com.qlbdx.service.UserService;
import java.security.Principal;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
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
 * @author quang
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiKhuDoController {

    @Autowired
    private KhuDoXeService khudoService;

    @Autowired
    private ChoDoXeService chodoSer;

    @Autowired
    private BaiDoXeService baidoSer;

    @GetMapping(path = "/getKhuDoXeByBaiDoXe", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<KhuDoXeDTO>> getKhuDoXeByBaiDoXeId(@RequestParam int baidoxeid, @RequestParam Map<String, String> params) {
        List<KhuDoXeDTO> kdx = new ArrayList<>();
        this.khudoService.getKhuDoXeByBaiDoXeId(baidoxeid, params).forEach(obj -> {
            kdx.add(
                    new KhuDoXeDTO(
                            ((Number) obj[0]).longValue(), // ID khu vực, chuyển đổi từ Integer/Long sang Long
                            ((Number) obj[1]).longValue(), // ID bãi đỗ xe, chuyển đổi từ Integer/Long sang Long
                            (String) obj[2], //
                            (Double) obj[3], // Giá trị, ép kiểu từ Object sang Double
                            (String) obj[4]
                    )
            );
        });
        return new ResponseEntity<>(kdx, HttpStatus.OK);
    }

    // nguoi dung
    @GetMapping(path = "/getChiTietBai", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<KhuDoXeDTO>> getChiTietBai(@RequestParam(required = false) int baidoxeid, @RequestParam Map<String, String> params) {
        List<KhuDoXeDTO> kdx = new ArrayList<>();
        Map<String, String> prmKhu = new HashMap<>();
        Map<String, String> prmCho = new HashMap<>();
        if (params.get("gia") != null || params.get("pt") != null) {
            prmKhu.put("gia", params.get("gia"));
            prmKhu.put("pt", params.get("pt"));
        }
        if (params.get("vt") != null || params.get("kc") != null) {
            prmCho.put("vt", params.get("vt"));
            prmCho.put("kc", params.get("kc"));
        }
        this.khudoService.getKhuDoXeByBaiDoXeId(baidoxeid, prmKhu).forEach(obj -> {
            List<Object[]> cds = this.chodoSer.getChoDoByIdKhuAndBai(Integer.parseInt(obj[0].toString()), Integer.parseInt(obj[1].toString()), prmCho);
            List<Object[]> pics = this.baidoSer.getAllPicWithBaiId(Integer.parseInt(obj[0].toString()));
            pics.forEach(action -> {
                System.out.println("1111111111111111");
                System.out.println(action[0]);
                                System.out.println(action[1]);

            });
            kdx.add(
                    new KhuDoXeDTO(
                            ((Number) obj[0]).longValue(), // ID khu vực, chuyển đổi từ Integer/Long sang Long
                            ((Number) obj[1]).longValue(), // ID bãi đỗ xe, chuyển đổi từ Integer/Long sang Long
                            (String) obj[2], //
                            (Double) obj[3], // Giá trị, ép kiểu từ Object sang Double
                            (String) obj[4],
                            ListMapper.mapListObject(cds, ChoDoMapper::toDTO),
                            ListMapper.mapListObject(pics, PictureMapper::toDTO)
                    )
            );
        });
        return new ResponseEntity<>(kdx, HttpStatus.OK);
    }

    @DeleteMapping("/khuDo/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteKhuDo(@PathVariable(value = "id") int id) {
        System.out.println("Bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");

        try {
            this.khudoService.deleteKhuDoXe(id);
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
