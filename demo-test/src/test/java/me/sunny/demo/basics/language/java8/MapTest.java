package me.sunny.demo.basics.language.java8;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class MapTest {

  @Test
  public void test() {
    Map<Integer, String> map = new HashMap<>();
    for (int i = 0; i < 10; i++) {
      // 会判断 key 是否已经存在，存在则直接返回 value， 否则 put， 再返回 value
      map.putIfAbsent(i, "val" + i);
    }
    // forEach 可以很方便地对 map 进行遍历操作
    map.forEach((key, value) -> System.out.println(value));

    for (int i = 0; i < 11; i++) {
      map.putIfAbsent(i, "val new " + i);
    }
    map.forEach((key, value) -> System.out.println(value));
  }

  @Test
  public void test2() {
    Map<Integer, String> map = new HashMap<>();
    for (int i = 0; i < 10; i++) {
      map.putIfAbsent(i, "val" + i);
    }
    // 如下：对 key 为 3 的值，内部会先判断值是否存在，存在，则做 value + key 的拼接操作
    map.computeIfPresent(3, (num, val) -> val + num);
    map.get(3); // val33

    // 先判断 key 为 9 的元素是否存在，存在，则做删除操作
    map.computeIfPresent(9, (num, val) -> null);
    map.containsKey(9); // false

    // computeIfAbsent(), 当 key 不存在时，才会做相关处理
    // 如下：先判断 key 为 23 的元素是否存在，不存在，则添加
    map.computeIfAbsent(23, num -> "val" + num);
    map.containsKey(23); // true

    // 先判断 key 为 3 的元素是否存在，存在，则不做任何处理
    map.computeIfAbsent(3, num -> "bam");
    map.get(3); // val33
  }
}
