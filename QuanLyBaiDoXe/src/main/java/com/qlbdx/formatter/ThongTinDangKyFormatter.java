/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.formatter;

import com.qlbdx.pojo.Thongtindangky;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author quang
 */
public class ThongTinDangKyFormatter implements Formatter<Thongtindangky>{
     @Override
    public String print(Thongtindangky object, Locale locale) {
        return String.valueOf(object.getId());
    }

    @Override
    public Thongtindangky parse(String text, Locale locale) throws ParseException {
        Thongtindangky c = new Thongtindangky();
        c.setId(Long.parseLong(text));
        return c;
    }
}
