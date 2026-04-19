package com.bu.admin.utils;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
public class DateConverterUtils {

    public enum FormatterType {
        FORMAT1("yyyy-MM-dd HH:mm:ss"),
        FORMAT2("yyyyMMdd HH:mm:ss"),
        FORMAT3("yyyy-MM-dd"),
        FORMAT4("yyyyMMddHHmmss"),
        FORMAT5("yyyyMMdd"),
        FORMAT6("yyyy-MM-dd HH:mm:ss SS"),
        FORMAT7("HH:mm:ss"),
        FORMAT8("dd-MMM"),
        ;

        private String code;

        FormatterType(String code) {
            this.code = code;
        }
    }

    /**
     * 转换日期为字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String formatDateToString(Date date, FormatterType format) {
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format.code);
            return dateFormat.format(date);
        } else {
            return "";
        }
    }


    /**
     * 转换字符串为日期
     *
     * @param dateStr
     * @param formatterType
     * @return
     * @throws ParseException
     */
    public static Date formatStringToDate(String dateStr, FormatterType formatterType) {
        if (StringUtils.isNotBlank(dateStr)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(formatterType.code);
            try {
                return dateFormat.parse(dateStr);
            } catch (ParseException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 转换日期为字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String formatCalendarToString(Calendar date, String format) {
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(date.getTime());
        } else {
            return "";
        }
    }

    /**
     * String转Calendar
     */
    public static Calendar formatStringToCalendar(String date, String format)
            throws ParseException {
        if (StringUtils.isBlank(date))
            return null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date dDate = dateFormat.parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dDate);
        return calendar;
    }

    /**
     * String转Calendar
     */
    public static String getCalendarDifference(Date startDate, Date endDate) {
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();
        startTime.setTime(startDate);
        endTime.setTime(endDate);
        long minutes = getMinutesInterval(startTime, endTime);
        long day = (minutes / 60 / 24);
        minutes -= day * 60 * 24;
        long hour = minutes / 60;
        minutes -= hour * 60;
        String result = "";
        if (day > 0) {
            result = String.valueOf(day) + "天";
        }
        if (hour > 0) {
            result += String.valueOf(hour) + "小时";
        }
        result += String.valueOf(minutes) + "分钟";

        return result;
    }

    /**
     * 两个Calendar赋值；
     */
    public static Calendar getCalendar(Calendar calendar) {
        Calendar calendarInfo = Calendar.getInstance();
        calendarInfo.setTime(calendar.getTime());
        return calendarInfo;
    }

    /**
     * 得到日期
     */
    public static Date toStartTimeOfDay(Date datetime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datetime);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date toEndTimeOfDay(Date datetime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datetime);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.MILLISECOND, 59);
        return calendar.getTime();
    }

    public static Calendar getStartTimeOfDay(Calendar calendar) {
        Calendar startTimeOfDay = (Calendar) calendar.clone();
        startTimeOfDay.set(Calendar.HOUR_OF_DAY, 0);
        startTimeOfDay.set(Calendar.SECOND, 0);
        startTimeOfDay.set(Calendar.MINUTE, 0);
        startTimeOfDay.set(Calendar.MILLISECOND, 0);
        return startTimeOfDay;
    }

    /**
     * 判断未来日期B是否与日期A相差整数月
     */
    public static Boolean isSameDayOfMonth(Calendar startDate, Calendar endDate) {
        int startDayOfMonth = startDate.get(Calendar.DAY_OF_MONTH);
        int endDayOfMonth = endDate.get(Calendar.DAY_OF_MONTH);
        int maxDayOfMonth = endDate.getActualMaximum(Calendar.DAY_OF_MONTH);
        int dayOfMonth;
        if (startDayOfMonth < maxDayOfMonth)
            dayOfMonth = startDayOfMonth;
        else
            dayOfMonth = maxDayOfMonth;
        return dayOfMonth == endDayOfMonth;
    }

    public static int getMonthsInterval(Calendar startDate, Calendar endDate) {
        int startMonth = startDate.get(Calendar.YEAR) * 12
                + startDate.get(Calendar.MONTH);
        int endMonth = endDate.get(Calendar.YEAR) * 12
                + endDate.get(Calendar.MONTH);
        return endMonth - startMonth;
    }

    /**
     * 日期累加天数
     *
     * @param startDate
     * @param days
     * @return
     */
    public static Date getDaysInterval(Date startDate, int days) {
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(startDate);
        endCalendar.add(Calendar.DAY_OF_YEAR, days);
        return endCalendar.getTime();
    }

    /**
     * 日期加几个小时
     *
     * @param startDate
     * @param hours
     * @return
     */
    public static Date getHoursAdd(Date startDate, int hours) {
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(startDate);
        endCalendar.add(Calendar.HOUR_OF_DAY, hours);
        return endCalendar.getTime();
    }

    /**
     * 日期加减分钟
     *
     * @param startDate
     * @param minute
     * @return
     */
    public static Date getMinuteAdd(Date startDate, int minute) {
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(startDate);
        endCalendar.add(Calendar.MINUTE, minute);
        return endCalendar.getTime();
    }

    /**
     * 累加减秒
     *
     * @param startDate
     * @param second
     * @return
     */
    public static Date getSecondAdd(Date startDate, int second) {
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(startDate);
        endCalendar.add(Calendar.SECOND, second);
        return endCalendar.getTime();
    }

    public static int getDaysInterval(Calendar startDate, Calendar endDate) {
        long startTime = startDate
                .getTimeInMillis();
        long endTime = endDate.getTimeInMillis();
        return (int) ((endTime - startTime) / (1000 * 60 * 60 * 24));
    }

    public static int getHoursInterval(Calendar startDate, Calendar endDate) {
        long startTime = startDate
                .getTimeInMillis();
        long endTime = endDate.getTimeInMillis();
        return (int) ((endTime - startTime) / (1000 * 60 * 60));
    }

    /**
     * 秒差
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getSecondInterval(Date startDate, Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();
        return diff / 1000;
    }

    public static int getMinutesInterval(Calendar startDate, Calendar endDate) {
        long startTime = startDate
                .getTimeInMillis();
        long endTime = endDate.getTimeInMillis();
        return (int) ((endTime - startTime) / (1000 * 60));
    }

    public static long getMinutesInterval(Date startDate, Date endDate) {
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - startDate.getTime();
        return diff / 1000 / 60;
    }

    public static Boolean isBetweenTimeSpanInDay(Calendar time,
                                                 Calendar startTime, Calendar endTime) {
        long sd = startTime.get(Calendar.YEAR) * 365L
                + startTime.get(Calendar.DAY_OF_YEAR);
        long ed = endTime.get(Calendar.YEAR) * 365L
                + endTime.get(Calendar.DAY_OF_YEAR);
        if (ed > sd) {
            return true;
        } else if (ed < sd) {
            return false;
        } else {
            Calendar calendar = getCalendar(time);
            calendar.set(startTime.get(Calendar.YEAR),
                    startTime.get(Calendar.MONTH), startTime.get(Calendar.DATE));
            long s = startTime.getTimeInMillis();
            long e = endTime.getTimeInMillis();
            long t = calendar.getTimeInMillis();

            return (s <= t && t <= e);
        }
    }


    public static Date getStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getEndTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }


    public static String getYesterdayStartTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(FormatterType.FORMAT1.code);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.MILLISECOND, 0);
        return sdf.format(calendar.getTime());
    }

    public static String getYesterdayEndTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(FormatterType.FORMAT1.code);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        calendar.add(Calendar.DATE, -1);
        return sdf.format(calendar.getTime());
    }

    public static Date getYesterdayStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getYesterdayEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        calendar.add(Calendar.DATE, 0);
        return calendar.getTime();
    }

    public static Date getEndDateOfYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 000);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    public static String getCurrentTime() {
        SimpleDateFormat sdFormatter = new SimpleDateFormat(FormatterType.FORMAT6.code);
        return sdFormatter.format(new Date());
    }

    public static String getTimeOfminute() {
        SimpleDateFormat sdFormatter = new SimpleDateFormat(FormatterType.FORMAT4.code);
        return sdFormatter.format(new Date()).substring(0, 12);
    }

    public static Date string2Date(String string) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(FormatterType.FORMAT1.code);
        return sdf.parse(string);
    }

    public static Date getCustomTime(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hour);
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - minute);
        return calendar.getTime();
    }

    public static Date getMonth(int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - month);
        return calendar.getTime();
    }

    public static Date getDay(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - day);
        return calendar.getTime();
    }

    public static Date strToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat(FormatterType.FORMAT3.code);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date strToTime(String str) {

        SimpleDateFormat format = new SimpleDateFormat(FormatterType.FORMAT7.code);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            log.error("str to time error", e);
        }
        return date;
    }

    public static Date timestapm2Date(String timestapm, FormatterType formats) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(formats.code);
        Long time = Long.parseLong(timestapm) * 1000L;
        String dateStr = format.format(time);
        return format.parse(dateStr);
    }

    public static Date formatTimestampToDate(String timestamp) {
        try {
            return new Date(Long.parseLong(timestamp));
        } catch (Exception e) {
            log.error("formatTimestampToDate error", e);
            return null;
        }
    }

    public static boolean compareTime(String str1, String str2, String formats) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(formats);
        Date dt1 = df.parse(str1);//将字符串转换为date类型
        Date dt2 = df.parse(str2);
        //比较时间大小,如果dt1大于dt2
        return dt1.getTime() >= dt2.getTime();

    }

    public static Date getMonthFirstDay(Date someDate) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(someDate);                            //  someDate 为你要获取的那个月的时间
        ca.set(Calendar.DAY_OF_MONTH, 1);
        return ca.getTime();
    }

    public static Date getMonthLastDay(Date someDate) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(someDate);                            //  someDate 为你要获取的那个月的时间
        ca.set(Calendar.DAY_OF_MONTH, 1);
        ca.add(Calendar.MONTH, 1);
        ca.add(Calendar.DAY_OF_MONTH, -1);
        return ca.getTime();
    }

    //获取某月天数
    public static int getDateDayNum(String year, String month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(year), Integer.parseInt(month), 0); //输入类型为int类型
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取时间段内时间间隔， 月单位
     *
     * @param startDate
     * @param endDate
     * @param interval
     * @return
     * @throws ParseException
     */
    public static Set<String> getIntervalTimeList(Date startDate, Date endDate, int interval) {
        Date monthFirstDay = getMonthFirstDay(startDate);
        Set<String> set = new HashSet<>();
        while (monthFirstDay.getTime() <= endDate.getTime()) {
            set.add(DateConverterUtils.formatDateToString(monthFirstDay, FormatterType.FORMAT1));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(monthFirstDay);
            calendar.add(Calendar.MONTH, interval);
            if (calendar.getTime().getTime() >= endDate.getTime()) {
                if (!monthFirstDay.equals(endDate)) {
                    set.add(DateConverterUtils.formatDateToString(monthFirstDay, FormatterType.FORMAT1));
                }
                monthFirstDay = calendar.getTime();
            } else {
                monthFirstDay = calendar.getTime();
            }

        }
        return set;
    }

    /**
     * 获取月第一天和最后一天
     *
     * @param timeStr
     * @return
     */
    public static JsonObject getStartAndEndMonthTime(String timeStr) throws ParseException {
        JsonObject jsonObject = new JsonObject();
        String firstday;
        String lastday;
        SimpleDateFormat format = new SimpleDateFormat(FormatterType.FORMAT3.code);
        Date parse = format.parse(timeStr);
        Calendar cale;

        // 获取前月的第一天
        cale = Calendar.getInstance();
        cale.setTime(parse);
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        firstday = format.format(cale.getTime());
        // 获取前月的最后一天
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        lastday = format.format(cale.getTime());
        jsonObject.addProperty("firstday", firstday + " 00:00:00");
        jsonObject.addProperty("lastday", lastday + " 23:59:59");
        return jsonObject;

    }

    /**
     * 判断必须大于今天
     *
     * @param timeStr
     * @return
     */
    public static boolean checkDateNotThisDate(String timeStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(FormatterType.FORMAT3.code);
        Date parse = format.parse(timeStr);
        Date nowdate = new Date();

        return parse.after(nowdate);

    }

    /**
     * 获取时间戳
     *
     * @param dateTime
     * @param format
     * @return
     */
    public static long getTime(String dateTime, FormatterType format) {
        Date date = formatStringToDate(dateTime, format);
        if (null != date) {
            return date.getTime();
        } else {
            return 0;
        }
    }

    public static String getWeekOfDate(Date dt) {

        String[] weekDays = {"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    /**
     * 获取某一时间段特定星期几的日期
     *
     * @param dateFrom 开始时间
     * @param dateEnd  结束时间
     * @param weekDays 星期
     * @return 返回时间数组
     */
    public static List<String> getWeekDates(String dateFrom, String dateEnd, String weekDays) {
        long time = 1l;
        long perDayMilSec = 24 * 60 * 60 * 1000L;
        List<String> dateList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat(FormatterType.FORMAT3.code);
        //需要查询的星期系数
        String strWeekNumber = weekForNum(weekDays);
        try {
            dateFrom = sdf.format(sdf.parse(dateFrom).getTime() - perDayMilSec);
            while (true) {
                time = sdf.parse(dateFrom).getTime();
                time = time + perDayMilSec;
                Date date = new Date(time);
                dateFrom = sdf.format(date);
                if (dateFrom.compareTo(dateEnd) <= 0) {
                    //查询的某一时间的星期系数
                    Integer weekDay = dayForWeek(date);
                    //判断当期日期的星期系数是否是需要查询的
                    if (strWeekNumber.indexOf(weekDay.toString()) != -1) {
                        dateList.add(dateFrom);
                    }
                } else {
                    break;
                }
            }
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        String[] dateArray = new String[dateList.size()];
        dateList.toArray(dateArray);
        return dateList;
    }

    //等到当期时间的周系数。星期日：1，星期一：2，星期二：3，星期三：4，星期四：5，星期五：6，星期六：7
    public static Integer dayForWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 得到对应星期的系数  星期日：1，星期一：2，星期二：3，星期三：4，星期四：5，星期五：6，星期六：7
     *
     * @param weekDays 星期格式  星期一|星期二
     */
    public static String weekForNum(String weekDays) {
        //返回结果为组合的星期系数
        StringBuilder weekNumber = new StringBuilder();
        //解析传入的星期
        if (weekDays.indexOf("|") != -1) {//多个星期数
            String[] strWeeks = weekDays.split("\\|");
            for (int i = 0; i < strWeeks.length; i++) {
                weekNumber.append(getWeekNum(strWeeks[i]).toString());
            }
        } else {//一个星期数
            weekNumber.append(getWeekNum(weekDays).toString());
        }

        return weekNumber.toString();

    }

    /**
     * //"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"}
     * //将星期转换为对应的系数  星期日：1，星期一：2，星期二：3，星期三：4，星期四：5，星期五：6，星期六：7
     */
    public static Integer getWeekNum(String strWeek) {
        Integer number = 1;//默认为星期日
        if ("SUNDAY".equals(strWeek)) {
            number = 1;
        } else if ("MONDAY".equals(strWeek)) {
            number = 2;
        } else if ("TUESDAY".equals(strWeek)) {
            number = 3;
        } else if ("WEDNESDAY".equals(strWeek)) {
            number = 4;
        } else if ("THURSDAY".equals(strWeek)) {
            number = 5;
        } else if ("FRIDAY".equals(strWeek)) {
            number = 6;
        } else if ("SATURDAY".equals(strWeek)) {
            number = 7;
        }
        return number;
    }

    /**
     * 获取当前距离明天0点的秒数
     *
     * @return
     */
    public static Long getSecondsNextEarlyMorning() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Long seconds = (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
        return seconds.longValue();
    }

    public static List<String> getDates(String startTime, String endTime) {
        List<String> dateList = new ArrayList<>();
        Date date0 = formatStringToDate(startTime, FormatterType.FORMAT3);
        Date date1 = formatStringToDate(endTime, FormatterType.FORMAT3);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date0);
        while (cal.getTime().compareTo(date1) <= 0) {
            //...
            dateList.add(formatDateToString(cal.getTime(), FormatterType.FORMAT3));
            cal.add(Calendar.DAY_OF_MONTH, 1);

        }
        return dateList;
    }

    /**
     * 获取传入日期(date)的yearCount年后的日期
     *
     * @param date
     * @param yearCount
     * @return
     */
    public static Date getManyYearDate(Date date, int yearCount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, yearCount);
        return cal.getTime();
    }

    /**
     * 判断该日期是否为闰年的2月29号
     *
     * @param date
     * @return
     */
    public static boolean checkEndOfFebInLeapYear(Date date) {
        boolean leapYear = false;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //获取年
        int year = calendar.get(Calendar.YEAR);
        if (isLeapYear(year)) {
            //获取月份，0表示1月份
            String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
            //获取当前天数
            String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

            if ("229".equals(month + day)) {

                leapYear = true;
            }

        }

        return leapYear;
    }

    /**
     * 判断某一年份是否为闰年
     *
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year) {
        boolean leapYear;
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            leapYear = true;
        } else {
            leapYear = false;
        }
        return leapYear;
    }

    /**
     * 获取传入日期(date)的dayCount天后的日期
     *
     * @param date
     * @param dayCount
     * @return
     */
    public static Date getManyDayDate(Date date, int dayCount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, dayCount);
        return cal.getTime();
    }
    public static String formatLocalToString(LocalDate date, FormatterType format) {
        if (date != null) {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(format.code, Locale.ENGLISH);
            return date.format(df);
        } else {
            return "";
        }
    }


}
