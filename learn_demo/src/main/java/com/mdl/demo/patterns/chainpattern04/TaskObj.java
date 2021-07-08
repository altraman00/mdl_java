package com.mdl.demo.patterns.chainpattern04;

import lombok.Data;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.demo.patterns.chainpattern04
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年10月09日 17:29
 * ----------------- ----------------- -----------------
 */

@Data
public class TaskObj {

  private float amount;

  public TaskObj(float amount) {
    this.amount = amount;
  }
}
