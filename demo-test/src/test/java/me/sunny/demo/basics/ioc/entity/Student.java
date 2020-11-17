package me.sunny.demo.basics.ioc.entity;

public class Student {
  private String name;
  private TClass tClass;

  public void sayHello() {
    System.out.println(
        "大家好，我是 " + this.name + "， 我的班级是 ：" + tClass.getCname() + "， 我的老师是： " + tClass.getTeacher().getName());
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TClass gettClass() {
    return tClass;
  }

  public void settClass(TClass tClass) {
    this.tClass = tClass;
  }
}