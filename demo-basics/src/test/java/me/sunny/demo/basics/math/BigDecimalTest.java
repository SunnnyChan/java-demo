package me.sunny.demo.basics.math;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.junit.Test;

public class BigDecimalTest {
  @Test
  public void testOperations() {
    BigDecimal a = new BigDecimal("0.1");
    BigDecimal b = new BigDecimal("0.2");

    System.out.println("a + b : " + a.add(b));
    System.out.println("a - b : " + a.subtract(b));
    System.out.println("a * b : " + a.multiply(b));
    System.out.println("a / b : " + a.divide(b));
    System.out.println("a compare b : " + a.compareTo(b));
  }

  @Test
  public void testPitfall() {
    BigDecimal a = new BigDecimal(0.1);
    System.out.println("a values is:" + a);
    System.out.println("=====================");
    BigDecimal b = new BigDecimal("0.1");
    System.out.println("b values is:" + b);
  }

  @Test
  public void testFormat() {
    NumberFormat currency = NumberFormat.getCurrencyInstance(); //建立货币格式化引用
    NumberFormat percent = NumberFormat.getPercentInstance();  //建立百分比格式化引用
    percent.setMaximumFractionDigits(3); //百分比小数点最多3位

    BigDecimal loanAmount = new BigDecimal("15000.48"); //贷款金额
    BigDecimal interestRate = new BigDecimal("0.008"); //利率
    BigDecimal interest = loanAmount.multiply(interestRate); //相乘

    System.out.println("贷款金额:\t" + currency.format(loanAmount));
    System.out.println("利率:\t" + percent.format(interestRate));
    System.out.println("利息:\t" + currency.format(interest));
  }

  @Test
  public void testFormat2() {
    System.out.println(formatToNumber(new BigDecimal("3.435")));
    System.out.println(formatToNumber(new BigDecimal(0)));
    System.out.println(formatToNumber(new BigDecimal("0.00")));
    System.out.println(formatToNumber(new BigDecimal("0.001")));
    System.out.println(formatToNumber(new BigDecimal("0.006")));
    System.out.println(formatToNumber(new BigDecimal("0.206")));
  }

  public String formatToNumber(BigDecimal obj) {
    DecimalFormat df = new DecimalFormat("#.00");
    if (obj.compareTo(BigDecimal.ZERO) == 0) {
      return "0.00";
    } else if (obj.compareTo(BigDecimal.ZERO) > 0 && obj.compareTo(new BigDecimal(1)) < 0) {
      return "0" + df.format(obj).toString();
    } else {
      return df.format(obj).toString();
    }
  }

}
