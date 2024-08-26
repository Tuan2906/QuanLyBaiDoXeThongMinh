/*
 * Click nbfs: nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs: nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.controllers;

import com.qlbdx.Mapper.ChoDoMapper;
import com.qlbdx.Mapper.ListMapper;
import com.qlbdx.components.JwtService;
import com.qlbdx.dto.ChoDoDTO;
import com.qlbdx.pojo.Chodo;
import com.qlbdx.pojo.User;
import com.qlbdx.service.ChoDoXeService;
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

public class ApiChoDoXeController {

    @Autowired
    private ChoDoXeService chodoSer;

    @GetMapping(path = "/getChoDoByKhuAndBaiId", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<ChoDoDTO>> getChoDoByKhuAndBaiId(
            @RequestParam(name = "baidoxeid", required = false) int baidoxeid,
            @RequestParam(name = "chitietkhuid", required = false) int chitietkhuid,
            @RequestParam (required = false) Map<String, String> params
    ) {
        List<Object[]> cds = this.chodoSer.getChoDoByIdKhuAndBai(baidoxeid, chitietkhuid,params);

        cds.forEach(obj -> {
            System.out.println(obj[0]);
            System.out.println(obj[1]);
            System.out.println(obj[2]);
            System.out.println(obj[3]);
        });

        List<ChoDoDTO> choDoDTOs = ListMapper.mapListObject(cds, ChoDoMapper::toDTO);
        return new ResponseEntity<>(choDoDTOs, HttpStatus.OK);
    }

    @GetMapping(path = "/getChoDoDaDangKy", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Long>> getChoDoDaDangKy(@RequestParam Map<String, String> params) {
        List<Long> cds = this.chodoSer.getDistinctChodoIds(params);
        cds.forEach(obj -> {
            System.out.println(obj);
        });
        return new ResponseEntity<>(cds, HttpStatus.OK);
    }

    @DeleteMapping("/chodoxe/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChoDo(@PathVariable(value = "id") int id) {
        System.out.println("Bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");

        try {
            this.chodoSer.deleteChoDo(this.chodoSer.getChoDoById(id));
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
