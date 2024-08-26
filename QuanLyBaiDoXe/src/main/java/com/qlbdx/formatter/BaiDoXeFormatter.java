/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.formatter;

import com.qlbdx.pojo.Baidoxe;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author quang
 */
public class BaiDoXeFormatter implements Formatter<Baidoxe> {

    @Override
    public String print(Baidoxe object, Locale locale) {
        return String.valueOf(object.getId());
    }

    @Override
    public Baidoxe parse(String text, Locale locale) throws ParseException {
        Baidoxe c = new Baidoxe();
        c.setId(Integer.parseInt(text));
        return c;
    }

}
