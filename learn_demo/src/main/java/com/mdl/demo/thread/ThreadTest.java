package com.mdl.demo.thread;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTest {

  public static void main(String[] args) throws IOException {

//    Executor executor1 = Executors.newFixedThreadPool(3);
//    Executor executor2 = Executors.newSingleThreadExecutor();

    //核心线程数
    int corePoolSize = 2;
    //最大线程数
    int maxiumPoolSize = 4;
    //等待时间
    long keepAliveTime = 10;
    //单位
    TimeUnit unit = TimeUnit.SECONDS;
    //线程等待队列
    BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
    //线程创建工厂
    ThreadFactory threadFactory = new NameThreadFactory();
//    ThreadFactory threadFactoryBuilder = new ThreadFactoryBuilder().setNameFormat("thread-").build();

    //拒绝策略
    RejectedExecutionHandler handler = new MyIgnorePolicy();

    ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxiumPoolSize,
        keepAliveTime, unit, workQueue, threadFactory, handler);
    //预启动所有核心线程
    executor.prestartAllCoreThreads();

    for (int i = 1; i <= 10; i++) {
      MyTask myTask = new MyTask(String.valueOf(i));
      executor.execute(myTask);
    }
    //阻塞主线程
    System.in.read();
  }

  /**
   * 线程创建工厂
   */
  public static class NameThreadFactory implements ThreadFactory {

    private final AtomicInteger nThreadNum = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
      Thread t = new Thread(r, "my-thread-" + nThreadNum.getAndIncrement());
      System.out.println(t.getName() + " has been created");
      return t;
    }

  }


  /**
   * 拒绝策略
   */
  public static class MyIgnorePolicy implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
      //可做日志记录等
      System.err.println(r.toString() + " rejected");
//      System.out.println("completedTaskCount:" + executor.getCompletedTaskCount());

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
      try {
        System.out.println(this.toString() + "is running!");
        //睡眠3s，让任务执行慢点
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
      return "MyTask{" + "name='" + name + '}';
    }
  }

}
