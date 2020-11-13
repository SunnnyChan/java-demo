package me.sunny.demo.basics.current.thread.future;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import me.sunny.demo.basics.current.thread.future.scenario.FactorialCalculator;
import org.junit.Test;

public class FutureCaseFactorialCalculatorTest {
  @Test
  public void test() {
    ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(2);

    List<Future<Integer>> resultList = new ArrayList<>();

    Random random = new Random();
    // 并行计算四4个数的阶乘
    for (int i = 0; i < 4; i++) {
      // 随机生成一个数
      Integer number = random.nextInt(10);
      // 为每次计算，创建一个计算器（任务）
      FactorialCalculator calculator = new FactorialCalculator(number);
      // 把任务提交给线程池调度执行
      Future<Integer> result = executor.submit(calculator);
      // 保存计算结果
      resultList.add(result);
    }
    // 输出计算结果
    for (Future<Integer> future : resultList) {
      try {
        System.out.println("Future result is : " + future.get() + ", And Task done is : " + future.isDone() + " .");
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    }
    //shut down the executor service now
    executor.shutdown();
  }
}
