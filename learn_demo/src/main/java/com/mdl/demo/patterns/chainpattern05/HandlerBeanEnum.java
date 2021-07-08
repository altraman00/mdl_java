package com.mdl.demo.patterns.chainpattern05;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.demo.patterns.chainpattern05
 * @Description : 严格按照枚举的顺序执行责任链顺序
 * @Author : xiekun
 * @Create Date : 2020年10月09日 22:15
 * ----------------- ----------------- -----------------
 */

@Getter
@AllArgsConstructor
public enum HandlerBeanEnum {

  /**
   * 枚举顺序不能随意改变
   **/
  MnewUserCostHandler("MnewUserCostHandler"),
  MactivityCostHandler("MactivityCostHandler"),
  MamountCostHandler("MamountCostHandler");

  private String beanName;

}
