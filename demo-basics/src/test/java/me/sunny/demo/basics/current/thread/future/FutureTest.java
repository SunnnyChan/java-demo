package me.sunny.demo.basics.current.thread.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.junit.Test;

public class FutureTest {
  @Test
  public void testFutureExecByThread() throws InterruptedException, ExecutionException {
    FutureTask<String> futureTask = new FutureTask<>(() -> {
      System.out.println("进入 Callable 的 call 方法");
      // 模拟子线程任务
      Thread.sleep(5000);
      return "Hello from Callable";
    });

    System.out.println("提交子线程执行");
    Thread thread = new Thread(futureTask);
    thread.start();

    System.out.println("主线程继续执行");

    System.out.println("主线程等待获取 Future 结果");
    // Future.get() blocks until the result is available
    String result = futureTask.get();
    System.out.println("主线程获取到 Future 结果: " + result);
  }

  @Test
  public void testFutureExecByThreadPool() throws InterruptedException, ExecutionException {
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    // 使用 Callable ，可以获取返回值
    Callable<String> callable = () -> {
      System.out.println("进入 Callable 的 call 方法");
      // 模拟子线程任务
      Thread.sleep(5000);
      return "Hello from Callable";
    };

    System.out.println("提交 Callable 到线程池");
    Future<String> future = executorService.submit(callable);

    System.out.println("主线程继续执行");

    System.out.println("主线程等待获取 Future 结果");
    // Future.get() blocks until the result is available
    String result = future.get();
    System.out.println("主线程获取到 Future 结果: " + result);

    executorService.shutdown();
  }

  @Test
  public void testFutureTask() {
    FutureTask futureTask;
  }
}
