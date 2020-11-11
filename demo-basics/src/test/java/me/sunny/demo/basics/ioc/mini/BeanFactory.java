package me.sunny.demo.basics.ioc.mini;

/**
 * 容器的基础接口
 * 提供容器最基本的功能
 */
public interface BeanFactory {
  // 核心方法 获取对象
  Object getBean(String beanName);
}