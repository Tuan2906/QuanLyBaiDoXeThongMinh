/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.Mapper;

import com.qlbdx.dto.BaidoxeDTO;
import com.qlbdx.pojo.Baidoxe;
import java.text.SimpleDateFormat;

/**
 *
 * @author quang
 */
public class BaidoxeMapper {

    public static BaidoxeDTO toDTO(Baidoxe b) {
        if (b == null) {
            return null;
        }

        // Chuyển đổi User entity sang UserDTO
        return new BaidoxeDTO(
                b.getTenBai(), // Chuyển đổi thuộc tính tenBai
                b.getId(), // Chuyển đổi thuộc tính id
                b.getDiachi(), // Chuyển đổi thuộc tính diachi
                DateMapper.formatter.format(b.getThoigiancua()), // Chuyển đổi thuộc tính thoigiancua
                DateMapper.formatter.format(b.getThoigiandongcua()) // Chuyển đổi thuộc tính thoigiandongcua
        );
    }

    public static BaidoxeDTO toDTOSpecial(Object[] obj) {
        return new BaidoxeDTO(
                (String) obj[4],
                (Integer) obj[3],
                (String) obj[2],
                DateMapper.formatter.format(obj[0]),
                DateMapper.formatter.format(obj[1]),
                (String) obj[5],
                (Double) (obj[6] == null ? 0.0 : obj[6]) // avgRate
        );
    }
}
