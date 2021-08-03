package com.mdl.influxdb4.controller;

import com.mdl.influxdb4.measurements.HealthSessionMeasurement;
import com.mdl.influxdb4.service.ITicHealthService;
import com.mdl.influxdb4.vo.HealthRecordQueryVO;
import com.mdl.influxdb4.vo.HealthRecordVO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project : ticwatch_backend
 * @Package Name : com.mobvoi.ticwatch.controller
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年07月28日 18:14
 * ----------------- ----------------- -----------------
 */

@RestController
@RequestMapping("/watch/health")
public class TicHealthRecordController {

  @Autowired
  private ITicHealthService iTicHealthService;

  @PostMapping("/influxdb/save")
  public Object saveMotionRecords(
      @RequestParam(value = "accountId") String accountId
      , @RequestBody HealthRecordVO healthRecordVO) {
    iTicHealthService.save(accountId, healthRecordVO);
    return "success";
  }

  @GetMapping("/influxdb/querylist/{dataSourceName}/{minStartTimeMs}-{maxEndTimeMs}")
  public Object queryMotionRecords(
      @PathVariable(value = "dataSourceName") String dataSourceName
      , @PathVariable(value = "minStartTimeMs") long minStartTimeMs
      , @PathVariable(value = "maxEndTimeMs") long maxEndTimeMs
      , @RequestParam(value = "dataType") String dataType
      , @RequestParam(value = "accountId") String accountId
  ) {
    HealthRecordQueryVO queryVO = new HealthRecordQueryVO();
    queryVO.setAccount_id(accountId);
    queryVO.setData_type(dataType);
    queryVO.setMin_start_time_ms(minStartTimeMs);
    queryVO.setMax_end_time_ms(maxEndTimeMs);
    queryVO.setDats_source_name(dataSourceName);
    List<HealthSessionMeasurement> resList = iTicHealthService.queryList(queryVO);
    return resList;
  }

}
