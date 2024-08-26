/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.service.impl;

import com.qlbdx.repository.StatsRepository;
import com.qlbdx.service.StatsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author quang
 */
@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    private StatsRepository statsRep;

    @Override
    public List<Object[]> stats(int month) {
        this.statsRep.stats(8).forEach(a -> {
            String diachi = (String) a[0]; // Dia chi
            String tenBai = (String) a[1]; // Ten Bai
            Double tongTien = (Double) a[2]; // Tong Tien
            Long soLuongCho = ((Number) a[3]).longValue(); // So Luong Cho
            System.out.printf("Dia chi: %s, Ten Bai: %s, Tong Tien: %.2f, So Luong Cho: %d\n", diachi, tenBai, tongTien, soLuongCho);
        });
        return this.statsRep.stats(month);
    }
}
