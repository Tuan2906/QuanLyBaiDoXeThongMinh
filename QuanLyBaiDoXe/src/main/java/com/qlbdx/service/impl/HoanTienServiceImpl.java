/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.service.impl;

import com.qlbdx.pojo.Hoadon;
import com.qlbdx.pojo.User;
import com.qlbdx.pojo.Userhoantien;
import com.qlbdx.repository.HoaDonRepository;
import com.qlbdx.repository.HoanTIenRepository;
import com.qlbdx.repository.UserRepository;
import com.qlbdx.service.HoaDonService;
import com.qlbdx.service.HoanTienService;
import com.qlbdx.service.UserService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author quang
 */
@Service
public class HoanTienServiceImpl implements HoanTienService {

    @Autowired
    private HoaDonRepository hoadonRep;
    @Autowired
    private UserRepository userRep;
    @Autowired
    private HoanTIenRepository hoantienRep;

    @Override
    public void addHoanTien(Userhoantien uht, int hoadonId, int userId) {
        Hoadon hd = hoadonRep.getHoaDonById(hoadonId);
        User u = userRep.getUserById(Long.valueOf(userId));
        uht.setNgayHoan(new Date());
        uht.setHoaDonid(hd);
        uht.setUserId(u);
        this.hoantienRep.addHoanTien(uht);
    }

}
