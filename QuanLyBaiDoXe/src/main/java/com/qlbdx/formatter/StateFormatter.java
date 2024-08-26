/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.formatter;

import com.qlbdx.pojo.State;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author quang
 */
public class StateFormatter implements Formatter<State> {

    @Override
    public String print(State object, Locale locale) {
        return String.valueOf(object.getId());
    }

    @Override
    public State parse(String text, Locale locale) throws ParseException {
        State c = new State();
        c.setId(Long.parseLong(text));
        return c;
    }

}
