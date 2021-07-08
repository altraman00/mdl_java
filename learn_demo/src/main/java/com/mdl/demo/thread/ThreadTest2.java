package com.mdl.demo.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadTest2 {

  public static void main(String[] args) {

//    //获取cpu核心数
//    int coreCount = Runtime.getRuntime().availableProcessors();
//    System.out.println(coreCount);

    //核心线程数
    int corePoolSize = 3;
    //最大线程数
    int maximumPoolSize = 6;
    //超过corePoolSize线程数量的线程最大空闲时间
    long keepAliveTime = 2;
    //以秒为时间单位
    TimeUnit unit = TimeUnit.SECONDS;

    //创建工作队列，用于存放提交的等待执行任务
    BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);

    ThreadPoolExecutor executor = null;

    try {
      //创建线程池
      executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
          new ThreadPoolExecutor.AbortPolicy());

      for (int i = 0; i < 8; i++) {
        //提交任务的索引
        final int index = (i + 1);
        executor.submit(() -> {
          //打印线程
          System.out.println("this is thread:" + index);

          try {
            //模拟线程执行时间，10s
            Thread.sleep(10000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

          try {
            //每个任务提交后休眠500ms再提交下一个任务，用于保证提交顺序
            Thread.sleep(500);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }


        });
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      executor.shutdown();
    }


  }

}
