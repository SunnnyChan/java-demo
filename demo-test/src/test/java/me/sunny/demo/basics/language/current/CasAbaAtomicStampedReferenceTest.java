package me.sunny.demo.basics.language.current;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CasAbaAtomicStampedReferenceTest {

  public static void main(String[] args) {
    AtomicStampedReference<Integer> reference = new AtomicStampedReference<>(10, 1);

    new Thread(() -> {
      int stamp = reference.getStamp();
      System.out.println("T1第一次拿到的版本号：" + stamp);
      // 先暂停1秒，等T2线程拿到相同的初始版本号
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      reference.compareAndSet(10, 101, reference.getStamp(), reference.getStamp() + 1);
      System.out.println("T1线程第一次操作后的版本号为："  + reference.getStamp());
      reference.compareAndSet(101, 10, reference.getStamp(), reference.getStamp() + 1);
      System.out.println("T1线程第二次操作后的版本号为："  + reference.getStamp());
    }, "T1").start();

    new Thread(() -> {
      int stamp = reference.getStamp();
      System.out.println("T2第一次拿到的版本号：" + stamp);
      // 先暂停3秒，等T1线程有充分的时候做一次ABA操作
      try {
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      boolean b = reference.compareAndSet(10, 2019, stamp, stamp + 1);
      System.out.println("当前内存中的最新值为：" + reference.getReference());
      System.out.println("T2线程在T1线程执行完ABA问题后在执行的结果为：" + b);
    }, "T2").start();
  }
}
