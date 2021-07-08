package com.mdl.demo.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTest3 {

  public static void main(String[] args) {

    //核心线程数
    int corePoolSize = 2;
    //最大线程数
    int maxiumPoolSize = 4;
    //等待时间
    long keepAliveTime = 10;
    //时间类型
    TimeUnit unit = TimeUnit.SECONDS;

    //队列
    BlockingQueue workQueue = new ArrayBlockingQueue(2);

    //线程工厂
    ThreadFactory threadFactory = new MyThreadFactory();

    //拒绝策略
    RejectedExecutionHandler rejectdHandler = new MyIgnorePolicy();

    ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxiumPoolSize,
        keepAliveTime, unit, workQueue, threadFactory, rejectdHandler);

    for (int i = 1; i < 10; i++) {
      executor.execute(new MyTask(String.valueOf(i)));
    }

  }

  /**
   * 线程工厂
   */
  private static class MyThreadFactory implements ThreadFactory {

    private final AtomicInteger nThreadNum = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
      Thread t = new Thread(r, "my-thread-" + nThreadNum.getAndIncrement());
      System.out.println("create thread : " + t.getName() + ":" + nThreadNum);
      return t;
    }

  }


  /**
   * 拒绝策略
   */
  private static class MyIgnorePolicy implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
      //记录拒绝日志
      System.out.println("reject" + r.toString());
    }
  }


  /**
   * 任务
   */
  private static class MyTask implements Runnable {

    private String name;

    public MyTask(String name) {
      this.name = name;
    }

    @Override
    public void run() {
      System.out.println("doing task " + name);
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return "MyTask{" +
          "name='" + name + '\'' +
          '}';
    }
  }


}
