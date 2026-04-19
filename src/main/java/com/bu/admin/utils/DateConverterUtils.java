package com.bu.admin.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
public class DateConverterUtils {

    @Getter
    public enum FormatterType {
        FORMAT1("yyyy-MM-dd HH:mm:ss"),
        FORMAT2("yyyyMMdd HH:mm:ss"),
        FORMAT3("yyyy-MM-dd"),
        FORMAT4("yyyyMMddHHmmss"),

        FORMAT5("yyyyMMdd"),
        FORMAT6("yyyy-MM-dd HH:mm:ss SS"),
        FORMAT7("HH:mm:ss"),
        FORMAT8("dd/MM/yyyy"),

        FORMAT9("dd/MM/yyyy HH:mm:ss"),
        FORMAT10("MM/dd/yy"),
        FORMAT11("yyyy/MM/dd"),
        FORMAT12("MM/dd/yyyy"),

        FORMAT13("MM/dd/yyyy HH:mm:ss"),
        FORMAT14("dd MMMM yyyy"),
        FORMAT15("dd MMMM"),
        FORMAT16("MM/dd/yy HH:mm:ss"),

        FORMAT17("yyyy-MM-dd'T'HH:mm:ss"),
        FORMAT18("yyMMdd"),
        FORMAT19("dd-MMM"), //14-Jul
        FORMAT20("HH:mm"), //14-Jul
        ;

        FormatterType(String desc) {
            this.desc = desc;
        }

        private final String desc;

    }

    @Getter
    public enum DateFormatType {
        FORMAT0("yyyy-MM-dd HH:mm:ss"),
        FORMAT1("yyyy-MM-dd"),
        FORMAT2("yyyy/MM/dd"),
        FORMAT3("yyyyMMdd"),
        FORMAT4("MM/dd/yy"),
        FORMAT5("yyyy-M-dd"),
        FORMAT6("yyyy/M/dd"),
        FORMAT7("yyyy/M/d"),
        FORMAT8("dd/MM/yyyy"),
        FORMAT9("MM/dd/yyyy");

        DateFormatType(String desc) {
            this.desc = desc;
        }

        private final String desc;

    }

    public static LocalDate getStringDateAllFormat(String dateStr) {
        DateFormatType[] dateFormatTypes = DateFormatType.values();
        for (DateFormatType d : dateFormatTypes) {
            LocalDate date = formatStrToLocalDate(dateStr, d);
            if (null != date) {
                return date;
            }
        }
        return null;
    }

    public static LocalDate formatStrToLocalDate(String localDateStr, DateFormatType... dateFormatType) {
        LocalDate localDate = null;
        for (DateFormatType formatType : dateFormatType) {
            try {
                localDate = LocalDate.parse(localDateStr, DateTimeFormatter.ofPattern(formatType.getDesc()));
            } catch (Exception e) {
                //do nothing
            }
        }
        return localDate;
    }
}
