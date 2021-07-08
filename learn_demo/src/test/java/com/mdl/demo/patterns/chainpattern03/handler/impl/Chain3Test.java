package com.mdl.demo.patterns.chainpattern03.handler.impl;

import com.mdl.demo.DemoApplication;
import com.mdl.demo.patterns.chainpattern03.handler.CostHandler;
import com.mdl.demo.patterns.chainpattern03.model.Task;
import java.util.List;
import java.util.Random;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.demo
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年10月09日 16:51
 * ----------------- ----------------- -----------------
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class Chain3Test {

  @Autowired
  private List<CostHandler> filters;

  @Test
  public void test() {
    System.out.println("12345678");
  }

  @Test
  public void test1() {
    Task task = new Task();
    Random rand = new Random();
    float v = rand.nextFloat();
    task.setDiscount(v);
    for (CostHandler filter : filters) {
      if (!filter.filter(task)) {
        return;
      }
    }

    // 过滤完成，后续是执行任务的逻辑
  }

}
