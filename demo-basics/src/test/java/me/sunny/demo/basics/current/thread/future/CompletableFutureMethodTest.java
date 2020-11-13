package me.sunny.demo.basics.current.thread.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import me.sunny.demo.basics.current.thread.future.scenario.Product;
import org.junit.Test;

public class CompletableFutureMethodTest {
  @Test
  public void testRunAsync() throws InterruptedException, ExecutionException {
    CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
      try {
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        throw new IllegalStateException(e);
      }
      System.out.println("运行在一个单独的线程当中");
    });
    System.out.println(future.get());
  }

  @Test
  public void testSupplyAsync() throws InterruptedException, ExecutionException {
    CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
      try {
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        throw new IllegalStateException(e);
      }
      System.out.println("运行在一个单独的线程当中");
      return "我有返回值";
    });

    System.out.println(future.get());
  }

  @Test
  public void testSupplyAsyncCallback() throws InterruptedException, ExecutionException {
    System.out.println("main Thread : " + Thread.currentThread().getId());
    CompletableFuture<String> comboText = CompletableFuture
        .supplyAsync(
            () -> {
              //可以注释掉做快速返回 start
              try {
                TimeUnit.SECONDS.sleep(1);
              } catch (InterruptedException e) {
                throw new IllegalStateException(e);
              }
              System.out.println("async Thread : " + Thread.currentThread().getId());
              return "赞";
            })
        .thenApply(
            first -> {
              System.out.println("async Thread : " + Thread.currentThread().getId());
              return first + ", 在看";
            })
        .thenApply(
            second -> {
              System.out.println("async Thread : " + Thread.currentThread().getId());
              return second + ", 转发";
            });

    System.out.println("三连有没有？");
    System.out.println(comboText.get());
  }

  @Test
  public void tesThenAccept() throws InterruptedException, ExecutionException {
    final CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(
        // 模拟远端API调用，这里只返回了一个构造的对象
        () -> Product.builder().id(12345L).name("颈椎/腰椎治疗仪").build())
        .thenAccept(product -> {
          System.out.println("获取到远程API产品名称 : " + product.getName());
        });
    voidCompletableFuture.get();
  }

  @Test
  public void tesThenRun() throws InterruptedException, ExecutionException {
    CompletableFuture
        .supplyAsync(() -> {
          System.out.println("前序操作");
          return "前需操作结果";
        })
        .thenRun(() -> {
          System.out.println("后续操作");
        });
  }

  @Test
  public void tesThenCombine() throws InterruptedException, ExecutionException {
    CompletableFuture<Double> weightFuture = CompletableFuture.supplyAsync(() -> 65.0);
    CompletableFuture<Double> heightFuture = CompletableFuture.supplyAsync(() -> 183.8);

    CompletableFuture<Double> combinedFuture = weightFuture
        .thenCombine(heightFuture, (weight, height) -> {
          Double heightInMeter = height / 100;
          return weight / (heightInMeter * heightInMeter);
        });

    System.out.println("身体BMI指标 - " + combinedFuture.get());
  }

  @Test
  public void tesExceptionally() throws InterruptedException, ExecutionException {
    Integer age = -1;

    CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
      if (age < 0) {
        throw new IllegalArgumentException("何方神圣？");
      }
      if (age > 18) {
        return "大家都是成年人";
      } else {
        return "未成年禁止入内";
      }
    }).thenApply((str) -> {
      System.out.println("游戏开始");
      return str;
    }).exceptionally(ex -> {
      System.out.println("必有蹊跷，来者" + ex.getMessage());
      return "Unknown!";
    });

    System.out.println(maturityFuture.get());
  }

  @Test
  public void tesHandle() throws InterruptedException, ExecutionException {
    Integer age = -1;

    CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
      if (age < 0) {
        throw new IllegalArgumentException("何方神圣？");
      }
      if (age > 18) {
        return "大家都是成年人";
      } else {
        return "未成年禁止入内";
      }
    }).thenApply((str) -> {
      System.out.println("游戏开始");
      return str;
    }).handle((res, ex) -> {
      if (ex != null) {
        System.out.println("必有蹊跷，来者" + ex.getMessage());
        return "Unknown!";
      }
      return res;
    });

    System.out.println(maturityFuture.get());
  }
}
