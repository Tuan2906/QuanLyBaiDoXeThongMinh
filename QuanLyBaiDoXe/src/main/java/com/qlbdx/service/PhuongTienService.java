/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlbdx.service;

import com.qlbdx.pojo.Baidoxe;
import com.qlbdx.pojo.Phuongtien;
import java.util.List;

/**
 *
 * @author admin
 */
public interface PhuongTienService {

    Phuongtien getPhuongTienID(int id);

    List<Phuongtien> getPhuongTien();

}
