// Copyright 2019 Mobvoi Inc. All Rights Reserved.

package com.mdl.home.util;

import static java.time.temporal.ChronoUnit.DAYS;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class DateUtil {

  public static final String DEFAULT_TIME_ZONE = "UTC+8";
  public static final String DEFAULT_OFFSET = "+8";
  public static final DateTimeFormatter YEAR_MONTH_FORMATTER = DateTimeFormatter
      .ofPattern("yyyyMM");
  public static final DateTimeFormatter YEAR_MONTH_DATE_FORMATTER = DateTimeFormatter
      .ofPattern("yyyyMMdd");
  public static final DateTimeFormatter DATA_TIME_FORMATTER = DateTimeFormatter
      .ofPattern("yyyy-MM-dd HH:mm:ss");
  public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
  public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";


  /**
   * 获取当月开始时间戳.
   */
  public static long getCurrentMonthStartMs(long ms) {
    Instant instant = Instant.ofEpochMilli(ms);
    LocalDateTime localDateTime = instant.atZone(ZoneId.of(DEFAULT_TIME_ZONE)).toLocalDateTime();
    LocalDateTime theDateTime = LocalDateTime.of(localDateTime.getYear(),
        localDateTime.getMonthValue(), 1, 0, 0, 0, 0);
    return theDateTime.toInstant(ZoneOffset.of(DEFAULT_OFFSET)).toEpochMilli();
  }

  /**
   * 获取几个月之前开始时间戳.
   */
  public static long getMonthBeforeStartMs(long ms, int monthBefore) {
    Instant instant = Instant.ofEpochMilli(ms);
    LocalDateTime localDateTime = instant.atZone(ZoneId.of(DEFAULT_TIME_ZONE)).toLocalDateTime();
    localDateTime = localDateTime.minusMonths(monthBefore);
    LocalDateTime theDateTime = LocalDateTime.of(localDateTime.getYear(),
        localDateTime.getMonthValue(), 1, 0, 0, 0, 0);
    return theDateTime.toInstant(ZoneOffset.of(DEFAULT_OFFSET)).toEpochMilli();
  }

  /**
   * 月维度.
   */
  public static String getDimension(long ms) {
    Instant instant = Instant.ofEpochMilli(ms);
    LocalDateTime localDateTime = instant.atZone(ZoneId.of(DEFAULT_TIME_ZONE)).toLocalDateTime();
    int monthValue = localDateTime.getMonthValue();
    if (String.valueOf(monthValue).length() == 1) {
      return localDateTime.getYear() + "_0" + monthValue;
    }
    return localDateTime.getYear() + "_" + monthValue;
  }

  /**
   * 获取当日0点时间戳.
   */
  public static long getDayStartMs(long ms) {
    Instant instant = Instant.ofEpochMilli(ms);
    LocalDateTime localDateTime = instant.atZone(ZoneId.of(DEFAULT_TIME_ZONE)).toLocalDateTime();
    LocalDateTime theDateTime = LocalDateTime.of(localDateTime.getYear(),
        localDateTime.getMonthValue(), localDateTime.getDayOfMonth(), 0, 0, 0, 0);
    return theDateTime.toInstant(ZoneOffset.of(DEFAULT_OFFSET)).toEpochMilli();
  }

  /**
   * 获取某月第几天.
   */
  public static String getDayOfMonth(long ms) {
    Instant instant = Instant.ofEpochMilli(ms);
    ZoneId zone = ZoneId.of(DEFAULT_TIME_ZONE);
    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd");
    return localDateTime.format(dateTimeFormatter);
  }

  /**
   * 获取日期.
   */
  public static String getDate(long ms, DateTimeFormatter dateTimeFormatter) {
    Instant instant = Instant.ofEpochMilli(ms);
    ZoneId zone = ZoneId.of(DEFAULT_TIME_ZONE);
    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
    return localDateTime.format(dateTimeFormatter);
  }


  /**
   * 根据"yyyy-MM-dd" 字符串查询两个日期间的天数差
   */
  public static long getBetweenDayByStr(String startDateStr, String endDateStr) {
    LocalDate startDay = strToLocalDate(startDateStr);
    LocalDate endDay = strToLocalDate(endDateStr);
    return getBetweenDayByLocalDate(startDay, endDay);
  }


  /**
   * 根据LocalDate查询两个日期间的天数差
   */
  public static long getBetweenDayByLocalDate(LocalDate startDay, LocalDate endDay) {
    int startDay_year = startDay.getYear();
    Month startDay_month = startDay.getMonth();
    int startDay_day = startDay.getDayOfMonth();
    int endDay_year = endDay.getYear();
    Month endDay_month = endDay.getMonth();
    int endDay_day = endDay.getDayOfMonth();
    LocalDate startDate = LocalDate.of(startDay_year, startDay_month, startDay_day);
    LocalDate endDayDate = LocalDate.of(endDay_year, endDay_month, endDay_day);
    return DAYS.between(startDate, endDayDate);
  }


  /**
   * 当前天数加n天
   */
  public static LocalDate getOffsetDay(LocalDate date, int n) {
    return date.plusDays(n);
  }

  /**
   * LocalDate 转字符串
   */
  public static String localDateToStr(LocalDate date) {
    return date.format(DATE_FORMATTER);
  }

  /**
   * yyyy-MM-dd字符串转LocalDate
   */
  public static LocalDate strToLocalDate(String month) {
    return LocalDate.parse(month, DATE_FORMATTER);
  }

  /**
   * 日期转时间戳
   * @param date
   * @return
   */
  public static long datestrToTimestamp(String date){
    LocalDate localDate = strToLocalDate(date);
    return localDateToTimestamp(localDate);
  }

  /**
   * localdate 转 timestamp
   * @param localDate
   * @return
   */
  public static long localDateToTimestamp(LocalDate localDate){
    return localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli();
  }


  /**
   * 查询一个月的第一天
   */
  public static LocalDate getFirstDayOfMonth(String month) {
    LocalDate date = LocalDate.parse(month, DATE_FORMATTER);
    LocalDate firstday = date.with(TemporalAdjusters.firstDayOfMonth());
    System.out.println("当月第一天:" + firstday);
    return firstday;
  }

  /**
   * 查询一个月的最后一天
   */
  public static LocalDate getLastDayOfMonth(String month) {
    LocalDate date = LocalDate.parse(month, DATE_FORMATTER);
    LocalDate lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
    System.out.println("当月最后一天:" + lastDay);
    return lastDay;
  }


  /**
   * 查询某个月的每个星期数据
   */
  public static List<WeekRange> getWeekOfMonths(String month) {
    LocalDate firstday = getFirstDayOfMonth(month);
    LocalDate lastDay = getLastDayOfMonth(month);
    return getMonthWeeks(firstday, lastDay);
  }

  /**
   * 查询几月到几月的每个星期数据
   */
  public static List<WeekRange> getWeekOfMonths(String startMonth, String endMonth) {
    LocalDate firstday = getFirstDayOfMonth(startMonth);
    LocalDate lastDay = getLastDayOfMonth(endMonth);
    return getMonthWeeks(firstday, lastDay);
  }


  /**
   * 所在周的周一
   */
  public static LocalDate getFirstDayForWeek(String date){
    LocalDate inputDate = LocalDate.parse(date);
    return getFirstDayForWeek(inputDate);
  }

  public static LocalDate getFirstDayForWeek(LocalDate date){
    return date.with(DayOfWeek.MONDAY);
  }

  /**
   * 计算日期在当月是第几周
   * 自然周(每月第一个周一为该月第一周)
   * @param date
   * @return
   */
  public static int getWeekNumByFirstMonday(String date) {
    LocalDate inputDate = LocalDate.parse(date);
    return getWeekNumByFirstMonday(inputDate);
  }

  /**
   * Date转换为LocalDateTime
   */
  public static LocalDateTime convertDateToLDT(long dateL) {
    Date date = new Date(dateL);
    return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
  }


  /**
   * 计算日期在当月是第几周
   * 自然周(每月第一个周一为该月第一周)
   * @param localDate
   * @return
   */
  public static int getWeekNumByFirstMonday(LocalDate localDate) {
    // 获得当前日期的所在周的周一(previousOrSame:如果当前日期是周一，就返回当前日期)
    LocalDate localDateMondy = localDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    LocalDate firstMonday = null;
    // 获得这个月的第一个周一(这个月的第一周)
    for (int day = 1; day <= 7; day++) {
      int year = localDate.getYear();
      int monthValue = localDate.getMonthValue();
      DayOfWeek dayOfWeek = LocalDate.of(year, monthValue, day).getDayOfWeek();
      if (DayOfWeek.MONDAY == dayOfWeek) {
        firstMonday = LocalDate.of(year, monthValue, day);
        break;
      }
    }
    int weekNum;
    // 根据两个周一判断是这个月的第几周
    if (firstMonday.isBefore(localDateMondy) || firstMonday.isEqual(localDateMondy)) {
      //a. 如果当月第一个周一小于等于当前日期所在的周一
      Long weekNumL = (localDateMondy.toEpochDay() - firstMonday.toEpochDay()) / 7 + 1;
      weekNum = weekNumL.intValue();
      return weekNum;
    } else {
      //b. 如果当月第一个周一比当前日期所在的周一还要大
      // 计算上一个月最后一天所在周
      LocalDate lastMonthDate = localDate.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
      return getWeekNumByFirstMonday(lastMonthDate);
    }
  }


  /**
   * 查询两个日期之间的月份
   * @param startMonth
   * @param endMonth
   * @return
   */
  public static List<String> getBetweenMonth(String startMonth,String endMonth) {
    List<String> list = new ArrayList<>();
    LocalDate startDate = LocalDate.parse(startMonth);
    LocalDate endDate = LocalDate.parse(endMonth);
    long distance = ChronoUnit.MONTHS.between(startDate, endDate);
    Stream.iterate(startDate, d -> d.plusMonths(1)).limit(distance + 1).forEach(f -> {
      list.add(f.toString());
    });
    return list;
  }

  /**
   * 所在周的周天
   */
  public static LocalDate getLastDayForWeek(String date){
    LocalDate inputDate = LocalDate.parse(date);
    return getLastDayForWeek(inputDate);
  }

  public static LocalDate getLastDayForWeek(LocalDate date){
    return date.with(DayOfWeek.SUNDAY);
  }


  /**
   * 获取当天周所在的月的是第几周
   * 一号所在的周为第一周
   * @param date
   * @return
   */
  public static int getWeekRankForMonth(String date){
    LocalDate inputDate = LocalDate.parse(date);
    return getWeekRankForMonth(inputDate);
  }

  public static int getWeekRankForMonth(LocalDate date){
    WeekFields weekFields = WeekFields.ISO;
    return date.get(weekFields.weekOfMonth());
  }


  /**
   * 获取当天日期字符串
   * @return
   */
  public static String getCurrDateStr(){
    LocalDate inputDate = LocalDate.now();
    return localDateToStr(inputDate);
  }

  /**
   * 获取昨天的日期字符串
   * @return
   */
  public static String getYesterDayDateStr(){
    LocalDate localDate = LocalDate.now().minusDays(1);
    return localDateToStr(localDate);
  }

  /**
   * 获取明天的日期的字符串
   * @return
   */
  public static String getTomorrowDateStr(){
    LocalDate localDate = LocalDate.now().plusDays(1);
    return localDateToStr(localDate);
  }

  public static String getFirstDayForLastWeek(){
    LocalDate localDate = LocalDate.now().minusDays(7);
    return getFirstDayForLastWeek(localDate);
  }

  public static String getLastDayForLastWeek(){
    LocalDate localDate = LocalDate.now().plusDays(7);
    return getLastDayForLastWeek(localDate);
  }


  public static String getFirstDayForLastWeek(LocalDate inputDate){
    LocalDate firstDayForWeek = getFirstDayForWeek(inputDate);
    return localDateToStr(firstDayForWeek);
  }

  public static String getLastDayForLastWeek(LocalDate inputDate){
    LocalDate lastDayForWeek = getLastDayForWeek(inputDate);
    return localDateToStr(lastDayForWeek);
  }


  /**
   * 把long 转换成 日期 再转换成String类型
   */
  public static String transferLongToDate(String dateFormat, Long millSec) {
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    Date date = new Date(millSec);
    return sdf.format(date);
  }

  public static List<WeekRange> getMonthWeeks(LocalDate firstday, LocalDate lastDay) {
    long days = getBetweenDayByLocalDate(firstday, lastDay) + 1;
    List<WeekRange> result = new ArrayList<>();
    int weekNum = 0;

    List<String> dateStr = new ArrayList<>();
    for (int i = 0; i < days; i++) {
      LocalDate startOffsetDay = getOffsetDay(firstday, i);
      int dayOfWeek = startOffsetDay.getDayOfWeek().getValue();
      if (dayOfWeek == 1) {
        weekNum++;
        dateStr = new ArrayList<>();
      }
      dateStr.add(localDateToStr(startOffsetDay));
      if (dayOfWeek == 7 && dateStr.size() == 7) {
        WeekRange weekRange = new WeekRange(weekNum,dateStr);
        result.add(weekRange);
      }
    }

    if (dateStr.size() > 0) {
      for (int i = 1; i <= 7; i++) {
        LocalDate endOffsetDay = getOffsetDay(lastDay, i);
        dateStr.add(localDateToStr(endOffsetDay));
        if (dateStr.size() == 7) {
          WeekRange weekRange = new WeekRange(weekNum,dateStr);
          result.add(weekRange);
          break;
        }
      }
    }

    return result;
  }


  public static class WeekRange{

    Integer weekNum;
    List<String> date;

    public WeekRange() {
    }

    public WeekRange(Integer weekNum,List<String> date) {
      this.weekNum = weekNum;
      this.date = date;
    }

    public List<String> getDate() {
      return date;
    }

    public void setDate(List<String> date) {
      this.date = date;
    }

    public int getWeekNum() {
      return weekNum;
    }

    public void setWeekNum(Integer weekNum) {
      this.weekNum = weekNum;
    }
  }


}
