/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlbdx.service;

import com.qlbdx.pojo.Baidoxe;
import com.qlbdx.pojo.Khudoxe;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public interface KhuDoXeService {

    List<Khudoxe> getKhuDoXe(Map<String, String> params);

    Khudoxe getKhuDoXeByID(int id);

    void add_or_update(Khudoxe p);

    void deleteKhuDoXe(int id);

    List<Object[]> getKhuDoXeByBaiDoXeId(int baiDoXeId, Map<String, String> params);

}
