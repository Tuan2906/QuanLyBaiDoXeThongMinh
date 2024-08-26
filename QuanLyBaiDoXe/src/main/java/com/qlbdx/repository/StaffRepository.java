/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlbdx.repository;

import com.qlbdx.pojo.Baidoxe;
import com.qlbdx.pojo.User;
import java.util.Set;

/**
 *
 * @author quang
 */
public interface StaffRepository {
    Set<Baidoxe> getBaiXeByIdStaff(User u);
}
