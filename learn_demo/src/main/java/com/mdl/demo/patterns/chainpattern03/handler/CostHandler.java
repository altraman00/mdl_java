package com.mdl.demo.patterns.chainpattern03.handler;

import com.mdl.demo.patterns.chainpattern03.model.Task;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.demo.patterns.chainpattern03
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年10月09日 16:43
 * ----------------- ----------------- -----------------
 */
public interface CostHandler {

  /**
   * 用于对各个任务节点进行过滤
   */
  boolean filter(Task task);

}
