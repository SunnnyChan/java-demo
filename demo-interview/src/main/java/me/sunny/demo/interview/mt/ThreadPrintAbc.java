package me.sunny.demo.interview.mt;

public class ThreadPrintAbc {
  public static volatile Integer l = 1;
  public static volatile Integer la = 0;
  public static volatile Integer lb = 0;

  public static void main(String[] args) {
    Thread t1 = new Thread(new C1());
    Thread t2 = new Thread(new C2());
    Thread t3 = new Thread(new C3());
    t1.start();
    t2.start();
    t3.start();
  }

  static class C1 implements Runnable {
    @Override
    public void run() {
      for (int i = 0; i < 10; i++) {
        while (true) {
          if (l == 1) {
            System.out.println(Thread.currentThread().getId() + " A");
            la = 1;
            l = 0;
            break;
          }
        }
      }
    }
  }

  static class C2 implements Runnable {
    @Override
    public void run() {
      for (int i = 0; i < 10; i++) {
        while (true) {
          if (la == 1) {
            System.out.println(Thread.currentThread().getId() + " B");
            la = 0;
            lb = 1;
            break;
          }
        }
      }
    }
  }

  static class C3 implements Runnable {
    @Override
    public void run() {
      for (int i = 0; i < 10; i++) {
        while (true) {
          if (lb == 1) {
            System.out.println(Thread.currentThread().getId() + " C");
            l = 1;
            lb = 0;
            break;
          }
        }
      }
    }
  }
}
