/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlbdx.repository;

import com.qlbdx.pojo.Baidoxe;
import com.qlbdx.pojo.Gia;
import java.util.List;

/**
 *
 * @author admin
 */
public interface GiaRepository {
    List<Gia> getGia();
    Gia getGiaByID(int id);
    void add_or_update(Gia p);
}
