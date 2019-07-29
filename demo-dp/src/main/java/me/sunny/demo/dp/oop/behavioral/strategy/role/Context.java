package me.sunny.demo.dp.oop.behavioral.strategy.role;

/**
 * 环境类
 */
public class Context {
    private Strategy strategy;
    public Strategy getStrategy()
    {
        return strategy;
    }
    public void setStrategy(Strategy strategy)
    {
        this.strategy=strategy;
    }
    public void strategyMethod()
    {
        strategy.strategyMethod();
    }
}
