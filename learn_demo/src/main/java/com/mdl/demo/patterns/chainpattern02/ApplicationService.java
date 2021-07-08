package com.mdl.demo.patterns.chainpattern02;

import com.mdl.demo.patterns.chainpattern02.model.UserRequest;
import com.mdl.demo.patterns.chainpattern02.server.DefaultPipeline;
import com.mdl.demo.patterns.chainpattern02.server.Pipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.demo.patterns.chainpattern02
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年10月09日 16:32
 * ----------------- ----------------- -----------------
 */
public class ApplicationService {

  @Autowired
  private ApplicationContext context;

  public void mockedClient() {
    // request一般是通过外部调用获取
    UserRequest request = new UserRequest();
    Pipeline pipeline = newPipeline(request);
    try {
      pipeline.fireTaskReceived();
      pipeline.fireTaskFiltered();
      pipeline.fireTaskExecuted();
    } finally {
      pipeline.fireAfterCompletion();
    }
  }

  private Pipeline newPipeline(UserRequest request) {
    return context.getBean(DefaultPipeline.class, request);
  }

}
