package me.sunny.demo.basics.language.current.lock;

import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class TestReentrantLock {
  private static ReentrantLock reentrantLock = new ReentrantLock(true);

  @Test
  public void test() {
    // 比如同一时间，只允许一个线程创建订单
    reentrantLock.lock();
    // 通常，lock 之后紧跟着 try 语句
    try {
      // 临界区代码，同一时间只能有一个线程进来(获取到锁的线程)，
      // 其他的线程在lock()方法上阻塞，等待获取到锁，再进来。
      System.out.println("I get Lock!");
    } finally {
      // 释放锁
      reentrantLock.unlock();
    }
  }

}
