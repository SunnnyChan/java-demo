package me.sunny.demo.aop;

import me.sunny.demo.aop.spring.annotation.sample1.HelloInterface;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSample1 {

    private ClassPathXmlApplicationContext contex;
    private HelloInterface sample1Service;

    @Before
    public void preDo() {
        contex  = new ClassPathXmlApplicationContext("root-context.xml");
        sample1Service = contex.getBean(HelloInterface.class);
    }

    @After
    public void finalDo() {
        contex.close();
    }

    @Test
    public void testAop(){
        sample1Service.sayHello();
    }
}
