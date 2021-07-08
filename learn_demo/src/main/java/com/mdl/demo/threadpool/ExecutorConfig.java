package com.mdl.demo.threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.demo.threadpool
 * @Description : 参考 https://blog.csdn.net/boling_cavalry/article/details/79120268
 * @Author : xiekun
 * @Create Date : 2020年08月27日 14:57
 * ----------------- ----------------- -----------------
 */

@Configuration
@EnableAsync
public class ExecutorConfig {

  private static final Logger logger = LoggerFactory.getLogger(ExecutorConfig.class);

  @Bean(name = "asyncServiceExecutor")
  public Executor asyncServiceExecutor() {
    logger.info("start asyncServiceExecutor");
    ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
    //设置核心线程数
    executor.setCorePoolSize(5);
    //设置最大线程数
    executor.setMaxPoolSize(7);
    //设置队列大小
    executor.setQueueCapacity(3);
    //设置线程池中的线程的名称前缀
    executor.setThreadNamePrefix("async-service-");
    //rejection-policy：当pool已经达到max size的时候，如何处理新任务
    //CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
    executor.setRejectedExecutionHandler(new CallerRunsPolicy());
    executor.initialize();
    return executor;
  }

}
