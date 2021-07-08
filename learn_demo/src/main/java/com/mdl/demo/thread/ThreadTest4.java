package com.mdl.demo.thread;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.demo.thread
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年08月28日 18:53
 * ----------------- ----------------- -----------------
 */
public class ThreadTest4 {

  public static void main(String[] args) {
    Runner1 runner1 = new Runner1();
    Runner2 runner2 = new Runner2();
//  Thread(Runnable target) 分配新的 Thread 对象。
    Thread thread1 = new Thread(runner1);
    Thread thread2 = new Thread(runner2);
    Thread thread3 = new Thread3();
//    thread1.run();
//    thread2.run();
    thread1.start();
    thread2.start();
    thread3.start();

    //方式1：相当于继承了Thread类，作为子类重写run()实现
    new Thread() {
      @Override
      public void run() {
        System.out.println("匿名内部类创建线程方式1...");
      }

      ;
    }.start();

    //方式2:实现Runnable,Runnable作为匿名内部类
    new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("匿名内部类创建线程方式2...");
      }
    }).start();


  }
}

class Runner1 implements Runnable { // 实现了Runnable接口，jdk就知道这个类是一个线程

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println("进入Runner1运行状态——————————" + i);
    }
  }
}

class Runner2 implements Runnable { // 实现了Runnable接口，jdk就知道这个类是一个线程

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println("进入Runner2运行状态==========" + i);
    }
  }
}


class Thread3 extends Thread {

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println("进入Runner3运行状态==========" + i);
    }
  }
}
