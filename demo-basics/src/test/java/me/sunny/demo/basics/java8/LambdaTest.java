package me.sunny.demo.basics.java8;

public class LambdaTest {
  public static void main(String[] args) {
    int number = 6;
    InterNew<Integer,String>inn = (t) -> String.valueOf(t + number);
    System.out.println("访问局部变量：" + inn.interNew(7));
  }
}

interface InterNew<String, Integer> {
  Integer interNew(String string);
}

