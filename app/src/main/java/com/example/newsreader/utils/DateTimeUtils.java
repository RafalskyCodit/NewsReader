package com.example.newsreader.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTimeUtils {
    public static String convertToNewFormat(String dateStr) throws ParseException {
        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        SimpleDateFormat destFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        sourceFormat.setTimeZone(utc);
        Date convertedDate = sourceFormat.parse(dateStr);
        return destFormat.format(convertedDate);
    }
}
