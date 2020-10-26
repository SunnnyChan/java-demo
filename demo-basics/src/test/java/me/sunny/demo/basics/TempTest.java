package me.sunny.demo.basics;


import org.junit.Assert;
import org.junit.Test;

public class TempTest {

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
