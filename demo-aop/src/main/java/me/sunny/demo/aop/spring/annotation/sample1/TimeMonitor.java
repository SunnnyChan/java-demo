package me.sunny.demo.aop.spring.annotation.sample1;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class TimeMonitor {

    @Around("execution(* me.sunny.demo.aop.spring.annotation.sample1.Sample1ServiceImpl.sayHello(..))")
    public void monitorAround(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("method start time:" + System.currentTimeMillis());
        Object re = pjp.proceed();
        System.out.println("method end time:" + System.currentTimeMillis());

    }


}
