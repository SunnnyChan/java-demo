package me.sunny.demo.basics.current.collection;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

public class TestConcurrentHashMapHashMap {
  private ConcurrentHashMap<String, String>  concurrentHashMap = new ConcurrentHashMap<>();

  @Test
  public void test() {
    concurrentHashMap.put("name", "Sunny");
    System.out.println(concurrentHashMap.toString());
  }
}
