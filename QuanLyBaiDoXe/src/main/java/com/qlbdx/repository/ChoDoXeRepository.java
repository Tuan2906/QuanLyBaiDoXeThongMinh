/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.repository;

import com.qlbdx.pojo.Chodo;
import java.util.List;
import java.util.Map;

/**
 *
 * @author quang
 */
public interface ChoDoXeRepository {

    Map<String, Object> getChoDoXe(Map<String, String> params);

    void addorUpdateChoDo(Chodo cd);

    void deleteChoDo(Chodo cd);

    Chodo getChoDoById(long id);

    void updateForeignKeyChoDo(Integer oldDetailId, Integer newId, Integer baiDoXeid);
    
    List<Object[]> getChoDoByIdKhuAndBai (int baiId , int ChiTietkhuId,Map<String, String> params);
    
    public List<Long> getDistinctChodoIds(Map<String, String> params);
}
