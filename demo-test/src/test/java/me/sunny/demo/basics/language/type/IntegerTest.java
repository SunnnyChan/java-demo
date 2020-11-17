package me.sunny.demo.basics.language.type;


import org.junit.Assert;
import org.junit.Test;

public class IntegerTest {

  private Integer testInteger = 1;

  @Test
  public void testIntegerParseInt() {
    String  testStr = "111";
    Assert.assertEquals(Integer.parseInt(testStr), 111);
    testStr = "-111";

    Assert.assertEquals(Integer.parseInt(testStr), -111);
    testStr = "-2147483649";
    Integer.parseInt(testStr);
  }
}
