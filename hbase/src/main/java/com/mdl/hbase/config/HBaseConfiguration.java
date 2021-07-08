package com.mdl.hbase.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.hbase.config
 * @Author : xiekun
 * @Create Date : 2020年11月23日 17:17
 * ----------------- ----------------- -----------------
 */
@Configuration
public class HBaseConfiguration {

  @Value("${hbase.zookeeper.quorum}")
  private String zookeeperQuorum;

  @Value("${hbase.zookeeper.property.clientPort}")
  private String clientPort;

  @Bean
  public HbaseTemplate hbaseTemplate() {
    org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
    conf.set("hbase.zookeeper.quorum", zookeeperQuorum);
    conf.set("hbase.zookeeper.property.clientPort", clientPort);
    return new HbaseTemplate(conf);
  }

}
