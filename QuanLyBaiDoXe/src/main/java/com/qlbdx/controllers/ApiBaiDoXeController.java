/*
 * Click nbfs: nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs: nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.controllers;

import com.qlbdx.Mapper.BaidoxeMapper;
import com.qlbdx.Mapper.ListMapper;
import com.qlbdx.components.JwtService;
import com.qlbdx.dto.BaidoxeDTO;
import com.qlbdx.pojo.Baidoxe;
import com.qlbdx.pojo.User;
import com.qlbdx.service.BaiDoXeService;
import com.qlbdx.service.UserService;
import java.security.Principal;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PutMapping;
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
public class ApiBaiDoXeController {

    @Autowired
    private BaiDoXeService baidoService;

    @GetMapping(path = "/staffBaiDoXe", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<BaidoxeDTO>> getbaiDoXeStaff(Principal p, @RequestParam Map<String, String> params) {
        List<BaidoxeDTO> bdx = new ArrayList<>();
        this.baidoService.getChiTietBaiXeByUsername(p.getName(),params).forEach(bd -> {
            bdx.add(BaidoxeMapper.toDTOSpecial(bd));
        });
        return new ResponseEntity<>(bdx, HttpStatus.OK);
    }

    @GetMapping(path = "/HomeBaiDoXe", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<BaidoxeDTO>> getHomeBaiDoXe( @RequestParam Map<String, String> params) {
        List<Object[]> cds = this.baidoService.getAllBai(params);
        List<BaidoxeDTO> bdx = ListMapper.mapListObject(cds, BaidoxeMapper::toDTOSpecial);
        return new ResponseEntity<>(bdx, HttpStatus.OK);
    }
    
    
    @DeleteMapping("/baiDo/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBaiDo(@PathVariable(value = "id") int id) {
        System.out.println("Bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");

        try {
            this.baidoService.deleteBaiDoXe(id);
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
