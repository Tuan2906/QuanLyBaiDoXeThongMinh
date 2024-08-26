/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlbdx.repository;

import com.qlbdx.pojo.Baidoxe;
import com.qlbdx.pojo.Chitietkhudo;
import com.qlbdx.pojo.Khudoxe;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public interface ChiTietKhuDoXeRepository {

    List<Chitietkhudo> getChiTietKhuDoXe(Map<String, String> params);

    Chitietkhudo getChiTietKhuDoXeByID(int id);

    void add_or_update(Chitietkhudo p);
    void deleteChiTietKhuDoXe(int id);

}
