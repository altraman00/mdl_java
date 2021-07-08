package com.mdl.home;

import com.mdl.home.util.DateUtil;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.home
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年05月28日 17:10
 * ----------------- ----------------- -----------------
 */
public class Test {

  public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
      .ofPattern("yyyy-MM-dd HH:mm:ss");

  public static void main(String[] args) {
    String ageStr = "-1549008000.0";
    int age = getAge(ageStr);
    System.out.println(age);
  }


  public static int getAge(String birthdayLStr) {
    int age;
    Float aFloat = Float.valueOf(birthdayLStr);
    int birthdayYear;
    if (aFloat < 0) {
      birthdayLStr = birthdayLStr.replace("-", "");
      Long birthdayL = Long.valueOf(birthdayLStr.split("\\.")[0]) * 1000;
      LocalDateTime birthdayLocalDate = DateUtil.convertDateToLDT(birthdayL);
      int year = birthdayLocalDate.getYear();
      int dur = year - 1970;
      birthdayYear = 1970 - dur;
      int currYear = LocalDate.now().getYear();
      age = currYear - birthdayYear;
    } else {
      Long birthdayL = Long.valueOf(birthdayLStr);
      LocalDateTime birthdayLocalDate = DateUtil.convertDateToLDT(birthdayL);
      birthdayYear = birthdayLocalDate.getYear();
      int currYear = LocalDate.now().getYear();
      age = currYear - birthdayYear;
    }
    return age;
  }


}
