package me.sunny.demo.basics.language.current.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {
  public static void main(String[] args) {
    // 1. 创建线程池
    ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200,
        TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));

    // 修改任务数的上线为 10，11，16会有不同现象
    // 当要处理的任务是10个时，只有创建5个线程，然后处理两次。
    // 如果任务是11个时，只有创建6个线程，然后处理两次。
    // 如果任务是16个时，创建10个线程，然后处理两次，但有一个任务会报异常，实际只处理了15个。
    for (int i = 0; i < 16; i++) {
      // 2. 创建任务
      Runnable task = new Runnable() {
        @Override
        public void run() {
          System.out.println("Thread" + Thread.currentThread().getId() + " 执行任务...");
          try {
            Thread.sleep(2000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      };
      executor.execute(task);// 3. 任务交给线程池执行
    }
    executor.shutdown();// 4. 关闭线程池
  }
}
