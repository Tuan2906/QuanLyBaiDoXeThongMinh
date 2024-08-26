/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.service.impl;

import com.qlbdx.dto.RatingDTO;
import com.qlbdx.pojo.Baidoxe;
import com.qlbdx.pojo.Rating;
import com.qlbdx.pojo.User;
import com.qlbdx.repository.BaiDoXeRepository;
import com.qlbdx.repository.GiaRepository;
import com.qlbdx.repository.RatingRepository;
import com.qlbdx.repository.UserRepository;
import com.qlbdx.repository.XeRepository;
import com.qlbdx.service.BaiDoXeService;
import com.qlbdx.service.GiaService;
import com.qlbdx.service.RatingService;
import com.qlbdx.service.XeService;
import com.qlbdx.utils.DateUtils;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author admin
 */
@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository rateRepo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BaiDoXeRepository baixe;

    private User getCurrentUser() {
//       SecurityContextHolder.getContext().getAuthentication().getName()
        System.out.println("username" + userRepository.getUserByUsername("admin"));
        return userRepository.getUserByUsername("admin"); // Tìm người dùng từ UserRepository
    }

    private RatingDTO convertToDTO(Rating rating) {
        RatingDTO dto = new RatingDTO();
        dto.setId(rating.getId());

        // Lấy thông tin từ các trường của Rating
        dto.setBaidoxeId(rating.getBaiDoid().getId()); // Giả sử đây là cách lấy ID của Baidoxe
        dto.setRate(rating.getRate());

        if (rating.getCreatedDate() != null) {
            dto.setCreatedDate(rating.getCreatedDate());
        }

        dto.setComments(rating.getNhanxet());
        dto.setAvatar(rating.getUserId().getAvatar());
        dto.setUsername(rating.getUserId().getUsername());
        return dto;
    }

    private Rating convertToEntity(RatingDTO rateDTO) {
        if (rateDTO == null) {
            return null;
        }
        if (rateDTO.getRate() == 0) {
            throw new IllegalArgumentException("Đánh giá must not be 0");
        }
        Date currentDate = new Date();
        Timestamp timestamp = new Timestamp(currentDate.getTime());
        Baidoxe bai = new Baidoxe(rateDTO.getBaidoxeId());

        // Tìm User từ cơ sở dữ liệu dựa trên userId
        User user = getCurrentUser();
        boolean active = true;
        System.out.println("user" + user.getId());
        return new Rating(
                timestamp,
                timestamp,
                rateDTO.getComments(),
                active,
                rateDTO.getRate(),
                bai,
                user
        );
    }

    @Override
    public List<RatingDTO> getRatingsByBaidoxeId(Long baidoxeId, Map<String, Object> params) {
        // Lấy danh sách các đánh giá từ repository
        List<Rating> ratings = rateRepo.getRatingsByBaidoxeId(baidoxeId, params);

        // Chuyển đổi các đối tượng Rating thành RatingDTO
        return ratings.stream()
                .map(this::convertToDTO) // Sử dụng ratingMapper để chuyển đổi
                .collect(Collectors.toList());
    }

    @Override
    public void add_or_update(RatingDTO rate) {
        Rating rating = convertToEntity(rate);
        this.rateRepo.add_or_update(rating);
    }
}
