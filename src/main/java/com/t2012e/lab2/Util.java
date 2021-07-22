package com.t2012e.lab2;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {
    public static LocalDate stringToDate(String date){
        return LocalDate.parse(date,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
