package com.mdl.influxdb.controller;

import com.mdl.influxdb.config.InfluxDBUtils;
import com.mdl.influxdb.model.Sensor;
import com.mdl.influxdb.model.vo.SensorVO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class InfluxdbControllerDemo {

  @Autowired
  private InfluxDBUtils influxdbUtils;



  /**
   * 插入单挑记录
   */
  @PostMapping("/index1")
  public void insert(@RequestBody SensorVO sensorVo) {
    Sensor sensor = apply(sensorVo);
    influxdbUtils.writeOne(sensor);
  }

  /**
   * 插入单挑记录
   */
  @PostMapping("/index12")
  public void insert2(@RequestBody SensorVO sensorVo) {
    Sensor sensor = apply(sensorVo);
    influxdbUtils.saveOne(sensor);
  }

  /**
   * 批量插入
   */
  @PostMapping("/index2")
  public void batchInsert(@RequestBody List<SensorVO> voList) {
    List<Sensor> sensorList = voList.stream().map(InfluxdbControllerDemo::apply)
        .collect(Collectors.toList());
    influxdbUtils.writeBatchByList(sensorList);
  }

  private static Sensor apply(SensorVO sensorVo) {
    Sensor sensor = new Sensor();
    sensor.setTime(sensorVo.getTime());
    sensor.setTWwid(sensorVo.getTWwid());
    sensor.setTAccountId(sensorVo.getTAccountId());
    sensor.setTDataType(sensorVo.getTDataType());
    sensor.setTDataSourceName(sensorVo.getTDataSourceName());
    sensor.setTStartTime(sensorVo.getTStartTime());
    sensor.setTEndTime(sensorVo.getTEndTime());
    sensor.setFValue(sensorVo.getFValue());
    sensor.setFValue2(sensorVo.getFValue2());
    sensor.setFValue3(sensorVo.getFValue3());
    return sensor;
  }


  /**
   * 获取数据(直接返回object，没有转换成实体对象)
   */
  @GetMapping("/datas01")
  public List<Object> datas(
      @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
  ) {
    // InfluxDB支持分页查询,因此可以设置分页查询条件
    String pageQuery = " LIMIT " + pageSize + " OFFSET " + (pageNo - 1) * pageSize;

    //查询条件暂且为空
    String queryCondition = "";
    // 此处查询所有内容,如果
    String queryCmd = "SELECT * FROM tic_sensor"
        // 查询指定设备下的日志信息
        // 要指定从 RetentionPolicyName.measurement中查询指定数据,默认的策略可以不加；
        // + 策略name + "." + measurement
        // 添加查询条件(注意查询条件选择tag值,选择field数值会严重拖慢查询速度)
        + queryCondition
        // 查询结果需要按照时间排序
        + " ORDER BY time DESC"
        // 添加分页查询条件
        + pageQuery;

    List<Object> sensorList = influxdbUtils.fetchRecords(queryCmd);
    log.info("query result => {}", sensorList);
    return sensorList;
  }

//  /**
//   * 获取数据(返回实体对象)
//   */
//  @GetMapping("/datas02")
//  public List<Sensor> datas1(
//      @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
//      @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
//  ) {
//    // InfluxDB支持分页查询,因此可以设置分页查询条件
//    String pageQuery = " LIMIT " + pageSize + " OFFSET " + (pageNo - 1) * pageSize;
//
//    //查询条件暂且为空
//    String queryCondition = "";
//    // 此处查询所有内容,如果
//    String queryCmd = "SELECT * FROM tic_sensor "
//        // 查询指定设备下的日志信息
//        // 要指定从 RetentionPolicyName.measurement中查询指定数据,默认的策略可以不加；
//        // + 策略name + "." + measurement
//        // 添加查询条件(注意查询条件选择tag值,选择field数值会严重拖慢查询速度)
//        + queryCondition
//        // 查询结果需要按照时间排序
//        + " ORDER BY time DESC"
//        // 添加分页查询条件
//        + pageQuery;
//    List<Sensor> sensorList = influxdbUtils.fetchResults(queryCmd, Sensor.class);
//    sensorList.forEach(sensor -> {
//      log.info("query tic_sensor => {}", sensor);
//    });
//    return sensorList;
//  }
//
//  /**
//   * 获取数据(返回实体对象)
//   */
//  @GetMapping("/datas03")
//  public List<Sensor> datas2(
//      @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
//      @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
//  ) {
//    // InfluxDB支持分页查询,因此可以设置分页查询条件
//    String pageQuery = " LIMIT " + pageSize + " OFFSET " + (pageNo - 1) * pageSize;
//
//    //查询条件暂且为空
//    String queryCondition = "";
//    // 此处查询所有内容,如果
//    String queryCmd = "SELECT * FROM tic_sensor "
//        // 查询指定设备下的日志信息
//        // 要指定从 RetentionPolicyName.measurement中查询指定数据,默认的策略可以不加；
//        // + 策略name + "." + measurement
//        // 添加查询条件(注意查询条件选择tag值,选择field数值会严重拖慢查询速度)
//        + queryCondition
//        // 查询结果需要按照时间排序
//        + " ORDER BY time DESC"
//        // 添加分页查询条件
//        + pageQuery;
//    List<Sensor> sensorList = influxdbUtils.fetchResults("*", "tic_sensor", Sensor.class);
//    sensorList.forEach(sensor -> {
//      log.info("query tic_sensor => {}", sensor);
//    });
//    return sensorList;
//  }


}