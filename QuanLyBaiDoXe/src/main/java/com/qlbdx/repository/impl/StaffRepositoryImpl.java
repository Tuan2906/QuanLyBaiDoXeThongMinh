/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.repository.impl;

import com.qlbdx.pojo.Baidoxe;
import com.qlbdx.pojo.User;
import com.qlbdx.repository.StaffRepository;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author quang
 */
@Repository
@Transactional
public class StaffRepositoryImpl implements StaffRepository{

    @Override
    public Set<Baidoxe> getBaiXeByIdStaff(User u) {
        return u.getBaidoxeSet();
    }
    
}
