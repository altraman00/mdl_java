package com.mdl.demo.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.demo.thread
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年08月27日 17:54
 * ----------------- ----------------- -----------------
 */
public class CallableFutureTest {

  public static void main(String[] args) {

    long startTime = System.currentTimeMillis();

    Callable<Integer> callable = new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        //模拟业务耗时时间
        Thread.sleep(2000);
        int result = 1 + 2;
        return result;
      }
    };

    FutureTask<Integer> futureTask = new FutureTask<>(callable);
    Thread t1 = new Thread(futureTask);
    t1.start();

    //现在加入Thread运行的是一个模拟远程调用的耗时服务，并且依赖他的计算结果（比如网络计算）
    try {
      Thread.sleep(3000);

      int a = 3 + 5;
      Integer result = futureTask.get();
      //模拟主线程依赖子线程的运行结果
      System.out.println("result = " + (a + result));
      long endTime = System.currentTimeMillis();

      System.out.println("time :" + (endTime - startTime));

    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }


  }

}
