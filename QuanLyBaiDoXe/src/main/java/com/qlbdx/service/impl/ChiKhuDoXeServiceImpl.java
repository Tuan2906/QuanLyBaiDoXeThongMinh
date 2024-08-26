/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.qlbdx.pojo.Baidoxe;
import com.qlbdx.pojo.Baidoxepic;
import com.qlbdx.pojo.Chitietkhudo;
import com.qlbdx.pojo.Khudoxe;
import com.qlbdx.repository.BaiDoXeRepository;
import com.qlbdx.repository.ChiTietKhuDoXeRepository;
import com.qlbdx.repository.KhuDoXeRepository;
import com.qlbdx.service.BaiDoXeService;
import com.qlbdx.service.ChiTietKhuDoXeService;
import com.qlbdx.service.KhuDoXeService;
import com.qlbdx.utils.DateUtils;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author admin
 */
@Service
public class ChiKhuDoXeServiceImpl implements ChiTietKhuDoXeService {
    
    @Autowired
    private ChiTietKhuDoXeRepository chiTietkhuDoRepo;
    
    @Override
    public List<Chitietkhudo> getChiTietKhuDoXe(Map<String, String> params) {
        return this.chiTietkhuDoRepo.getChiTietKhuDoXe(params);
    }
    
    @Override
    public Chitietkhudo getChiTietKhuDoXeByID(int id) {
        return this.chiTietkhuDoRepo.getChiTietKhuDoXeByID(id);
    }
    
    @Override
    public void add_or_update(Chitietkhudo p) {
        this.chiTietkhuDoRepo.add_or_update(p);
    }

    @Override
    public void deleteChiTietKhuDoXe(int id) {
        try {
            this.chiTietkhuDoRepo.deleteChiTietKhuDoXe(id);
        } catch (RuntimeException e) {
            // Xử lý ngoại lệ hoặc ghi log
            throw e; // Hoặc ném một ngoại lệ khác nếu cần
        }
    }
    
}
