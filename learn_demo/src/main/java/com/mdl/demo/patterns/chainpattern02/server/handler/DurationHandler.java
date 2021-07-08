package com.mdl.demo.patterns.chainpattern02.server.handler;

import com.mdl.demo.patterns.chainpattern02.model.UserTask;
import com.mdl.demo.patterns.chainpattern02.server.Handler;
import com.mdl.demo.patterns.chainpattern02.server.HandlerContext;
import org.springframework.stereotype.Component;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.demo.patterns.chainpattern02
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年10月09日 16:33
 * ----------------- ----------------- -----------------
 */
@Component
public class DurationHandler implements Handler {

  @Override
  public void filterTask(HandlerContext ctx, UserTask task) {
    System.out.println("时效性检验");
    ctx.fireTaskFiltered(task);
  }

}
