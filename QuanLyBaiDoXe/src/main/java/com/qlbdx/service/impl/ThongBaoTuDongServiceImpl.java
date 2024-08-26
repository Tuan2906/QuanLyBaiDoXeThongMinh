/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.service.impl;

import com.qlbdx.pojo.Thongtindangky;
import com.qlbdx.repository.ThongTinDangKiRepository;
import com.qlbdx.service.SendEmailService;
import com.qlbdx.service.ThongBaoThoiGianDoTuDong;
import com.qlbdx.utils.DateUtils;
import java.io.IOException;
import static java.time.LocalDate.now;
import static java.time.LocalDateTime.now;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author admin
 */
@Service
public class ThongBaoTuDongServiceImpl implements ThongBaoThoiGianDoTuDong {

    @Autowired
    private ThongTinDangKiRepository thongTinDangKiRepository;

    @Autowired
    private SendEmailService emailService;

    private Map<Long, Date> notifiedVehicles = new HashMap<>(); // Theo dõi thời gian đã thông báo cho từng xe

    @Override
    public void performScheduledTask2(List<Thongtindangky> overdueRegistrations) {
        if (!isWithinActiveHours()) {
            return; // Nếu không trong giờ hoạt động, thoát khỏi phương thức
        }
        Date currentDate = new Date(); // Thời gian hiện tại
        for (Thongtindangky registration : overdueRegistrations) {
            Long vehicleId = registration.getXeId().getId();
            Date raBaiTime = registration.getThoiGianRaBai();

            // Chỉ gửi email nếu chưa gửi thông báo cho xe này hoặc nếu thời gian thông báo đã hết hạn
            if (!notifiedVehicles.containsKey(vehicleId) || notifiedVehicles.get(vehicleId).before(raBaiTime)) {
                String userEmail = registration.getXeId().getUserId().getEmail();
                String subject = "Thông báo về thời gian ra bãi đã hết hạn";
                String text = String.format(
                        "Kính gửi,\n\nXe với biển số %s đã hết thời gian ra bãi từ %s.\nVui lòng kiểm tra và xử lý kịp thời.\n\nTrân trọng.",
                        registration.getXeId().getBienSo(), raBaiTime
                );
                emailService.sendEmail(userEmail, subject, text);

                // Cập nhật thời gian đã thông báo cho xe này
                notifiedVehicles.put(vehicleId, raBaiTime);
            }

        }
    }

    @Override
    @Scheduled(fixedRate = 5000) // Mỗi 5 s để kiểm tra
    public void performScheduledTask() {
        if (!isWithinActiveHours()) {
            return; // Nếu không trong giờ hoạt động, thoát khỏi phương thức
        }
        Date currentDate = new Date(); // Thời gian hiện tại
        List<Thongtindangky> overdueRegistrations = thongTinDangKiRepository.findByThoiGianRaBaiBefore();
        for (Thongtindangky registration : overdueRegistrations) {
            Long vehicleId = registration.getXeId().getId();
            Date raBaiTime = registration.getThoiGianRaBai();

            // Chỉ gửi email nếu chưa gửi thông báo cho xe này hoặc nếu thời gian thông báo đã hết hạn
            if (!notifiedVehicles.containsKey(vehicleId) || notifiedVehicles.get(vehicleId).before(raBaiTime)) {
                String userEmail = registration.getXeId().getUserId().getEmail();
                String subject = "Thông báo về thời gian ra bãi đã hết hạn";
                String text = String.format(
                        "Kính gửi,\n\nXe với biển số %s đã hết thời gian ra bãi từ %s.\nVui lòng kiểm tra và xử lý kịp thời.\n\nTrân trọng.",
                        registration.getXeId().getBienSo(), raBaiTime
                );
                emailService.sendEmail(userEmail, subject, text);

                // Cập nhật thời gian đã thông báo cho xe này
                notifiedVehicles.put(vehicleId, raBaiTime);
            }

        }
    }

    private boolean isWithinActiveHours() {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        return hour >= 7 && hour < 21; // Kiểm tra nếu thời gian hiện tại nằm trong khoảng từ 7 giờ sáng đến 9 giờ tối
    }

    @Override
    @Scheduled(cron = "0 0 2 * * *") // 2:00 AM hàng ngày
    public void performDailyTask() {
        System.out.println("Daily task executed at 2:00 AM");
        // Thực hiện nhiệm vụ hàng ngày của bạn ở đây
    }
}
