package com.qlbdx.controllers;

import com.qlbdx.service.ChiTietKhuDoXeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tuanc
 */
@Controller
@RequestMapping("/api")
@CrossOrigin
public class ApiKhuDoXeController {
     @Autowired
    private ChiTietKhuDoXeService chiTietKhuDoService;
    
     
     
   @DeleteMapping("/chiTietKhuDo/{chiTietKhuDoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "chiTietKhuDoId") int id) {
        try {
        this.chiTietKhuDoService.deleteChiTietKhuDoXe(id);
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
