/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.service.impl;

import com.qlbdx.pojo.Chitietkhudo;
import com.qlbdx.repository.ChiTietKhuDoRepository;
import com.qlbdx.service.ChiTietKhuDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author quang
 */
@Service
public class ChiTietKhuDoServiceImpl implements ChiTietKhuDoService{

    @Autowired
    private ChiTietKhuDoRepository chitietRep;
    @Override
    public Chitietkhudo GetOrCreateCTKD(Long idKhu, Long idGia, Long idPhuongTIen) {
        return chitietRep.GetOrCreateCTKD(idKhu, idGia, idPhuongTIen);
    }
    
}
