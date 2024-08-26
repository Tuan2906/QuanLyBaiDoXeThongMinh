/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.Mapper;

import com.qlbdx.dto.ThongTinDangKyDTO;
import java.sql.Timestamp;

/**
 *
 * @author quang
 */
public class ThongTinDangKyMapper {

    public static ThongTinDangKyDTO toDTO(Object[] obj) {
        return new ThongTinDangKyDTO(
                (Long) obj[0],
                (boolean) obj[1],
                DateMapper.formatter.format((Timestamp) obj[2]), // thoiGianRaBai
                DateMapper.formatter.format((Timestamp) obj[3]), // thoiGianRaBai
                DateMapper.formatter.format((Timestamp) obj[4]), // thoiGianRaBai
                (String) obj[5], // tenXe
                (String) obj[6], // bienSo
                (String) obj[7], // image
                (String) obj[8] // username
        );
    }
}
