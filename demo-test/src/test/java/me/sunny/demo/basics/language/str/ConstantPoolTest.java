package me.sunny.demo.basics.language.str;

import org.junit.Test;

public class ConstantPoolTest {

  private static final String hel = "Hel";
  private static final String lo = "lo";

  private static final String hel1;
  private static final String lo1;

  static {
    hel1 = "Hel";
    lo1 = "lo";
  }

  @Test
  public void test() {

    String hello = "Hello";
    String hel = "Hel";
    String lo = "lo";

    // true
    System.out.println((hello == "Hello"));
    // true
    System.out.println((Other.hello == hello));
    // true
    // 只有文本字符串之间使用“+”连接产生的新对象才会被加入字符串池中。
    System.out.println((hello == ("Hel" + "lo")));
    // false
    System.out.println((hello == ("Hel" + lo)));
    // false
    System.out.println((hello == (hel + lo)));
    // true
    System.out.println(hello == ("Hel" + lo).intern());
    // false
    System.out.println((hello == (ConstantPoolTest.hel + lo)));
    // false
    System.out.println((hello == (ConstantPoolTest.hel1 + lo)));
    // true
    // A和B都是常量，值是固定的，因此s的值也是固定的，它在类被编译时就已经确定了。
    // 即 String s=A+B; 等同于：String s="ab"+"cd";
    System.out.println((hello == (ConstantPoolTest.hel + ConstantPoolTest.lo)));
    // false
    // A和B虽然被定义为常量，但是它们都没有马上被赋值。
    // 在运算出s的值之前，他们何时被赋值，以及被赋予什么样的值，都是个变数。
    // 因此A和B在被赋值之前，性质类似于一个变量。
    // 那么s就不能在编译期被确定，而只能在运行时被创建了。
    System.out.println((hello == (ConstantPoolTest.hel1 + ConstantPoolTest.lo1)));
  }

  static class Other {
    static String hello = "Hello";
  }
}
