package com.mdl.influxdb;

import com.mdl.influxdb.config.InfluxDBUtils;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InfluxdbApplicationTests {

  @Autowired
  private InfluxDBUtils influxDBUtils;

  @Test
  void query() {

    String wwid = "";
    HashMap<String, String> params = new HashMap<String, String>(1) {{
      put("wwid", wwid);
    }};
    String tags = influxDBUtils.getTags(params);
    System.out.println(tags);

  }

}
