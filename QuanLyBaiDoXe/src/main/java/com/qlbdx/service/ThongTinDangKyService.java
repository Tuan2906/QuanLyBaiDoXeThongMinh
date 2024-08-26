/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlbdx.service;

import com.qlbdx.dto.LichSuDangKyChoDoDTO;
import com.qlbdx.dto.ThongTinDangKyDTO_v2;
import com.qlbdx.pojo.Thongtindangky;
import java.util.List;
import java.util.Map;

/**
 *
 * @author quang
 */
public interface ThongTinDangKyService {

    List<Thongtindangky> getThongTinDangKy(Map<String, String> params);

    List<Object[]> getThongTinChoDaDangKy(int ChoDoId,Map<String, String> params);

    void HuyDangKy(int id);

    void add_or_update(ThongTinDangKyDTO_v2 xe);

    List<LichSuDangKyChoDoDTO> getActiveRegistrationsByUserId(Long currentUserId);

}
