/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.service.impl;

import com.qlbdx.pojo.Khudoxe;
import com.qlbdx.repository.KhuDoXeRepository;
import com.qlbdx.service.KhuDoXeService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author quang
 */
@Service
public class KhuDoXeServiceImpl implements KhuDoXeService{
    @Autowired
    private KhuDoXeRepository khuDoRepo;

    @Override
    public List<Khudoxe> getKhuDoXe(Map<String, String> params) {
        return this.khuDoRepo.getKhuDoXe(params);
    }

    @Override
    public Khudoxe getKhuDoXeByID(int id) {
        return this.khuDoRepo.getKhuDoXeByID(id);
    }

    @Override
    public void add_or_update(Khudoxe p) {
        this.khuDoRepo.add_or_update(p);
    }

    @Override
    public void deleteKhuDoXe(int id) {
        this.khuDoRepo.deleteKhuDoXe(id);
    }

    @Override
    public List<Object[]> getKhuDoXeByBaiDoXeId(int baiDoXeId, Map<String, String> params) {
        return this.khuDoRepo.getKhuDoXeByBaiDoXeId(baiDoXeId,  params);
    }
}
