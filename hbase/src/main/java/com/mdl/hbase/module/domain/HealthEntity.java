package com.mdl.hbase.module.domain;

import lombok.Data;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.hbase.module.domain
 * @Author : xiekun
 * @Create Date : 2020年11月23日 17:32
 * ----------------- ----------------- -----------------
 */


/**
 * hbase只能使用基础类型，不支持包装类Integer，Long，Boolean等
 */
@Data
public class HealthEntity {

  /**
   * 列簇
   */
  public final static String FAMILY = "base_info";

  public String name;

  public int age;

  public String sex;

  public String address;

  public long salary;


}
