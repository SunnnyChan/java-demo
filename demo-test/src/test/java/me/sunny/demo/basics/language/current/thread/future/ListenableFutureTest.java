package me.sunny.demo.basics.language.current.thread.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class ListenableFutureTest {

  private static final int processors = Runtime.getRuntime().availableProcessors();

  private static final ThreadFactory threadFactory = new ThreadFactoryBuilder()
      .setDaemon(true)
      .setNameFormat("ListenableFutureAdapter-thread-%d")
      .build();

  private static final ExecutorService pool =
      Executors.newFixedThreadPool(processors, threadFactory);

  private static final  ListeningExecutorService executorService
      = MoreExecutors.listeningDecorator(pool);


  public static void main(String[] args) throws InterruptedException {
    Callable<String> task1 =  new Callable<String>() {
      @Override
      public String call() throws Exception {
        // 执行成功
        System.out.println("task1 handling");
        return "task1 OK";
      }
    };

    Callable<String> task2 =  new Callable<String>() {
      @Override
      public String call() throws Exception {
        // 执行失败
        System.out.println("task2 handling");
        throw new Exception("task2 failed");
      }
    };

    ListenableFuture<String> futureTask1 = executorService.submit(task1);
    ListenableFuture<String> futureTask2 = executorService.submit(task2);

    FutureCallbackImpl callback1 = new FutureCallbackImpl();
    FutureCallbackImpl callback2 = new FutureCallbackImpl();

    Futures.addCallback(futureTask1, callback1, pool);
    Futures.addCallback(futureTask2, callback2, pool);

    TimeUnit.SECONDS.sleep(1);
    pool.shutdown();
  }

  private static class FutureCallbackImpl implements FutureCallback<String> {

    @Override
    public void onSuccess(String result) {
      System.out.println("Success callback, result  : " + result);
    }

    @Override
    public void onFailure(Throwable t) {
      System.err.println("Failed callback, error message : " + t.getMessage());
    }
  }
}
