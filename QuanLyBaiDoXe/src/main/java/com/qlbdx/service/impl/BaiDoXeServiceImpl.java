/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.qlbdx.pojo.Baidoxe;
import com.qlbdx.pojo.Baidoxepic;
import com.qlbdx.repository.BaiDoXeRepository;
import com.qlbdx.repository.UserRepository;
import com.qlbdx.service.BaiDoXeService;
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
public class BaiDoXeServiceImpl implements BaiDoXeService {

    @Autowired
    private BaiDoXeRepository baiDoRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Map<String, Object> getBaiDoXe(Map<String, String> params) {

        return this.baiDoRepo.getBaiDoXe(params);
    }

    @Override
    public void addBaiDoXe(Baidoxe p) {
        System.out.println("vo" + p.getDiachi() + p.getThoigiancua() + p.getThoigiandongcua());
        if (p != null) {
            // Chuyển đổi thời gian
            if (p.getThoigiancua() != null) {
                p.setThoigiancua(new Timestamp(p.getThoigiancua().getTime()));
            }
            if (p.getThoigiandongcua() != null) {
                p.setThoigiandongcua(new Timestamp(p.getThoigiandongcua().getTime()));
            }

            // Xử lý ảnh
            if (p.getBaidoxepicSet() != null && !p.getBaidoxepicSet().isEmpty()) {
                for (Baidoxepic epic : p.getBaidoxepicSet()) {
                    MultipartFile file = epic.getFile();
                    if (file != null && !file.isEmpty()) {
                        try {
                            Map<String, Object> res = this.cloudinary.uploader().upload(file.getBytes(),
                                    ObjectUtils.asMap("resource_type", "auto"));
                            epic.setImage(res.get("secure_url").toString());
                        } catch (IOException ex) {
                            Logger.getLogger(BaiDoXeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            }
            System.out.println("sau khi vo" + p.getDiachi() + p.getThoigiancua() + p.getThoigiandongcua());

            // Ghi thông tin
            // Lưu đối tượng vào repository
            this.baiDoRepo.addBaiDoXe(p);
        }
    }

    @Override
    public void deleteBaiDoXe(int id) {
        try {
            this.baiDoRepo.deleteBaiDoXe(id);
        } catch (RuntimeException e) {
            // Xử lý ngoại lệ hoặc ghi log
            throw e; // Hoặc ném một ngoại lệ khác nếu cần
        }

    }

    @Override
    public List<Object[]> getChiTietBaiXeById(int id) {
        System.out.println("vo duoc");
        return this.baiDoRepo.getChiTietBaiXeById(id);
    }

    @Override
    public List<Object[]> getChiTietBaiXeByUsername(String username,Map<String, String> params) {
        Long id = this.userRepo.getUserByUsername(username).getId();
        return this.baiDoRepo.getChiTietBaiXeByUserId(id.intValue(),params);
    }

    @Override
    public List<Object[]> getAllBai(Map<String, String> params) {
        this.baiDoRepo.getAllBai(params).forEach(obj -> {
            System.out.println(obj[0]);
            System.out.println(obj[1]);
            System.out.println(obj[2]);
            System.out.println(obj[3]);
            System.out.println(obj[4]);
            System.out.println(obj[5]);
            System.out.println(obj[6]);

        });

        return this.baiDoRepo.getAllBai(params);
    }

    @Override
    public List<Object[]> getAllPicWithBaiId(int id) {
        return this.baiDoRepo.getAllPicWithBaiId(id);
    }

}
