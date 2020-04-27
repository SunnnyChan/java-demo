package me.sunny.demo.aop;

import me.sunny.demo.aop.spring.annotation.sample2.Sample2HelloInterface;
import me.sunny.demo.aop.spring.annotation.sample2.Sample2ServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSample2 {

    private ClassPathXmlApplicationContext context;
    private Sample2ServiceImpl sample2Service;
    private Sample2HelloInterface sample2HelloInterface;

    @Before
    public void preDo() {
        context  = new ClassPathXmlApplicationContext("test-context.xml");
        sample2Service = context.getBean(Sample2ServiceImpl.class);
        sample2HelloInterface = context.getBean(Sample2HelloInterface.class);
    }

    @After
    public void finalDo() {
        context.close();
    }

    /**
     * 通过实现类标记的变量直接调用标注了注解的方法
     * 实现类通过Spring管理
     */
    @Test
    public void testAopSample2ImplClassWithAnnotation() throws Exception {
        sample2Service.sayHello();
    }

    /**
     * 通过接口标记的变量直接调用标注了注解的方法，但接口中的方法未标记
     * 实现类通过Spring管理
     */
    @Test
    public void testAopSample2InterfaceWithNoAnnotation() throws Exception {
        sample2HelloInterface.sayHello();
    }

    /**
     * 通过实现类标记的变量直接调用标注了注解的方法，但接口中的方法未标记
     * 实现类new创建
     */
    @Test
    public void testAopSample2ImplClassWithNoAnnotation2() throws Exception {
        Sample2ServiceImpl sample2HelloInterface = new Sample2ServiceImpl();
        sample2HelloInterface.sayHello();
    }

    /**
     * 通过接口标记的变量直接调用标注了注解的方法，但接口中的方法未标记
     * 实现类new创建
     */
    @Test
    public void testAopSample2InterfaceWithNoAnnotation2() throws Exception {
        Sample2HelloInterface sample2HelloInterface = new Sample2ServiceImpl();
        sample2HelloInterface.sayHello();
    }

}
