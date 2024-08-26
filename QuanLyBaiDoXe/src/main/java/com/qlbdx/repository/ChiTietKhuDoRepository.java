/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlbdx.repository;

import com.qlbdx.pojo.Chitietkhudo;

/**
 *
 * @author quang
 */
public interface ChiTietKhuDoRepository {
    Chitietkhudo GetOrCreateCTKD(Long idKhu, Long idGia, Long idPhuongTIen);
}
