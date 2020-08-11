package com.tignioj.studentmanagement.gui.utils;

import javafx.util.StringConverter;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
    private static final String DEFAULT_DATE_FORMAT = "yyyy年MM月dd日";

    //datepicker 的格式化工具
    public static StringConverter stringConverter = new StringConverter<LocalDate>() {
        private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);

        @Override
        public String toString(LocalDate localDate) {
            if (localDate == null)
                return "";
            return dateTimeFormatter.format(localDate);
        }

        @Override
        public LocalDate fromString(String dateString) {
            if (dateString == null || dateString.trim().isEmpty()) {
                return null;
            }
            return LocalDate.parse(dateString, dateTimeFormatter);
        }
    };

    //为TreeView格式化Date用
//    private static final DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.CHINA);
    private static final DateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
    public static StringConverter<Date> stringConverterForTreeView =  new StringConverter<Date>() {
        @Override
        public String toString(Date t) {
            return df.format(t);
        }

        @Override
        public Date fromString(String string) {
            try {
                Date parse = df.parse(string);
                return parse;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }
    };


    public static String format(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String format(Date date) {
        return new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(date);
    }

    @Test
    public void test1() {
        System.out.println(new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(new Date()));
    }
}
