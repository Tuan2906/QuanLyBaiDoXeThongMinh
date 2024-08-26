/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.formatter;

/**
 *
 * @author tuanc
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import org.springframework.format.Formatter;

public class DateFormatter  implements Formatter<Date> {

    // Define the MySQL-compatible date-time pattern
    private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);

    /**
     * Parses a MySQL-compatible date-time string into LocalDateTime
     *
     * @param dateTimeString the date-time string to parse
     * @return LocalDateTime or null if the string is invalid
     */
  
    @Override
    public String print(Date object, Locale locale) {
                return dateFormat.format(object);

    }

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
               return dateFormat.parse(text);
    }
    
    
}

