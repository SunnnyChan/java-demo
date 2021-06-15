package me.sunny.demo.basics.java8;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

public class DataHandle {
  public static class Apple {
    private Integer id;
    private String name;
    private BigDecimal money;
    private Integer num;
    public Apple(Integer id, String name, BigDecimal money, Integer num) {
      this.id = id;
      this.name = name;
      this.money = money;
      this.num = num;
    }

    public Integer getId() {
      return id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public BigDecimal getMoney() {
      return money;
    }

    public void setMoney(BigDecimal money) {
      this.money = money;
    }

    public Integer getNum() {
      return num;
    }

    public void setNum(Integer num) {
      this.num = num;
    }
  }

  public static void main(String[] args) {
    List<Apple> apples = new ArrayList<>();//存放apple对象集合

    Apple apple1 =  new Apple(1,"苹果1",new BigDecimal("3.25"),10);
    Apple apple12 = new Apple(1,"苹果2",new BigDecimal("1.35"),20);
    Apple apple2 =  new Apple(2,"香蕉",new BigDecimal("2.89"),30);
    Apple apple3 =  new Apple(3,"荔枝",new BigDecimal("9.99"),40);

    apples.add(apple1);
    apples.add(apple12);
    apples.add(apple2);
    apples.add(apple3);

    // //List 以ID分组 Map<Integer,List<Apple>>
    Map<Integer, List<Apple>> groupBy
        = apples.stream().collect(Collectors.groupingBy(Apple::getId));
    System.err.println("groupBy:" + JSON.toJSONString(groupBy));
      // {1=[Apple{id=1, name='苹果1', money=3.25, num=10}, Apple{id=1, name='苹果2', money=1.35, num=20}],
      // 2=[Apple{id=2, name='香蕉', money=2.89, num=30}],
      // 3=[Apple{id=3, name='荔枝', money=9.99, num=40}]}

    // List -> Map
    Map<Integer, Apple> appleMap
        = apples.stream().collect(Collectors.toMap(Apple::getId, a -> a,(k1,k2)->k1));
    System.err.println("Map:" + JSON.toJSONString(appleMap));

    //过滤出符合条件的数据
    List<Apple> filterList
        = apples.stream().filter(a -> a.getName().equals("香蕉")).collect(Collectors.toList());
    System.err.println("filterList:" + JSON.toJSONString(filterList));

    //计算 总金额
    BigDecimal totalMoney = apples.stream().map(Apple::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
    System.err.println("totalMoney:"+totalMoney);  //totalMoney:17.48

    Optional<Apple> maxApple = apples.stream().collect(Collectors.maxBy(Comparator.comparing(Apple::getNum)));
    if (maxApple.isPresent()) {System.out.println(JSON.toJSONString(maxApple.get()));}

    Optional<Apple> minApple = apples.stream().collect(Collectors.minBy(Comparator.comparing(Apple::getNum)));
    if (maxApple.isPresent()) {System.out.println(JSON.toJSONString(minApple.get()));}

    // 根据id去重
    List<Apple> unique = apples.stream().collect(
        collectingAndThen(
            toCollection(() -> new TreeSet<>(comparingLong(Apple::getId))), ArrayList::new)
    );
    System.err.println("unique:" + JSON.toJSONString(unique));
  }
}
