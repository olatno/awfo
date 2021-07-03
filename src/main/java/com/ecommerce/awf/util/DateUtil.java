package com.ecommerce.awf.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DateUtil {

    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static String formatDateToString(LocalDate localDate){
        return localDate.format(formatter);
    }
}
