package me.sunny.demo.basics.java8.repeatable;

import com.alibaba.fastjson.JSON;

import org.junit.Test;

public class RepeatableAnnotationTest {

  @Test
  public void test1() {
    @Hint("hint1")
    @Hint("hint2")
    class Person {

    }

    Hint hint = Person.class.getAnnotation(Hint.class);
    System.out.println(hint); // null

    Hints hints1 = Person.class.getAnnotation(Hints.class);
    System.out.println(JSON.toJSONString(hints1.value()));

    Hint[] hints2 = Person.class.getAnnotationsByType(Hint.class);
    System.out.println(JSON.toJSONString(hints2));
  }


  @Test
  public void test2() {
    @Hints({@Hint("hint1"), @Hint("hint2")})
    class Person {

    }

    Hint hint = Person.class.getAnnotation(Hint.class);
    System.out.println(hint); // null

    Hints hints1 = Person.class.getAnnotation(Hints.class);
    System.out.println(JSON.toJSONString(hints1.value()));

    Hint[] hints2 = Person.class.getAnnotationsByType(Hint.class);
    System.out.println(JSON.toJSONString(hints2));
  }
}
