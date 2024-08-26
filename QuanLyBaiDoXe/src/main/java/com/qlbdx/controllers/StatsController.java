/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.controllers;

import com.qlbdx.service.StatsService;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author quang
 */
@Controller
public class StatsController {

    @Autowired
    private StatsService statsSer;

    @RequestMapping("/stats")
    public String Stats(Model model, @RequestParam(required = false) String month) {
        if (month == null){
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            month = String.valueOf(localDate.getMonthValue());
        }
        System.out.println(month);
        model.addAttribute("stats", this.statsSer.stats(Integer.parseInt(month)));
        return "stats";
    }
}
