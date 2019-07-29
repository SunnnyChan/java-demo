package me.sunny.demo.dp.oop.structural.adapter.obj;

import me.sunny.demo.dp.oop.structural.adapter.cls.Adaptee;
import me.sunny.demo.dp.oop.structural.adapter.cls.Target;

public class Adapter implements Target {
    // 适配者是对象适配器的一个属性
    private Adaptee adaptee = new Adaptee();

    @Override
    public void request() {
        //...
        adaptee.adapteeRequest();
        //...
    }
}
