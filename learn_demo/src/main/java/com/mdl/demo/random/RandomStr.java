package com.mdl.demo.random;

import java.util.Collections;
import java.util.Map;
import java.util.Random;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.demo.random
 * @Author : xiekun
 * @Create Date : 2020年12月16日 14:05
 * ----------------- ----------------- -----------------
 */
public class RandomStr {

//  public static void main(String[] args) {
//    for (int i = 0; i < 90; i++) {
//      String randomString = getRandomString(16);
//      System.out.println(randomString);
//    }
//  }

  public static void main(String[] a){
    // create singleton map
    Map map = Collections.singletonMap("key","Value");
    System.out.println("Singleton map is: "+map);
  }


  /**
   * length用户要求产生字符串的长度
   */
  public static String getRandomString(int length) {
//    String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    String str = "bdeghkmnprstwxyzBDEGHKMNPRSTWXYZ234568";
    Random random = new Random();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < length; i++) {
      int number = random.nextInt(str.length());
      sb.append(str.charAt(number));
    }
    return sb.toString().toLowerCase();
  }

  /**
   * 可以指定字符串的某个位置是什么范围的值
   */
  public static String getRandomString2(int length) {
    Random random = new Random();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < length; i++) {
      int number = random.nextInt(3);
      long result = 0;
      switch (number) {
        case 0:
          result = Math.round(Math.random() * 25 + 65);
          sb.append(String.valueOf((char) result));
          break;
        case 1:
          result = Math.round(Math.random() * 25 + 97);
          sb.append(String.valueOf((char) result));
          break;
        case 2:
          sb.append(String.valueOf(new Random().nextInt(10)));
          break;
      }
    }
    return sb.toString();
  }

}
