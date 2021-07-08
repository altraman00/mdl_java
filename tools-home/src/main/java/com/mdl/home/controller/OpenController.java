package com.mdl.home.controller;

import com.mdl.home.service.UserToolsCategorySiteService;
import com.mdl.home.vo.ToolsSiteVO;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.home.controller
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年01月07日 23:31
 * ----------------- ----------------- -----------------
 */

@RequestMapping("/open")
@RestController
public class OpenController {

  public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  public static final DateTimeFormatter DATE_FORMATTER_YM = DateTimeFormatter.ofPattern("yyyy-MM");

  private static final String GMT_PREFIX = "GMT";

  private static final double BASE_RANK_SCORE_BASE = 100000000.0d;
  //高位100+010=110=国内
  private static final double BASE_RANK_SCORE_HOME = 010000000.0d;
  //高位100+020=120=国外
  private static final double BASE_RANK_SCORE_FOREIGN = 020000000.0d;
  //高位100+001=101=男
  private static final double BASE_RANK_SCORE_MAN = 001000000.0d;
  //告我100+002=102=女
  private static final double BASE_RANK_SCORE_WOMAN = 002000000.0d;

  @Autowired
  private UserToolsCategorySiteService siteService;

  @RequestMapping("/site/{username}")
  public Map<String, List<ToolsSiteVO>> home(
      @RequestParam(value = "username", required = false, defaultValue = "admin") String userId) {
    Map<String, List<ToolsSiteVO>> siteList = siteService.findByUsername(userId);
    return siteList;
  }


  public static void main(String[] args) {
//    String date = "2021-04-08";
//    String month = "2021-04-01";

//    List<DateUtil.WeekRange> weekOfMonths = DateUtil.getWeekOfMonths(month);
//    String result = JSONUtil.toJsonStr(weekOfMonths);
//    System.out.println(result);

//    LocalDate inputDate = LocalDate.parse(date);
//    // 所在周开始时间
//    LocalDate beginDayOfWeek = inputDate.with(DayOfWeek.MONDAY);
//    // 所在周结束时间
//    LocalDate endDayOfWeek = inputDate.with(DayOfWeek.SUNDAY);
//
//    System.out.println(beginDayOfWeek);
//    System.out.println(endDayOfWeek);

//    String currDateStr = DateUtil.getCurrDateStr();
//    String yesterDayDateStr = DateUtil.getYesterDayDateStr();
//    String tomorrowDateStr = DateUtil.getTomorrowDateStr();
//
//    System.out.println(yesterDayDateStr);
//    System.out.println(currDateStr);
//    System.out.println(tomorrowDateStr);

//    int weekRankForMonth = DateUtil.getWeekRankForMonth(date);
//    System.out.println("date:" + date + "---weekNum:" + weekRankForMonth);
//
//    Calendar calendar = Calendar.getInstance();
//    calendar.setTime(new Date());
//    int i = calendar.get(Calendar.WEEK_OF_MONTH);
//    System.out.println("date:" + date + "---weekNum:" + i);
//
//    LocalDate inputDate = LocalDate.parse(date);
//    WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 1);
//    int i1 = inputDate.get(weekFields.weekOfMonth());
//    System.out.println("date:" + date + "---weekNum:" + i1);
//
//    int weekOfMonthByDay = DateUtil.getWeekNumByFirstMonday(date);
//    System.out.println("date:" + date + "---weekNum:" + weekOfMonthByDay);

//    String start = "2020-03-01";
//    String end = "2021-02-01";
//    List<String> betweenMonth = DateUtil.getBetweenMonth(start, end);
//    System.out.println(JSONUtil.toJsonStr(betweenMonth));

//    String substring = month.substring(0, 7);
//    System.out.println(substring);

//    // 获取本次计算的时区.
//    int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
//    String timeZone = getTimeZoneAtTwoAm(hour);
//    long currTime = System.currentTimeMillis();
//    long dayStartTimeByZone = getDayStartTimeByZone(currTime, timeZone);
//    System.out.println("curr region hour=" + hour
//        + "--->timeZone=" + timeZone
//        + "--->timestamp=" + currTime
//        + "--->dayStartTimeByZone=" + dayStartTimeByZone);
//
//    LocalDate startDate = LocalDate.now();
//    // +8是北京时间
//    Long minTime = LocalDateTime.of(startDate, LocalTime.MIN).toInstant(ZoneOffset.of("+8")).toEpochMilli();
//    Long maxTime = LocalDateTime.of(startDate, LocalTime.MAX).toInstant(ZoneOffset.of("+8")).toEpochMilli();
//    System.out.println(minTime);
//    System.out.println(maxTime);

//    double b = 9.723456;
//    String c = b+"";
//    String d = c.substring(c.indexOf(".")+1,c.length());
//    System.out.println(d);

//    Date date=new Date(1620462348169L);
//    SimpleDateFormat sdf8=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    sdf8.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));//设置时区为东八区
//    System.out.println("东八区的时间:"+sdf8.format(date));//输出格式化日期
//
//    SimpleDateFormat sdf16=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    sdf16.setTimeZone(TimeZone.getTimeZone("GMT+16:00"));//设置时区为东八区
//    System.out.println("东十六区的时间:"+sdf16.format(date));//输出格式化日期
//
//
//    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    System.out.println("默认时区的时间:"+sdf.format(date));//输出格式化日期

//    long aa = 884310656;
////    long l = TimeUnit.MILLISECONDS.toSeconds(aa);
////    System.out.println(l);
//
//    long l1 = TimeUnit.SECONDS.toMillis(aa);
//    System.out.println(l1);

    long startAt = 1605307121191L;
    long YEAR_IN_MS = TimeUnit.DAYS.toMillis(365);
    System.out.println(YEAR_IN_MS);
    System.out.println(startAt - YEAR_IN_MS);


  }

  public static long getDayStartTimeByZone(long ms, String timeZone) {
    Date date = new Date(ms);
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
    // 设置那天时间00：00.
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTimeInMillis();
  }

  public static String getTimeZoneAtTwoAm(int hour) {
    String timeZone = "GMT+8";
    if (hour > 22 && hour <= 24) {
      hour = 34 - hour;
      return GMT_PREFIX + "+" + hour;
    }
    if (hour > 0 && hour <= 22) {
      hour = 10 - hour;
      return hour < 0 ? GMT_PREFIX + hour : GMT_PREFIX + "+" + hour;
    }
    return timeZone;
  }


}
