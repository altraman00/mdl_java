package com.mdl.file.controller;

import com.mdl.file.model.SysLog;
import com.mdl.file.utils.ExportUtil;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.file.controller
 * @Author : xiekun
 * @Create Date : 2020年12月03日 14:30
 * ----------------- ----------------- -----------------
 */

@RestController
@RequestMapping("/test")
@Slf4j
public class DownloadFileController {

  @GetMapping("/file")
  public String download(HttpServletResponse response) {
    List<Map<String, Object>> dataList;

    List<SysLog> logList = new ArrayList<SysLog>() {{
      add(new SysLog("111", "111", "111", "111", new Date()));
      add(new SysLog("222", "222", "222", "222", new Date()));
      add(new SysLog("333", "333", "333", "333", new Date()));
    }};

    if (logList.size() == 0) {
      return "无数据导出";
    }
    String sTitle = "id,用户名,操作类型,操作方法,创建时间";
    String fName = "log_";
    String mapKey = "id,username,operation,method,createDate";
    dataList = new ArrayList<>();
    Map<String, Object> map;
    for (SysLog order : logList) {
      map = new HashMap<>();
      map.put("id", order.getId());
      map.put("username", order.getUsername());
      map.put("operation", order.getOperation());
      map.put("method", order.getMethod());
      map.put("createDate", DateFormatUtils.format(order.getCreateDate(), "yyyy/MM/dd HH:mm"));
      dataList.add(map);
    }
    try (final OutputStream os = response.getOutputStream()) {
      ExportUtil.responseSetProperties(fName, response);
      ExportUtil.doExport(dataList, sTitle, mapKey, os);
      return null;
    } catch (Exception e) {
      log.error("生成csv文件失败", e);
    }
    return "数据导出出错";
  }


  public static void main(String[] args) {




  }




}
