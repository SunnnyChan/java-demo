package me.sunny.demo.basics.dp;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

public class CglibProxyTest {
  @Test
  public void testCglibProxy() {
    // 通过CGLIB动态代理获取代理对象的过程
    Enhancer enhancer = new Enhancer();
    // 设置enhancer对象的父类
    enhancer.setSuperclass(CglibProxiedService.class);
    // 设置enhancer的回调对象
    enhancer.setCallback(new CglibProxyInterceptor());
    // 创建代理对象
    CglibProxiedService proxy = (CglibProxiedService)enhancer.create();

    System.out.println(CglibProxiedService.class);
    System.out.println(proxy.getClass());

    // 通过代理对象调用目标方法
    proxy.sayHello();
  }

  static class CglibProxiedService {

    public void sayHello() {
      System.out.println("hello !");
    }
  }

  static class CglibProxyInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object sub, Method method, Object[] objects, MethodProxy methodProxy)
        throws Throwable {
      System.out.println("before hello");
      Object object = methodProxy.invokeSuper(sub, objects);
      System.out.println("after hello");
      return object;
    }
  }
}
