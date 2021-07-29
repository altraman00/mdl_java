package com.mdl.home;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.mdl.home.util.DateUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.home
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年05月27日 18:13
 * ----------------- ----------------- -----------------
 */
public class TestBirthdayInfoDataSet {


  public static void main(String[] args) {

//    String json = "[{\"data_source_name\":\"raw:09d72f97717fb517cc2c482d\",\"max_end_time_ms\":1610697722411,\"min_start_time_ms\":1610527596215,\"points\":[{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1610530581534,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1610530581534,\"string_val\":\"678349056\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1610527596215,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1610527596215,\"string_val\":\"709975104\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1610697722411,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1610697722411,\"string_val\":\"457514208\"}]},{\"data_source_name\":\"raw:15ba7ef5ffaba2690b9782c9\",\"max_end_time_ms\":1616752452837,\"min_start_time_ms\":1616752452837,\"points\":[{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1616752452837,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1616752452837,\"string_val\":\"-1138716288\"}]},{\"data_source_name\":\"raw:21c935087ad6b0402e700bff\",\"max_end_time_ms\":1619518702749,\"min_start_time_ms\":1615361012007,\"points\":[{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1619507252427,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1619507252427,\"string_val\":\"646758016\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1619518702749,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1619518702749,\"string_val\":\"646758016\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1615361012007,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1615361012007,\"string_val\":\"-570731776\"}]},{\"data_source_name\":\"raw:23c6f87418b8eb48585f5ea2\",\"max_end_time_ms\":1617952432246,\"min_start_time_ms\":1610522286523,\"points\":[{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1611303902068,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1611303902068,\"string_val\":\"-2098712064\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1617952432246,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1617952432246,\"string_val\":\"-286649152\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1611821536871,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1611821536871,\"string_val\":\"836208768\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1611821406748,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1611821406748,\"string_val\":\"962438976\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1610522286523,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1610522286523,\"string_val\":\"836205504\"}]},{\"data_source_name\":\"raw:248f1d06f16520d99fb9310d\",\"max_end_time_ms\":1619156903144,\"min_start_time_ms\":1618991504439,\"points\":[{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1619156903144,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1619156903144,\"string_val\":\"646758016\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1618994779179,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1618994779179,\"string_val\":\"-2117027584\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1618991504439,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1618991504439,\"string_val\":\"-1359648512\"}]},{\"data_source_name\":\"raw:38def1deaa8c52703a00ba87\",\"max_end_time_ms\":1622085078794,\"min_start_time_ms\":1622075290653,\"points\":[{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1622085078794,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1622085078794,\"string_val\":\"-1672519680\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1622075290653,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1622075290653,\"string_val\":\"-1672529536\"}]},{\"data_source_name\":\"raw:420e51c5ead2ba25e5b03f4d\",\"max_end_time_ms\":1619772414358,\"min_start_time_ms\":1617952489963,\"points\":[{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1619592657168,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1619592657168,\"string_val\":\"646758016\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1617952489963,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1617952489963,\"string_val\":\"-1328114688\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1619772414358,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1619772414358,\"string_val\":\"646758016\"}]},{\"data_source_name\":\"raw:429911e1cd5b99621307071d\",\"max_end_time_ms\":1605089847148,\"min_start_time_ms\":1605089847148,\"points\":[{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1605089847148,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1605089847148,\"string_val\":\"-2138103808\"}]},{\"data_source_name\":\"raw:58bcab2a21383ac04226472c\",\"max_end_time_ms\":1609324058443,\"min_start_time_ms\":1608695387994,\"points\":[{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1608704229738,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1608704229738,\"string_val\":\"-2138118272\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1608695387994,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1608695387994,\"string_val\":\"-2138127104\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1609222508353,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1609222508353,\"string_val\":\"837152128\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1609324058443,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1609324058443,\"string_val\":\"-2.16082637E9\"}]},{\"data_source_name\":\"raw:596ba0df264157bd6c038934\",\"max_end_time_ms\":1621679568867,\"min_start_time_ms\":1621679568867,\"points\":[{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1621679568867,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1621679568867,\"string_val\":\"536428800\"}]},{\"data_source_name\":\"raw:6785c97215bf5c139544415d\",\"max_end_time_ms\":1620741988355,\"min_start_time_ms\":1620741988355,\"points\":[{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1620741988355,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1620741988355,\"string_val\":\"631123200\"}]},{\"data_source_name\":\"raw:7982637268b34dbf39524d65\",\"max_end_time_ms\":1615187708046,\"min_start_time_ms\":1615187708046,\"points\":[{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1615187708046,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1615187708046,\"string_val\":\"-1549008000.0\"}]},{\"data_source_name\":\"raw:84cee0086319d0c11594baa0\",\"max_end_time_ms\":1611555450090,\"min_start_time_ms\":1610091721335,\"points\":[{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1610098636449,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1610098636449,\"string_val\":\"646814464\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1610097730132,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1610097730132,\"string_val\":\"646814464\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1610091721335,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1610091721335,\"string_val\":\"646814464\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1611555450090,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1611555450090,\"string_val\":\"804579456\"}]},{\"data_source_name\":\"raw:906187d87cc3df2d1a5ebc81\",\"max_end_time_ms\":1612332013886,\"min_start_time_ms\":1596873092357,\"points\":[{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1612332013886,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1612332013886,\"string_val\":\"646808384\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1612331996275,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1612331996275,\"string_val\":\"836200768\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1596873092357,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1596873092357,\"string_val\":\"131528704\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1611303957880,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1611303957880,\"string_val\":\"-2098712064\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1611909900636,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1611909900636,\"string_val\":\"836210688\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1611906223976,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1611906223976,\"string_val\":\"552120192\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1611819981392,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1611819981392,\"string_val\":\"804584768\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1611818414051,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1611818414051,\"string_val\":\"804583232\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1603697684739,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1603697684739,\"string_val\":\"-1956587136\"}]},{\"data_source_name\":\"raw:90ef3b7980311d7716f3d990\",\"max_end_time_ms\":1620901007288,\"min_start_time_ms\":1620901007288,\"points\":[{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1620901007288,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1620901007288,\"string_val\":\"631123200\"}]},{\"data_source_name\":\"raw:99db380216601df52c814ba7\",\"max_end_time_ms\":1600399874708,\"min_start_time_ms\":1600399346502,\"points\":[{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1600399874708,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1600399874708,\"string_val\":\"12600\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1600399863400,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1600399863400,\"string_val\":\"157779008\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1600399859289,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1600399859289,\"string_val\":\"63084600\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1600399855756,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1600399855756,\"string_val\":\"-124576200\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1600399787577,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1600399787577,\"string_val\":\"-380579456\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1600399770362,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1600399770362,\"string_val\":\"-285366656\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1600399744588,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1600399744588,\"string_val\":\"-976307520\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1600399683949,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1600399683949,\"string_val\":\"-663712384\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1600399346502,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1600399346502,\"string_val\":\"-1036080896\"}]},{\"data_source_name\":\"raw:a47982783ec063a3336097e8\",\"max_end_time_ms\":1618827925660,\"min_start_time_ms\":1618827925660,\"points\":[{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1618827925660,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1618827925660,\"string_val\":\"-1359639296\"}]},{\"data_source_name\":\"raw:cbae1ea6b0a790fa8bf1288d\",\"max_end_time_ms\":1614243462606,\"min_start_time_ms\":1614243462606,\"points\":[{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1614243462606,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1614243462606,\"string_val\":\"-2098710144\"}]},{\"data_source_name\":\"raw:e0622845ec451b7ef84dd9a2\",\"max_end_time_ms\":1621330269064,\"min_start_time_ms\":1619433399220,\"points\":[{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1619433399220,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1619433399220,\"string_val\":\"631123200\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1621330269064,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1621330269064,\"string_val\":\"631123200\"}]},{\"data_source_name\":\"raw:f00b439e6b100cdff2348aed\",\"max_end_time_ms\":1619142603542,\"min_start_time_ms\":1619142603542,\"points\":[{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1619142603542,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1619142603542,\"string_val\":\"646758016\"}]},{\"data_source_name\":\"raw:f4db7bf823c2596f14ee912c\",\"max_end_time_ms\":1602579400754,\"min_start_time_ms\":1602579400754,\"points\":[{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1602579400754,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1602579400754,\"string_val\":\"-1956601856\"}]}]";
//    String json = "[{\"data_source_name\":\"raw:a973f7086f34eeb95902cdfb\",\"max_end_time_ms\":1622449907673,\"min_start_time_ms\":1618889097993,\"points\":[{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1622449907673,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1622449907673,\"string_val\":\"631123200\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1618889097993,\"fp_val\":0.0,\"int_val\":0,\"start_time_millis\":1618889097993,\"string_val\":\"631123200\"}]}]";
    String json = "[{\"data_source_name\":\"raw:d9ea935a6f211affa008f9a9\",\"max_end_time_ms\":1626404254757,\"min_start_time_ms\":1626404225835,\"points\":[{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1626404254757,\"fp_val\":0,\"int_val\":0,\"start_time_millis\":1626404254757,\"string_val\":\"-2097435776\"},{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1626404225835,\"fp_val\":0,\"int_val\":0,\"start_time_millis\":1626404225835,\"string_val\":\"963716224\"}]},{\"data_source_name\":\"raw:f84353ffbc6bfb30046d9c08\",\"max_end_time_ms\":1626415895738,\"min_start_time_ms\":1626415895738,\"points\":[{\"data_type\":\"info_birthday\",\"deleted\":0,\"end_time_millis\":1626415895738,\"fp_val\":0,\"int_val\":0,\"start_time_millis\":1626415895738,\"string_val\":\"-1182188928\"}]}]";

    JSONArray objects = JSONUtil.parseArray(json);

    List<DataSet> dataSets = JSONUtil.toList(objects, DataSet.class);

    List<DataSetPoint> allAgePoints = new ArrayList<>();
    List<DataSetPoint> finalAllPoints = allAgePoints;
    dataSets.stream().forEach(t -> {
      t.setMaxEndTime(DateUtil.transferLongToDate(DateUtil.DATE_TIME_FORMAT, t.getMaxEndTimeMs()));
      t.setMinStartTime(DateUtil.transferLongToDate(DateUtil.DATE_TIME_FORMAT, t.getMinStartTimeMs()));

      JSONArray objects1 = JSONUtil.parseArray(t.getPoints());
      List<DataSetPoint> points = JSONUtil.toList(objects1, DataSetPoint.class);
      for (DataSetPoint p : points) {
        p.setStartTime(DateUtil.transferLongToDate(DateUtil.DATE_TIME_FORMAT, p.getStartTimeMillis()));
        p.setEndTime(DateUtil.transferLongToDate(DateUtil.DATE_TIME_FORMAT, p.getEndTimeMillis()));
        finalAllPoints.add(p);
      }
    });

    allAgePoints = sortListByStartAt(allAgePoints);
    System.out.println(JSONUtil.toJsonStr(dataSets));
    System.out.println(JSONUtil.toJsonStr(allAgePoints));


  }


  private static List<DataSetPoint> sortListByStartAt(List<DataSetPoint> list) {
    List<DataSetPoint> collect = list.stream()
        .sorted(Comparator.comparing(DataSetPoint::getStartTimeMillis).reversed())
        .collect(Collectors.toList());
    return collect;
  }


}
