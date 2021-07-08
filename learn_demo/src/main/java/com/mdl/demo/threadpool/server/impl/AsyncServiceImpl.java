package com.mdl.demo.threadpool.server.impl;


import com.mdl.demo.threadpool.server.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.demo.threadpool.server
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年08月27日 14:57
 * ----------------- ----------------- -----------------
 */

@Service
public class AsyncServiceImpl implements AsyncService {

  private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

  @Override
  @Async("asyncServiceExecutor")
  public void executeAsync() {

    logger.info("start executeAsync");

    try {
      //执行具体业务
      System.out.println("异步线程要做的事情");
      System.out.println("可以在这里执行批量插入等耗时的事情");
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    logger.info("end executeAsync");
    logger.info("-------------------------------");

  }
}
