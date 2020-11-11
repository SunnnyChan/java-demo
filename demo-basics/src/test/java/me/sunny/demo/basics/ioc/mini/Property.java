package me.sunny.demo.basics.ioc.mini;

/**
 * 用于封装一个property标签
 * 属性数据
 **/
public class Property {
  private String name; // 属性名称
  private String value; // 属性的值
  private String ref; // 属性的引用

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getRef() {
    return ref;
  }

  public void setRef(String ref) {
    this.ref = ref;
  }
}