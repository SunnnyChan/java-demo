package me.sunny.demo.basics.gc;

import com.alibaba.fastjson.JSON;

import org.junit.Test;

public class FinalizeTest {
  private static FinalizeTest obj;

  //当执行GC时，会执行 finalize 方法，且只执行一次
  @Override
  protected void finalize() throws Throwable {
    super.finalize();
    System.out.println("FinalizeTest finalize called");
    //当执行GC时，会执行 finalize 方法，下面代码作用是将值为null的object复活，变成了可触及性
    obj = this;
  }

  @Override
  public String toString() {
    return "I am FinalizeTest";
  }

  @Test
  public void test() throws InterruptedException {
    obj = new FinalizeTest();

    // 可复活
    obj = null;
    System.out.println("First time GC");
    System.gc();
    Thread.sleep(1000);
    System.out.println("obj : " + JSON.toJSONString(obj));

    // 不可复活
    obj = null;
    System.out.println("Second time GC");
    System.gc();
    Thread.sleep(1000);
    System.out.println("obj : " + JSON.toJSONString(obj));
  }
}
