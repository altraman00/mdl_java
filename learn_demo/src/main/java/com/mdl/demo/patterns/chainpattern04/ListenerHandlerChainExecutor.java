package com.mdl.demo.patterns.chainpattern04;

import java.util.Iterator;
import java.util.List;
import lombok.SneakyThrows;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.demo.patterns.chainpattern04
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年10月09日 18:14
 * ----------------- ----------------- -----------------
 */
public class ListenerHandlerChainExecutor implements CostHandler {

  private Iterator<CostHandler> it;
  private List<CostHandler> handlers;

  public ListenerHandlerChainExecutor(List<CostHandler> handlers) {
    this.handlers = handlers;
    it = this.handlers.iterator();
  }

  @SneakyThrows
  @Override
  public void process(TaskObj taskObj) {
    if (it.hasNext()) {
      CostHandler nextHandler = it.next();
      //ResponseHandler 接口里的默认实现,java8的default
      //！！！！关键的调用，实现了链式处理
      nextHandler.process(taskObj, this);
    }

  }
}
