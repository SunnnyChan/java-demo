package me.sunny.demo.basics.language.java8;

import java.math.BigDecimal;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import me.sunny.demo.basics.language.java8.scenario.Student;
import org.junit.Test;

public class LambdaTest {

  public static void main(String[] args) {
    int number = 6;
    InterNew<Integer,String>inn = (t) -> String.valueOf(t + number);
    System.out.println("访问局部变量：" + inn.interNew(7));
  }

  /**
   * 函数式接口使用
   */
  @Test
  public void testFunctionInterface() {
    Predicate<Integer> predicate = x -> x > 185;
    Student student = new Student("Sunny", 23, 175);
    System.out.println("Sunny的身高高于185吗？：" + predicate.test(student.getStature()));

    Consumer<String> consumer = System.out::println;
    consumer.accept("命运由我不由天");

    Function<Student, String> function = Student::getName;
    String name = function.apply(student);
    System.out.println(name);

    Supplier<Integer> supplier = () -> Integer.valueOf(BigDecimal.TEN.toString());
    System.out.println(supplier.get());

    UnaryOperator<Boolean> unaryOperator = ugly -> !ugly;
    Boolean apply2 = unaryOperator.apply(true);
    System.out.println(apply2);

    BinaryOperator<Integer> operator = (x, y) -> x * y;
    Integer integer = operator.apply(2, 3);
    System.out.println(integer);

    testFunctionAsParam(() -> "一个演示的函数式接口");
  }

  private void testFunctionAsParam(Worker worker) {
    String work = worker.work();
    System.out.println(work);
  }

  public interface Worker {
    String work();
  }
}

interface InterNew<String, Integer> {
  Integer interNew(String string);
}

