/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlbdx.repository;

import com.qlbdx.pojo.Baidoxepic;
import java.util.List;

/**
 *
 * @author admin
 */
public interface PictureRepository {

    List<Baidoxepic> getPicturesByParkingLot(int baiDoXeId);

    List<Baidoxepic> getPic();

    void add_or_update(Baidoxepic p);

}
