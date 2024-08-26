/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.Mapper;

import com.qlbdx.dto.ChoDoDTO;
import com.qlbdx.pojo.Chodo;

/**
 *
 * @author quang
 */
public class ChoDoMapper {
    public static ChoDoDTO toDTO(Chodo b) {
        if (b == null) {
            return null;
        }

        return new ChoDoDTO(
                b.getId(),
                b.getVitri(),
                b.getKhoangCach(),
                b.getStateId().getLoai()
               
        );
    }
      public static ChoDoDTO toDTO(Object[] obj) {
        return new ChoDoDTO(
            (Long) obj[0],      // id
            (Integer) obj[1],    // vitri
            (Double) obj[2],    // khoangCach
            (String) obj[3]     // stateLoai
        );
      }
}
