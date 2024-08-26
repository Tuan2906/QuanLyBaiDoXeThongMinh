/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.formatter;

import com.qlbdx.pojo.Chodo;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author quang
 */
public class ChoDoFormatter implements Formatter<Chodo> {

    @Override
    public String print(Chodo object, Locale locale) {
        return String.valueOf(object.getId());
    }

    @Override
    public Chodo parse(String text, Locale locale) throws ParseException {
        Chodo c = new Chodo();
        c.setId(Long.parseLong(text));
        return c;
    }

}
