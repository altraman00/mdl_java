package com.mdl.demo.patterns.chainpattern05.handler;

import com.mdl.demo.patterns.chainpattern05.CostHandler;
import org.springframework.stereotype.Component;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.demo.patterns.chainpattern04.handler
 * @Description : 充值金额的消费
 * @Author : xiekun
 * @Create Date : 2020年10月09日 17:30
 * ----------------- ----------------- -----------------
 */

@Component("MamountCostHandler")
public class MamountCostHandler extends CostHandler {

  @Override
  public CostHandler getNextHandler() {
    return null;
  }

  @Override
  public void setNextHandler(CostHandler nextHandler) {

  }

  @Override
  public void dealRequest(float num) {
    System.out.println("MamountCostHandler:" + num);
    if (num <= 55) {
      System.out.format("%s消费了:%.2f%n", this.getClass().getName(), num);
    }
  }

}
