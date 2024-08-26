/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlbdx.repository;

import com.qlbdx.pojo.Baidoxe;
import com.qlbdx.pojo.Rating;
import com.qlbdx.pojo.User;
import com.qlbdx.pojo.Xe;
import java.util.List;
import java.util.Map;

/**
 *
 * @author quang
 */
public interface RatingRepository {

   List<Rating> getRatingsByBaidoxeId(Long baidoxeId, Map<String, Object> filterParams);

    void add_or_update(Rating rate);

}
