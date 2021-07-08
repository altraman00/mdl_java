package com.mdl.demo.patterns.chainpattern03.handler.impl;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.demo.patterns.chainpattern03
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年10月09日 16:45
 * ----------------- ----------------- -----------------
 */

import com.mdl.demo.patterns.chainpattern03.handler.CostHandler;
import com.mdl.demo.patterns.chainpattern03.model.Task;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 次数限制校验
 */

@Component
@Order(1)
public class TimesCostHandler implements CostHandler {

  @Override
  public boolean filter(Task task) {
    Float discount = task.getDiscount();
    System.out.println("TimesCostHandler:" + discount);
    Boolean flag = false;
    if (discount <= 0.15) {
      System.out.format("%s批准了折扣:%.2f%n", this.getClass().getName(), discount);
      flag = true;
    } else {
      System.out.format("%s拒绝了折扣:%.2f%n", this.getClass().getName(), discount);
    }
    return flag;
  }
}
