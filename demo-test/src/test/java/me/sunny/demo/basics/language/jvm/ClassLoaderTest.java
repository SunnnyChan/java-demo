package me.sunny.demo.basics.language.jvm;

import org.junit.Test;

public class ClassLoaderTest {
  @Test
  public void testClassLoaderForeachPath() {
    System.out.println();
    //BoostrapClassLoader
    String[] split = System.getProperty("sun.boot.class.path").split(":");
    for (String data : split) {
      System.out.println(data);
    }
    System.out.println("===================");
    //ExeClassLoader
    String[] split1 = System.getProperty("java.ext.dirs").split(":");
    for (String data : split1) {
      System.out.println(data);
    }
    System.out.println("===================");
    //AppClassLoader
    String[] split2 = System.getProperty("java.class.path").split(":");
    for (String data : split2) {
      System.out.println(data);
    }
    System.out.println("================");
  }

  @Test
  public void testClassLoaderPath() {
    System.out.println(ClassLoaderTest.class.getClassLoader());
    System.out.println(ClassLoaderTest.class.getClassLoader().getParent());
    System.out.println(ClassLoaderTest.class.getClassLoader().getParent().getParent());
    System.out.println("------------------------------------");
    System.out.println(int.class.getClassLoader());
    System.out.println(Long.class.getClassLoader());
  }
}
