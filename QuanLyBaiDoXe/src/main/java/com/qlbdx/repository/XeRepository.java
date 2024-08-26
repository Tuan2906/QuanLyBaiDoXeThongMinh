/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlbdx.repository;

import com.qlbdx.pojo.User;
import com.qlbdx.pojo.Xe;
import java.util.List;

/**
 *
 * @author quang
 */
public interface XeRepository {

    List<Xe> getXeByIdUser(User u);
    void add_or_update(Xe xe);
    Xe getXeById(long id);
    void deleteXe(Long id);
}
