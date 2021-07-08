package com.mdl.home;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.home
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年05月28日 12:04
 * ----------------- ----------------- -----------------
 */
public class TestMotionRecords {

  public static void main(String[] args) {

    int strengthHeart = 87;
    String filePath = "/Users/knight/workspace/sourceTree/os_mdl/mdl/mdl-demo/tools-home/src/test/java/com/mdl/home/motion_records.json";
    File file = new File(filePath);

    JSONArray jsonArray = JSONUtil.readJSONArray(file, Charset.forName("UTF-8"));
    JSONObject jsonObject = jsonArray.getJSONObject(0);
    String account_id = jsonObject.get("account_id").toString();
    System.out.println(account_id);

    JSONArray track_points = jsonObject.getJSONArray("track_points");

    List<SportPoint> sportPoints = JSONUtil.toList(track_points, SportPoint.class);
    long tempTime = 0;
    long highIntensityTrainingTime = 0;

    for (SportPoint point : sportPoints) {
      if (point.heartRate > 0) {
        System.out.println("strengthHeart is " + strengthHeart + " heart is " + point.heartRate);

        if (point.heartRate > strengthHeart) {
          if (tempTime != 0 && point.timestamp > tempTime) {
            highIntensityTrainingTime += point.timestamp - tempTime;
          }
          tempTime = point.timestamp;
        } else {
          tempTime = 0;
        }
      }
    }
    if (highIntensityTrainingTime > 0L) {
      System.out.println("strengthHeart is " + strengthHeart + " time is " + highIntensityTrainingTime);
    }
  }


//  public void test() throws Exception {
//    int strengthHeart = 87;
//    String jsonText = FileUtils.getStringFromFile(getFileFromResource("points.json"));
//    NetSportRecord record = JSON.parseObject(jsonText, NetSportRecord.class);
//    SportPointCollection sportPointCollection = DataTranslator.translatePoints(record, GeoUtil.LOCATION_SYSTEM_STANDARD);
//    Iterable<SportPoint> sportPoints = sportPointCollection.getTrackPoints();
//    long tempTime = 0;
//    long highIntensityTrainingTime = 0;
//    SportPoint maxHeart = null;
//    SportPoint minHeart = null;
//    for (SportPoint point : sportPoints) {
//      if (point.heart > 0) {
//        if (Log.isLoggable("strengthHeart", Log.VERBOSE)) {
//          LogUtil.d(TAG, "strengthHeart is %d, heart is %d", strengthHeart, point.heart);
//        }
//
//        if (maxHeart == null || minHeart == null) {
//          maxHeart = point;
//          minHeart = point;
//        }
//
//        if (maxHeart.heart < point.heart) {
//          maxHeart = point;
//        }
//        if (minHeart.heart > point.heart) {
//          minHeart = point;
//        }
//
//        if (point.heart > strengthHeart) {
//          if (tempTime != 0 && point.time > tempTime) {
//            highIntensityTrainingTime += point.time - tempTime;
//          }
//          tempTime = point.time;
//        } else {
//          tempTime = 0;
//        }
//      }
//    }
//    if (highIntensityTrainingTime > 0L) {
//      LogUtil.d(TAG, "strengthHeart is %d, time is %d", strengthHeart, highIntensityTrainingTime);
//
//      DataPoint dataPoint = new DataPoint();
//      dataPoint.time_from = record.startAt;
//      dataPoint.time_to = record.endAt;
//      dataPoint.type = DataType.HighStrengthTraining.typeCode;
//      dataPoint.values = record.motionType + "," + highIntensityTrainingTime;
//    }
//
//  }


}
