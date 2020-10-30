package me.sunny.demo.basics.dp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

public class JDKProxyTest {
  /**
   * 测试JDK代理
   * 创建代理类并生成相应的代理对象
   */
  @Test
  public void test() {
    JDKProxyInterface target = new JDKProxyInterfaceImpl();
    // 根据目标对象创建代理对象
    JDKProxyInterface proxy = (JDKProxyInterface) Proxy.newProxyInstance(
        target.getClass().getClassLoader(), target.getClass().getInterfaces(),
        new JDKProxyInvocationHandler(target));
    // 调用代理对象方法
    proxy.testProxy();
  }
  /**
   * 被代理的接口
   */
  interface JDKProxyInterface {
    void testProxy();
  }
  /**
   * 被代理的类
   */
  static class JDKProxyInterfaceImpl implements JDKProxyInterface {
    @Override
    public void testProxy() {
      System.out.println("testProxy");
    }
  }
  /**
   * 实现InvocationHandler接口
   */
  static class JDKProxyInvocationHandler implements InvocationHandler {
    private Object target;

    public JDKProxyInvocationHandler(Object target) {
      this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      System.out.println("执行前");
      Object result = method.invoke(this.target, args);
      System.out.println("执行后");
      return result;
    }
  }
}
