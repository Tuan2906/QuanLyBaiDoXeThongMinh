/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.Mapper;

import com.qlbdx.dto.BaiDoPic_DTO;

/**
 *
 * @author quang
 */
public class PictureMapper {

    public static BaiDoPic_DTO toDTO(Object[] obj) {

        System.out.println("_---------00000000000000000000000000000");

        System.out.println(obj[0]);
        // Chuyển đổi User entity sang UserDTO
        BaiDoPic_DTO s = new BaiDoPic_DTO(
                (Long) obj[1],
                String.valueOf(obj[0])
        );
        System.out.println(s.getId());
        System.out.println(s.getNamePic());
        return s;
    }
}
