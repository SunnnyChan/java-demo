package me.sunny.demo.basics.current.sync;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {
  // 创建 CyclicBarrier 实例，计数器的值设置为2
  // private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

  // 创建 CyclicBarrier 实例，计数器的值设置为2
  // private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
  //  System.out.println("全部运行结束");
  // });

  // 创建单线程线程池
  private static Executor executor = Executors.newSingleThreadExecutor();

  // 创建 CyclicBarrier 实例，计数器的值设置为2
  private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
    executor.execute(() -> gather());
  });

  private static void gather() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("全部运行结束");
  }

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(2);

    executorService.submit(() -> {
      try {
        System.out.println(Thread.currentThread() + "第一回合");
        Thread.sleep(1000);
        cyclicBarrier.await();

        System.out.println(Thread.currentThread() + "第二回合");
        Thread.sleep(2000);
        cyclicBarrier.await();

        System.out.println(Thread.currentThread() + "第三回合");
      } catch (InterruptedException | BrokenBarrierException e) {
        e.printStackTrace();
      }
    });

    executorService.submit(() -> {
      try {
        System.out.println(Thread.currentThread() + "第一回合");
        Thread.sleep(2000);
        cyclicBarrier.await();

        System.out.println(Thread.currentThread() + "第二回合");
        Thread.sleep(1000);
        cyclicBarrier.await();

        System.out.println(Thread.currentThread() + "第三回合");
      } catch (InterruptedException | BrokenBarrierException e) {
        e.printStackTrace();
      }
    });

    executorService.shutdown();
  }
}
