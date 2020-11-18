package me.sunny.demo.basics.guava;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.common.base.Predicates;
import org.junit.Test;

public class RetryTest {
  @Test
  public void test() throws Exception {
    //定义重试机制
    Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
        //retryIf 重试条件
        .retryIfException()
        .retryIfRuntimeException()
        .retryIfExceptionOfType(Exception.class)
        .retryIfException(Predicates.equalTo(new Exception()))
        .retryIfResult(Predicates.equalTo(false))
        // 等待策略：每次请求间隔1s
        .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))
        // 停止策略 : 尝试请求6次
        .withStopStrategy(StopStrategies.stopAfterAttempt(6))
        .build();

    //定义请求实现
    Callable<Boolean> callable = new Callable<Boolean>() {
      int times = 1;

      @Override
      public Boolean call() throws Exception {
        System.out.println("call times= " + times);
        times++;

        if (times == 2) {
          throw new NullPointerException();
        } else if (times == 3) {
          throw new Exception();
        } else if (times == 4) {
          throw new RuntimeException();
        } else if (times == 5) {
          return false;
        } else {
          return true;
        }

      }
    };
    //利用重试器调用请求
    retryer.call(callable);
  }
}
