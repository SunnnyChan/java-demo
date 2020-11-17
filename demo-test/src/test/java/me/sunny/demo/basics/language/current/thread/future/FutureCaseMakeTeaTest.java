package me.sunny.demo.basics.language.current.thread.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * 假设：
 * 洗水壶 1 分钟
 * 烧开水 15 分钟
 * 洗茶壶 1 分钟
 * 洗茶杯 1 分钟
 * 拿茶叶 2 分钟
 *
 * 如果串行总共需要 20 分钟，烧开水期间，可以洗茶壶/洗茶杯/拿茶叶，这样总共需要 16 分钟，节约了 4分钟时间。
 */
public class FutureCaseMakeTeaTest {

  @Test
  public void test() throws ExecutionException, InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(2);

    // 创建线程1的FutureTask
    FutureTask<String> ft1 = new FutureTask<String>(new T1Task());
    // 创建线程2的FutureTask
    FutureTask<String> ft2 = new FutureTask<String>(new T2Task());

    executorService.submit(ft1);
    executorService.submit(ft2);

    System.out.println(ft1.get() + " , " + ft2.get());
    System.out.println("开始泡茶");

  }

  static class T1Task implements Callable<String> {
    @Override
    public String call() throws Exception {
      System.out.println("T1:洗水壶...");
      TimeUnit.SECONDS.sleep(1);

      System.out.println("T1:烧开水...");
      TimeUnit.SECONDS.sleep(15);

      return "T1:开水已备好";
    }
  }

  static class T2Task implements Callable<String> {
    @Override
    public String call() throws Exception {
      System.out.println("T2:洗茶壶...");
      TimeUnit.SECONDS.sleep(1);

      System.out.println("T2:洗茶杯...");
      TimeUnit.SECONDS.sleep(2);

      System.out.println("T2:拿茶叶...");
      TimeUnit.SECONDS.sleep(1);
      return "T2:福鼎白茶拿到了";
    }
  }
}
