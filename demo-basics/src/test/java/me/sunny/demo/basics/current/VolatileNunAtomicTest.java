package me.sunny.demo.basics.current;

public class VolatileNunAtomicTest {
  public static class TestData {
    volatile int num1 = 0;

    int num2 = 0;

    public void updateNum1() {
      num1++;
    }

    //synchronized
    public synchronized void updateNum2() {
      num2++;
    }
  }

  public static void main(String[] args) {

    final TestData testData = new TestData();

    for (int i = 1; i <= 10; i++) {
      new Thread(new Runnable() {
        @Override
        public void run() {
          for (int j = 1; j <= 1000; j++) {
            testData.updateNum1();
            testData.updateNum2();
          }
        }
      }).start();
    }

    while (Thread.activeCount() > 2) {
      Thread.yield();
    }
    System.out.println("volatile 最终结果：" + testData.num1);
    System.out.println("synchronized 最终结果：" + testData.num2);
  }
}
