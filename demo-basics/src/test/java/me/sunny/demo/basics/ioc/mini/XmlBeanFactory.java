package me.sunny.demo.basics.ioc.mini;

/**
 * 继承核心实现类
 * 基于xml配置bean的实现类
 **/
public class XmlBeanFactory extends DefaultListableBeanFactory {
  /**
   * 将解析配置文件 注册bean的所有工作交给reader对象
   */
  private final XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(this);

  /**
   * 构造器需要传入xml配置文件
   */
  public XmlBeanFactory(String configPath) {
    // 使用reader对象 解析配置  注册Bean
    this.xmlBeanDefinitionReader.loadBeanDefinitions(configPath);
    // 初始化单例对象
    this.preInstanceSingletons();
  }
}