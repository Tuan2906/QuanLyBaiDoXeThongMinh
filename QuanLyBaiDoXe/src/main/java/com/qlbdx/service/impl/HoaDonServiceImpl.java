/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.service.impl;

import com.qlbdx.pojo.Hoadon;
import com.qlbdx.repository.HoaDonRepository;
import com.qlbdx.service.HoaDonService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author quang
 */
@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    private HoaDonRepository hoadonRep;

    @Override
    public Map<String, Object> getHoaDon(Map<String, String> params) {
        return this.hoadonRep.getHoaDon(params);
    }

    @Override
    public void addHoaDon(Hoadon b) {
        this.hoadonRep.addHoaDon(b);

    }

    @Override
    public void deleteHoaDon(int id) {
         try {

            this.hoadonRep.deleteHoaDon(id);
        } catch (RuntimeException e) {
            // Xử lý ngoại lệ hoặc ghi log
            throw e; // Hoặc ném một ngoại lệ khác nếu cần
        }
    }

    @Override
    public Hoadon getHoaDonById(int id) {
        return this.hoadonRep.getHoaDonById(id);
    }

    @Override
    public Hoadon getHoaDonByThongTinDangKyId(int ttdkId) {
        return this.hoadonRep.getHoaDonByThongTinDangKyId(ttdkId);
    }
    

}
