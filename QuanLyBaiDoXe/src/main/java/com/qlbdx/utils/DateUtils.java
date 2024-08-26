/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.utils;

/**
 *
 * @author tuanc
 */
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    // Define the MySQL-compatible date-time pattern
    private static final String MYSQL_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    // Create a DateTimeFormatter for MySQL
    private static final DateTimeFormatter mysqlDateTimeFormatter = DateTimeFormatter.ofPattern(MYSQL_DATE_TIME_PATTERN);

    /**
     * Parses a MySQL-compatible date-time string into LocalDateTime
     *
     * @param dateTimeString the date-time string to parse
     * @return LocalDateTime or null if the string is invalid
     */
    public static String formatToMySQLDateTimeString(Date date) {
        if (date == null) {
            return null;
        }
        // Convert Date to LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        // Format LocalDateTime to string
        return localDateTime.format(mysqlDateTimeFormatter);
    }

    public static Date parseDateTime(String dateTimeString) {
         System.out.println("datetimes"+dateTimeString);

       Date date = (Date) mysqlDateTimeFormatter.parse(dateTimeString);
        System.out.println("date"+date.toString());
        return date;
    }

}
