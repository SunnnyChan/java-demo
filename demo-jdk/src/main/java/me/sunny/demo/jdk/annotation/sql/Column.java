package me.sunny.demo.jdk.annotation.sql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解 ，用来映射字段名
 */
@Retention(RetentionPolicy.RUNTIME) //设置生命周期为运行时
@Target(ElementType.FIELD)  //设置作用域为类 接口
public @interface Column {
    String value();
}
