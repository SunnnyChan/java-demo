package me.sunny.demo.spring.event.first;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 通过Spring上下文对象的publishEvent方法来触发StudentAddEvent事件
 */
@Component
public class StudentAddBean {
    /**
     * 定义Spring上下文对象
     */
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 增加一个学生
     * @param studentName
     */
    public void addStudent(String studentName) {
        // 1.构造一个增加学生的事件
        StudentAddEvent aStudentEvent = new StudentAddEvent(applicationContext, studentName);
        // 2.触发增加学生事件
        applicationContext.publishEvent(aStudentEvent);
    }
}
