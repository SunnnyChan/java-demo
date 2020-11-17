package me.sunny.demo.basics.language.current.thread.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

public class CompletableFutureTest {
  // CompletableFuture的异步执行
  private static Integer calc(Integer para) {
    try {
      // 模拟一个长时间的执行
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      System.out.println("error message : " + e.getMessage());
    }
    return para * para;
  }

  /**
   * CompletableFuture的异步执行
   */
  @Test
  public void testAsyncExec() throws InterruptedException, ExecutionException {
    final CompletableFuture<Integer> future = CompletableFuture
        .supplyAsync(() -> calc(50));
    System.out.println(future.get());
  }

  /**
   * CompletableFuture的流式调用
   */
  @Test
  public void testCallAsStream() throws InterruptedException, ExecutionException {
    CompletableFuture<Void> fu = CompletableFuture
        .supplyAsync(() -> calc(50))
        .thenApply((i) -> Integer.toString(i))
        .thenApply((str) -> "\"" + str + "\"")
        .thenAccept(System.out::println);
    fu.get();
  }

  /**
   * 组合多个 CompletableFuture
   */
  @Test
  public void testCombineMutilCompletableFuture() throws InterruptedException, ExecutionException {
    CompletableFuture<Void> fu = CompletableFuture
        .supplyAsync(() -> calc(50))
        .thenCompose((i) -> CompletableFuture.supplyAsync(() -> calc(i)))
        .thenApply((str) -> "\"" + str + "\"")
        .thenAccept(System.out::println);
    fu.get();
  }
}
