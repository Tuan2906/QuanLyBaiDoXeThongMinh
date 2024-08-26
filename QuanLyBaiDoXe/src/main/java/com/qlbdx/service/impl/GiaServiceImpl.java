/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.qlbdx.pojo.Baidoxe;
import com.qlbdx.pojo.Baidoxepic;
import com.qlbdx.pojo.Gia;
import com.qlbdx.repository.BaiDoXeRepository;
import com.qlbdx.repository.GiaRepository;
import com.qlbdx.service.BaiDoXeService;
import com.qlbdx.service.GiaService;
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
public class GiaServiceImpl implements GiaService {

    @Autowired
    private GiaRepository giaF;
    @Override
    public List<Gia> getGia() {
        return this.giaF.getGia();
    }

    @Override
    public Gia getGiaByID(int id) {
         return this.giaF.getGiaByID(id);
    }

    @Override
    public void add_or_update(Gia p) {
        this.add_or_update(p);
    }

}
