package me.sunny.demo.basics.ioc.mini;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 基础容器的核心实现
 * 提供 beanDefinitionMap 存储bean的定义
 * 提供 singletonObjects 存储bean的对象实例
 **/
public class DefaultListableBeanFactory implements BeanFactory {
  // 提供 beanDefinitionMap 存储bean的定义
  private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
  // 提供 singletonObjects 存储bean的对象实例 (scope为singleton的)
  private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

  /**
   * 1. 先从单例的map集合中获取 是否有指定beanName的对象
   * 有直接返回
   * 没有下一步
   * 2. 从注册集合中获取bean的定义对象
   * 有下一步
   * 没有抛异常NoSuchBeanDefinition
   * 3. 判断bean的scope作用域
   * singleton单例
   * createBean对象
   * 存入单例集合
   * 返回对象
   * prototype多例
   * createBean对象
   * 返回对象
   * 4. createBean方法
   * 获取BeanDefinition中的className
   * 通过反射API得到Class对象
   * 通过反射API得到bean实例
   * 获取BeanDefinition中依赖的属性列表
   * 实现属性的依赖注入
   */

  @Override
  public Object getBean(String beanName) {
    // 1. 先从单例的map集合中获取 是否有指定beanName的对象
    Object singletonObj = singletonObjects.get(beanName);
    // 有直接返回
    if (singletonObj != null) {
      return singletonObj;
    }
    // 没有下一步
    // 2. 从注册集合中获取bean的定义对象
    BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
    // 有下一步
    // 没有抛异常NoSuchBeanDefinition
    if (beanDefinition == null) {
      throw new RuntimeException("NoSuchBeanDefinition : 你找的 " + beanName + " 对象 不存在");
    }
    // 3. 判断bean的scope作用域
    String scope = beanDefinition.getScope();
    // singleton单例
    if ("singleton".equals(scope)) {
      // createBean对象
      Object obj = createBean(beanDefinition);
      //存入单例集合
      singletonObjects.put(beanName, obj);
      // 返回对象
      return obj;
    } else {
      // prototype多例
      // createBean对象
      return createBean(beanDefinition);
      // 返回对象
    }
  }

  /**
   * //4. createBean方法
   * //获取BeanDefinition中的className
   * //通过反射API得到Class对象
   * //通过反射API得到bean实例
   * //获取BeanDefinition中依赖的属性列表
   * //实现属性的依赖注入
   *
   * 创建对象
   */
  private Object createBean(BeanDefinition beanDefinition) {
    String className = beanDefinition.getClassName();
    Class<?> aClass;
    try {
      aClass = Class.forName(className);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      throw new RuntimeException("类未找到" + e.getMessage());
    }
    // 创建对象
    Object obj;
    try {
      obj = aClass.newInstance();
    } catch (InstantiationException e) {
      e.printStackTrace();
      throw new RuntimeException("创建对象失败" + e.getMessage());
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      throw new RuntimeException("非法访问" + e.getMessage());
    }
    // 依赖注入
    List<Property> propertyList = beanDefinition.getPropertyList();
    for (Property property : propertyList) {
      String name = property.getName();
      String value = property.getValue();
      String ref = property.getRef();
      // 属性名不为空 进行注入
      if (name != null && !"".equals(name)) {
        // 如果配置的是value值 直接注入
        if (value != null && !"".equals(value)) {
          Map<String, String> params = new HashMap<>();
          params.put(name, value);
          try {
            BeanUtils.populate(obj, params);
          } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("非法访问" + e.getMessage());
          } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("调用目标对象失败" + e.getMessage());
          }
        }
        // 如果配置的是ref需要获取其它对象注入
        if (ref != null && !"".equals(ref)) {
          try {
            BeanUtils.setProperty(obj, name, getBean(ref));
          } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("非法访问" + e.getMessage());
          } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("调用目标对象失败" + e.getMessage());
          }
        }
      }
    }
    return obj;
  }

  /**
   * 将bean注册到容器中
   */
  public void registerBeanDefinition(BeanDefinition beanDefinition) {
    beanDefinitionMap.put(beanDefinition.getBeanName(), beanDefinition);
  }

  public void preInstanceSingletons(){
    beanDefinitionMap.forEach((beanName,beanDefinition)->{
      String scope = beanDefinition.getScope();
      // 判断单例  非抽象   不懒加载
      if ("singleton".equals(scope)){
        this.getBean(beanName);
      }
    });
  }
}