/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlbdx.service;

import com.qlbdx.dto.RatingDTO;
import com.qlbdx.dto.XeDTO;
import com.qlbdx.pojo.Baidoxe;
import com.qlbdx.pojo.Gia;
import com.qlbdx.pojo.User;
import com.qlbdx.pojo.Xe;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public interface RatingService {

    List<RatingDTO> getRatingsByBaidoxeId(Long baidoxeId, Map<String, Object> params);

    void add_or_update(RatingDTO rate);

}
