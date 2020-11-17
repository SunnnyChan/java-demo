package me.sunny.demo.basics.language.current.thread;

import java.util.concurrent.Callable;

import org.junit.Test;

public class CallableTest {

  @Test
  public void test() {
    Callable<String> callable = () -> {
      // Perform some computation
      Thread.sleep(2000);
      return "Return some result";
    };
  }
}
