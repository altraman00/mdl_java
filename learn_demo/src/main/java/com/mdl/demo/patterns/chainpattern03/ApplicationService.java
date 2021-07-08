package com.mdl.demo.patterns.chainpattern03;

import com.mdl.demo.patterns.chainpattern03.handler.CostHandler;
import com.mdl.demo.patterns.chainpattern03.model.Task;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.demo.patterns.chainpattern03
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年10月09日 16:47
 * ----------------- ----------------- -----------------
 */
public class ApplicationService {

  @Autowired
  private List<CostHandler> filters;

  public void mockedClient() {
    Task task = new Task();
    for (CostHandler filter : filters) {
      if (!filter.filter(task)) {
        return;
      }
    }

    // 过滤完成，后续是执行任务的逻辑
  }

}
