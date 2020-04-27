package me.sunny.demo.aop.spring.annotation.sample1;

import org.springframework.stereotype.Service;

@Service
public class Sample1ServiceImpl implements HelloInterface {

    @Override
    public void sayHello() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Sample1 say : Hello!");
    }

}
