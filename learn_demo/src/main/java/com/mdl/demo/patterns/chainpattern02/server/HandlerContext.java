package com.mdl.demo.patterns.chainpattern02.server;

import com.mdl.demo.patterns.chainpattern02.model.UserTask;
import com.mdl.demo.patterns.chainpattern02.model.UserRequest;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.demo.patterns.chainpattern02
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年10月09日 16:26
 * ----------------- ----------------- -----------------
 */
public class HandlerContext {

  HandlerContext prev;
  HandlerContext next;
  Handler handler;

  private UserTask task;

  public void fireTaskReceived(UserRequest request) {
    invokeTaskReceived(next(), request);
  }

  /**
   * 处理接收到任务的事件
   */
  static void invokeTaskReceived(HandlerContext ctx, UserRequest request) {
    if (ctx != null) {
      try {
        ctx.handler().receiveTask(ctx, request);
      } catch (Throwable e) {
        ctx.handler().exceptionCaught(ctx, e);
      }
    }
  }

  public void fireTaskFiltered(UserTask task) {
    invokeTaskFiltered(next(), task);
  }

  /**
   * 处理任务过滤事件
   */
  static void invokeTaskFiltered(HandlerContext ctx, UserTask task) {
    if (null != ctx) {
      try {
        ctx.handler().filterTask(ctx, task);
      } catch (Throwable e) {
        ctx.handler().exceptionCaught(ctx, e);
      }
    }
  }

  public void fireTaskExecuted(UserTask task) {
    invokeTaskExecuted(next(), task);
  }

  /**
   * 处理执行任务事件
   */
  static void invokeTaskExecuted(HandlerContext ctx, UserTask task) {
    if (null != ctx) {
      try {
        ctx.handler().executeTask(ctx, task);
      } catch (Exception e) {
        ctx.handler().exceptionCaught(ctx, e);
      }
    }
  }

  public void fireAfterCompletion(HandlerContext ctx) {
    invokeAfterCompletion(next());
  }

  static void invokeAfterCompletion(HandlerContext ctx) {
    if (null != ctx) {
      ctx.handler().afterCompletion(ctx);
    }
  }

  private HandlerContext next() {
    return next;
  }

  private Handler handler() {
    return handler;
  }


}
