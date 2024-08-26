/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.service.impl;

import com.qlbdx.pojo.Baidoxe;
import com.qlbdx.pojo.User;
import com.qlbdx.repository.StaffRepository;
import com.qlbdx.repository.UserRepository;
import com.qlbdx.service.StaffService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author quang
 */
@Service
public class StaffServiceImpl implements StaffService{

    @Autowired
    private StaffRepository staffRep;
    @Autowired
    private UserRepository userRep;
    
    @Override
    public Set<Baidoxe> getBaiXeByIdStaff(String username) {
        return this.staffRep.getBaiXeByIdStaff(this.userRep.getUserByUsername(username));
    }
    
}
