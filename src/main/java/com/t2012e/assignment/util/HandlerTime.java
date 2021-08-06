package com.t2012e.assignment.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HandlerTime {
    public static String convertDateToString(LocalDate time){
        return time.toString();
    }

    public static LocalDate convertStringToDate(String time){
        return LocalDate.parse(time);
    }
}
