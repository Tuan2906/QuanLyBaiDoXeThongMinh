/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.service;

import com.qlbdx.pojo.Thongtindangky;
import com.qlbdx.pojo.User;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author quang
 */
public interface ThongBaoThoiGianDoTuDong {

    void performScheduledTask();

    void performDailyTask();

    public void performScheduledTask2(List<Thongtindangky> overdueRegistrations) ;

    }
