package me.sunny.demo.dp.oop.structural.adapter.voltage;

public class AC220 implements AC{

    @Override
    public int outputAC() {
        return 220;
    }
}
