package me.sunny.demo.basics.language.current;

import org.junit.Test;

public class VolatileVisibilityTest {
  public static class TestData {
    volatile int num = 0;

    public void updateNum() {
      num = 1;
    }
  }

  @Test
  public void test() {

    final TestData testData = new TestData();
    System.out.println("MainThread num-->" + testData.num);

    new Thread(new Runnable() {
      @Override
      public void run() {
        //子线程读取数据
        System.out.println("ChildThread num-->" + testData.num);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        // 子线程改写数据
        testData.updateNum();
        System.out.println("ChildThread update num-->" + testData.num);
      }
    }).start();
    System.out.println("MainThread num-->" + testData.num);
    // 以下每次读取，都会刷新主线本地空间中num变量
    while (testData.num == 0) {

    }

    System.out.println("MainThread num-->" + testData.num);
  }
}
