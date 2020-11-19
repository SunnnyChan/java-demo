package me.sunny.demo.basics.language.current.sync;

import java.util.concurrent.Exchanger;

public class ExchangerTest {
  private static Exchanger<String> exchanger = new Exchanger<String>();

  public static void main(String[] args) throws InterruptedException {
    System.out.println("准备交易，一手交钱一手交货...");
    // 卖家
    new Thread() {
      public void run() {
        String goods = "电脑";
        System.out.println(getName() + " 卖家到了，已经准备好货：" + goods);
        try {
          String money = exchanger.exchange(goods);
          System.out.println(getName() + " 卖家收到钱：" + money);
        } catch (Exception e) {
          e.printStackTrace();
        }
      };
    }.start();

    Thread.sleep(3000);

    // 买家
    new Thread() {
      public void run() {
        try {
          String money = "$1000";
          System.out.println(getName() + " 买家到了，已经准备好钱：" + money);
          String goods = exchanger.exchange(money);
          System.out.println(getName() + " 买家收到货：" + goods);
        } catch (Exception e) {
          e.printStackTrace();
        }
      };
    }.start();
  }
}
