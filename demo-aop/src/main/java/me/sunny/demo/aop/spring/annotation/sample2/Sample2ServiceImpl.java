package me.sunny.demo.aop.spring.annotation.sample2;

import org.springframework.stereotype.Service;

@Service
public class Sample2ServiceImpl implements Sample2HelloInterface {

    @Sample2AopAnnotation
    @Override
    public void sayHello() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Sample2 say : Hello!");
    }

}
