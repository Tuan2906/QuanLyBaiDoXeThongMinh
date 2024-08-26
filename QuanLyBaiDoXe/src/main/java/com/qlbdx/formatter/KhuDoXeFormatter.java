/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.formatter;

import com.qlbdx.pojo.Khudoxe;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author quang
 */
public class KhuDoXeFormatter implements Formatter<Khudoxe> {

    @Override
    public String print(Khudoxe object, Locale locale) {
        return String.valueOf(object.getId());
    }

    @Override
    public Khudoxe parse(String text, Locale locale) throws ParseException {
       Khudoxe c = new Khudoxe();
        c.setId(Long.valueOf(text));
        return c;
    }

}
