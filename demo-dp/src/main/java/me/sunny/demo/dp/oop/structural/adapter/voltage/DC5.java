package me.sunny.demo.dp.oop.structural.adapter.voltage;

/**
 * 适配器接口
 */
public interface DC5 {
    // 用于检查输入的电压是否与适配器匹配
    boolean support(AC ac);
    // 用于将输入的电压变换为 5V 后输出
    int outputDC5V(AC ac);
}
