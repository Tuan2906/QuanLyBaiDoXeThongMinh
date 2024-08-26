/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlbdx.repository;

import com.qlbdx.pojo.Hoadon;
import java.util.List;
import java.util.Map;

/**
 *
 * @author quang
 */
public interface HoaDonRepository {

    Map<String, Object> getHoaDon(Map<String, String> params);

    void addHoaDon(Hoadon b);

    void deleteHoaDon(int id);

    Hoadon getHoaDonById(int id);
    
    Hoadon getHoaDonByThongTinDangKyId(int ttdkId);
}
