/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.formatter;

import com.qlbdx.pojo.Baidoxe;
import com.qlbdx.pojo.Gia;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author quang
 */
public class GiaFormatter implements Formatter<Gia> {

    @Override
    public String print(Gia object, Locale locale) {
        return String.valueOf(object);
    }

    @Override
    public Gia parse(String text, Locale locale) throws ParseException {
        Gia gia= new Gia();
        gia.setId(Integer.valueOf(text));
        return gia;
    }

    

}
