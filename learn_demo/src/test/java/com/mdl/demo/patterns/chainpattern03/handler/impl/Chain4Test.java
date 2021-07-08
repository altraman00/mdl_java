package com.mdl.demo.patterns.chainpattern03.handler.impl;

import com.mdl.demo.DemoApplication;
import com.mdl.demo.patterns.chainpattern04.CostHandler;
import com.mdl.demo.patterns.chainpattern04.ListenerHandlerChainExecutor;
import com.mdl.demo.patterns.chainpattern04.TaskObj;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.demo.patterns.chainpattern03.handler.impl
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年10月09日 17:38
 * ----------------- ----------------- -----------------
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class Chain4Test {

  @Autowired
  private List<CostHandler> costHandlerList;

  @Test
  public void test1() {
    TaskObj taskObj = new TaskObj(0.43f);
    new ListenerHandlerChainExecutor(costHandlerList).process(taskObj);
  }


}
