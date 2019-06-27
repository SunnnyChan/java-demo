package me.sunny.demo.spring.event.first;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 新建StudentAddListener类，实现接口org.springframework.context.ApplicationListener中的onApplicationEvent方法，
 * 在该方法中只处理StudentAddEvent类型的ApplicationEvent事件
 * 定义StudentAddListener监听器
 */
@Component
public class ListenerNum3 implements ApplicationListener {

    //使用注解@Async支持 这样不仅可以支持通过调用，也支持异步调用，非常的灵活，
    @Async
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        // 1.判断是否是增加学生对象的事件
        if (!(event instanceof StudentAddEvent)) {
            return;
        }
        // 2.是增加学生事件的对象，进行逻辑处理，比如记日志、积分等
        StudentAddEvent studentAddEvent = (StudentAddEvent) event;
        System.out.println("增加了学生:" + studentAddEvent.getName());
    }

}
