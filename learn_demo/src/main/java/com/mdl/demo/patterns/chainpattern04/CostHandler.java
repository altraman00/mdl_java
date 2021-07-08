package com.mdl.demo.patterns.chainpattern04;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.demo.patterns.chainpattern04
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年10月09日 17:27
 * ----------------- ----------------- -----------------
 */
public interface CostHandler {

  /**
   * 处理消费事件
   */
  void process(TaskObj taskObj);

  /**
   * 使用java8的default默认实现
   */
  default void process(TaskObj taskObj, ListenerHandlerChainExecutor chain) throws Exception {
    this.process(taskObj);
    if (chain != null) {
      //链式调用
      chain.process(taskObj);
    }
  }


}
