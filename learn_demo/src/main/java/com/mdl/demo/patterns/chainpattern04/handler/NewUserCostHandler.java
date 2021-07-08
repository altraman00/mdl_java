package com.mdl.demo.patterns.chainpattern04.handler;

import com.mdl.demo.patterns.chainpattern04.CostHandler;
import com.mdl.demo.patterns.chainpattern04.TaskObj;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.demo.patterns.chainpattern04.handler
 * @Description : 新用户的免费调用次数
 * @Author : xiekun
 * @Create Date : 2020年10月09日 17:30
 * ----------------- ----------------- -----------------
 */

@Component
@Order(1)
public class NewUserCostHandler implements CostHandler {

  @Override
  public void process(TaskObj taskObj) {
    float amount = taskObj.getAmount();
    System.out.println("MnewUserCostHandler:" + amount);
    if (amount <= 0.15) {
      System.out.format("%s消费了:%.2f%n", this.getClass().getName(), amount);
    }
  }
}
