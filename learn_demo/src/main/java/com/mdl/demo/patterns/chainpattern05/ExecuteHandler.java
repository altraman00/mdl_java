package com.mdl.demo.patterns.chainpattern05;

import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.demo.patterns.chainpattern05
 * @Description : 职责处理器
 * @Author : xiekun
 * @Create Date : 2020年10月09日 22:04
 * ----------------- ----------------- -----------------
 */

@Component
public class ExecuteHandler {

  @Autowired
  private ApplicationContext context;

  private List<CostHandler> handlerList = new LinkedList<>();

  private CostHandler handler;


  /**
   * 方法一：
   * 在@PostConstruct标注的方法中将多个责任连接成链，然后返回第一个责任，在调用第一个责任内可根据逻辑决定是否执行下一个责任
   *
   * 方法二：
   * 在@PostConstruct标注的方法中将多个责任直接放进有序list，然后在另一个方法中通过循环返回每一个责任的处理结果，
   * 然后判断当前责任的结果是true还是false，决定循环是否执行下一个责任
   * 例：
   * public class ChainApplication {
   *     public static void main(String[] args) {
   *         HandlerChain handlerChain = new HandlerChain();
   *         handlerChain.addHandler(Lists.newArrayList(new HandlerA(), new HandlerB()));
   *         boolean resultFlag = handlerChain.handle();
   *         if (!resultFlag) {
   *             System.out.println("责任链中处理器不满足条件");
   *         }
   *     }
   * }
   *
   * 作者：源码兴趣圈
   * 链接：https://juejin.cn/post/6932058640677355534
   * 来源：掘金
   * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
   *
   * @return
   */
  public CostHandler getHandler() {
    return handler;
  }

  /**
   * 该方法会在该对象创建之后被调用
   */
  @PostConstruct
  public void afterPostConstruct() {
    HandlerBeanEnum[] values = HandlerBeanEnum.values();
    for (HandlerBeanEnum value : values) {
      CostHandler bean = context.getBean(value.getBeanName(), CostHandler.class);
      handlerList.add(bean);
    }
    if (handlerList != null && handlerList.size() > 0) {
      for (int i = 1; i < handlerList.size(); i++) {
        //当前处理器
        CostHandler currentHandler = handlerList.get(i - 1);
        //下一个处理器
        CostHandler nextHandler = handlerList.get(i);
        //将处理器串成链表
        currentHandler.setNextHandler(nextHandler);
      }
      this.handler = handlerList.get(0);
    }

  }

}
