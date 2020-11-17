package me.sunny.demo.basics.language.java8;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

import com.alibaba.fastjson.JSON;

import org.junit.Test;

import static java.util.stream.Collectors.summarizingInt;

public class StreamCollectTest {

  @Test
  public void summarizing(){
    List<Integer> list = Arrays.asList(1, 10, 23, 25);
    IntSummaryStatistics l = list.stream().collect(summarizingInt(Integer::intValue));
    System.out.println(JSON.toJSONString(l));

    double averaget = l.getAverage();
    long count = l.getCount();
    int max = l.getMax();
    int min = l.getMin();
    long sum = l.getSum();
  }
}
