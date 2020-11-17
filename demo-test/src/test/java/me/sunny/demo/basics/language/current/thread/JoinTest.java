package me.sunny.demo.basics.language.current.thread;

import org.junit.Test;

public class JoinTest {

  @Test
  public void test() throws InterruptedException {
    Thread thread1 = new Thread(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        System.out.println("Thread-1 执行完毕");
      }
    }, "Thread-1");

    Thread thread2 = new Thread(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        System.out.println("Thread-2 执行完毕");
      }
    }, "Thread-2");

    thread1.start();
    thread2.start();

    thread1.join();
    thread2.join();

    System.out.println("主线程执行完毕");
  }
}
