/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.controllers;

import com.qlbdx.dto.LichSuDangKyChoDoDTO;
import com.qlbdx.dto.RatingDTO;
import com.qlbdx.dto.ThongTinDangKyDTO;
import com.qlbdx.service.RatingService;
import com.qlbdx.service.ThongTinDangKyService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tuanc
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiRatingBaiDoXe {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/rating/baidoxe/{baidoxeId}")
    public ResponseEntity<List<RatingDTO>> getRatingsByBaidoxeId(
            @PathVariable Long baidoxeId,
            @RequestParam Map<String, Object> params) {
        List<RatingDTO> ratingDTOs = ratingService.getRatingsByBaidoxeId(baidoxeId, params);
        return ResponseEntity.ok(ratingDTOs);
    }

    @PostMapping("/rating/baidoxe")
    public  ResponseEntity<String> saveRating(
            @RequestBody RatingDTO ratingDTO) {

        // Gán `baidoxeId` từ đường dẫn vào `ratingDTO`

        // Gọi service để lưu `Rating`
         ratingService.add_or_update(ratingDTO);

        // Trả về phản hồi với trạng thái thành công
        return ResponseEntity.ok("Lưu đánh giá thành công");
    }
}
