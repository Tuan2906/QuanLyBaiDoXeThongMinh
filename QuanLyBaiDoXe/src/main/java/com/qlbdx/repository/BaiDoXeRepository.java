/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlbdx.repository;

import com.qlbdx.pojo.Baidoxe;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public interface BaiDoXeRepository {
   Map<String, Object> getBaiDoXe(Map<String, String> params);
    void addBaiDoXe(Baidoxe b);
    void deleteBaiDoXe(int id);
    List<Object[]> getChiTietBaiXeById(int id);
    List<Object[]> getChiTietBaiXeByUserId(int id, Map<String, String> params);
    List<Object[]> getAllBai(Map<String, String> params);
    List<Object[]> getAllPicWithBaiId(int id);
}
