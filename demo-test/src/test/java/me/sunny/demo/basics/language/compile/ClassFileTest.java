package me.sunny.demo.basics.language.compile;

public class ClassFileTest {

  private int a = 1;

  public int add() {
    int b = 2;
    int c = a + b;
    System.out.println(c);
    return c;
  }
}