package me.sunny.demo.aop.spring.annotation.sample2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnnotationAspect {

    @Pointcut("@annotation(me.sunny.demo.aop.spring.annotation.sample2.Sample2AopAnnotation)")
    private void pointCutMethod() {
    }

    @Around(value = "pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("AOP execute success!");

        Field filed = pjp.getClass().getDeclaredField("methodInvocation");
        filed.setAccessible(true);
        Object methodInvocation = filed.get(pjp);

        Method method = methodInvocation.getClass().getMethod("getMethod");
        Method m = (Method) method.invoke(methodInvocation);

        Sample2AopAnnotation sample2AopAnnotation = m.getAnnotation(Sample2AopAnnotation.class);
        if (!Objects.isNull(sample2AopAnnotation)) {
            System.out.println("Annotation AOP execute success!");
        }
        return pjp.proceed();
    }
}
