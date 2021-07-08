package com.mdl.demo.threadpool.controller;

import com.mdl.demo.threadpool.server.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.demo.threadpool.controller
 * @Description : 在命令窗口可使用以下命令进行简单压测 "ab -c 10 -n 100 http://127.0.0.1:8080/thread/executor"
 * @Author : xiekun
 * @Create Date : 2020年08月27日 14:57
 * ----------------- ----------------- -----------------
 */

@RestController
@RequestMapping("/thread-pool")
public class ThreadPoolTestController {

  private static final Logger logger = LoggerFactory.getLogger(ThreadPoolTestController.class);

  @Autowired
  private AsyncService asyncService;

  @GetMapping("/executor")
  public String submit() {
    logger.info("start submit");
    asyncService.executeAsync();
    logger.info("end submit");
    return "success";
  }

}
