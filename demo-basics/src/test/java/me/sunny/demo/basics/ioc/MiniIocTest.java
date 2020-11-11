package me.sunny.demo.basics.ioc;

import me.sunny.demo.basics.ioc.entity.Student;
import me.sunny.demo.basics.ioc.mini.BeanFactory;
import me.sunny.demo.basics.ioc.mini.XmlBeanFactory;
import org.junit.Test;

public class MiniIocTest {

  @Test
  public void test() {
    // 创建IOC容器
    BeanFactory beanFactory = new XmlBeanFactory("applicationContext.xml");
    // 通过容器获取对象
    Student student = (Student)beanFactory.getBean("student");
    // 调用对象sayHello方法
    student.sayHello();
  }
}
