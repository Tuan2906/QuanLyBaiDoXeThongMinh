/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.Mapper;

import com.qlbdx.dto.HoaDonDTO;
import com.qlbdx.pojo.Hoadon;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author quang
 */
public class HoadonMapper {
    
    public static HoaDonDTO toDTO(Hoadon obj) {
        if (obj == null) {
            return null;
        }

        // Chuyển đổi Hoadon entity sang HoaDonDTO
        return new HoaDonDTO(
                obj.getId(),
                obj.getSoTien(),
                obj.getUid(),
                DateMapper.formatter.format(obj.getNgayCapNhat())
        );
    }
}
