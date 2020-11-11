package me.sunny.demo.basics.current.aqs;

import org.junit.Test;

public class AqsTest {
  // 共享变量
  private int count = 0;
  // 自定义锁
  private AqsUdfLock aqsUdfLock = new AqsUdfLock();

  @Test
  public void testAqsUdfLock() throws InterruptedException {
    // 创建线程类
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        try {
          aqsUdfLock.lock();
          for (int i = 0; i < 10000; i++) {
            count++;
          }
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          aqsUdfLock.unlock();
        }
      }
    };
    //两个线程同时处理
    Thread thread1 = new Thread(runnable);
    Thread thread2 = new Thread(runnable);
    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();

    System.out.println(count);
  }
}
