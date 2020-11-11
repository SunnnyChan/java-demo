package me.sunny.demo.basics.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;

/*
 *   fast-fail事件产生的条件：
 *   当多个线程对Collection进行操作时，若其中某一个线程通过iterator 去遍历集合时，
 *   该集合的内容被其他线程所改变，则会抛出ConcurrentModificationException异常。
 *
 *   fast-fail解决办法：
 *   通过util.concurrent集合包下的相应类去处理，则不会产生fast-fail事件。
 *
 *   使用ArrayList时，会产生fast-fail事件，抛出ConcurrentModificationException异常；
 *   使用时CopyOnWriteArrayList，不会产生fast-fail事件。
 */
public class FailFastTest {
  private List<String> list1 = new ArrayList<String>();
  private List<String> list2 = new CopyOnWriteArrayList<>();

  @Test
  public void testArrayListFastFail() {
    new Thread1().start();
    new Thread2().start();
  }

  @Test
  public void testCopyOnWriteArrayListNotFastFail() {
    new Thread3().start();
    new Thread4().start();
  }

private class Thread1 extends Thread {
    public void run() {
      int i = 0;
      while (i < 6) {
        list1.add(String.valueOf(i));
        printAll(list1);
        i++;
      }
    }
  }

  private class Thread2 extends Thread {
    public void run() {
      int i = 10;
      while (i < 16) {
        list1.add(String.valueOf(i));
        printAll(list1);
        i++;
      }
    }
  }

  private class Thread3 extends Thread {
    public void run() {
      int i = 0;
      while (i < 6) {
        list2.add(String.valueOf(i));
        printAll(list2);
        i++;
      }
    }
  }

  private class Thread4 extends Thread {
    public void run() {
      int i = 10;
      while (i < 16) {
        list2.add(String.valueOf(i));
        printAll(list2);
        i++;
      }
    }
  }

  private void printAll(List<String> list) {
    System.out.println("");
    String value;

    Iterator iter = list.iterator();
    while (iter.hasNext()) {
      value = (String)iter.next();
      System.out.print(value + ", ");
    }
  }
}
