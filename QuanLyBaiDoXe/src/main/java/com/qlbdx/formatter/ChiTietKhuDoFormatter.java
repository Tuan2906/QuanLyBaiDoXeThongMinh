package com.qlbdx.formatter;

import com.qlbdx.pojo.Chitietkhudo;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author quang
 */
public class ChiTietKhuDoFormatter implements Formatter<Chitietkhudo> {

    @Override
    public String print(Chitietkhudo object, Locale locale) {
        return String.valueOf(object.getId());
    }

    @Override
    public Chitietkhudo parse(String text, Locale locale) throws ParseException {
        Chitietkhudo c = new Chitietkhudo();
        c.setId(Integer.parseInt(text));
        return c;
    }

}
