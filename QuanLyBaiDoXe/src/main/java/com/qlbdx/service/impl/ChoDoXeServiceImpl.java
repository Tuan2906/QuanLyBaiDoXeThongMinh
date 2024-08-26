/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.service.impl;

import com.qlbdx.pojo.Chodo;
import com.qlbdx.repository.ChoDoXeRepository;
import com.qlbdx.service.ChoDoXeService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author quang
 */
@Service
public class ChoDoXeServiceImpl implements ChoDoXeService {

    @Autowired
    private ChoDoXeRepository choDoRepo;

    @Override
    public Map<String, Object> getChoDoXe(Map<String, String> params) {
        return choDoRepo.getChoDoXe(params);
    }

    @Override
    public void addorUpdateChoDo(Chodo cd) {
        choDoRepo.addorUpdateChoDo(cd);
    }

    @Override
    public void deleteChoDo(Chodo cd) {
        try {
            choDoRepo.deleteChoDo(cd);

        } catch (RuntimeException e) {
            // Xử lý ngoại lệ hoặc ghi log
            throw e; // Hoặc ném một ngoại lệ khác nếu cần
        }
    }

    @Override
    public Chodo getChoDoById(long id) {
        return choDoRepo.getChoDoById(id);
    }
    @Override
    public void updateForeignKeyChoDo(Integer oldDetailId, Integer newId, Integer baiDoXeid)  {
        this.choDoRepo.updateForeignKeyChoDo(oldDetailId, newId,baiDoXeid);
    }

    @Override
    public List<Object[]> getChoDoByIdKhuAndBai(int baiId, int ChiTietkhuId,Map<String, String> params) {
        return this.choDoRepo.getChoDoByIdKhuAndBai(baiId, ChiTietkhuId,params);
    }

    @Override
    public List<Long> getDistinctChodoIds(Map<String, String> params) {
        return this.choDoRepo.getDistinctChodoIds(params);
    }

}
