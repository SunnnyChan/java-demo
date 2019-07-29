package me.sunny.demo.dp.oop.structural.adapter.voltage;

public class JapanAdapter implements DC5 {
    public static final int voltage = 110;

    @Override
    public boolean support(AC ac) {
        return (voltage == ac.outputAC());
    }

    @Override
    public int outputDC5V(AC ac) {
        int adapterInput = ac.outputAC();
        //变压器...
        int adapterOutput = adapterInput / 22;
        System.out.println("使用JapanPowerAdapter变压适配器，输入AC:"
            + adapterInput + "V" + "，输出DC:" + adapterOutput + "V");
        return adapterOutput;
    }
}
