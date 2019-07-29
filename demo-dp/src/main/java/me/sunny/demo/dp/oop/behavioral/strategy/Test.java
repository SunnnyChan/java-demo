package me.sunny.demo.dp.oop.behavioral.strategy;

import me.sunny.demo.dp.oop.behavioral.strategy.role.ConcreteStrategyA;
import me.sunny.demo.dp.oop.behavioral.strategy.role.ConcreteStrategyB;
import me.sunny.demo.dp.oop.behavioral.strategy.role.Context;
import me.sunny.demo.dp.oop.behavioral.strategy.role.Strategy;

public class Test {
    @org.testng.annotations.Test
    public static void test() {
        Context context = new Context();
        Strategy strategy = new ConcreteStrategyA();

        context.setStrategy(strategy);
        context.strategyMethod();

        System.out.println("-----------------");

        strategy = new ConcreteStrategyB();
        context.setStrategy(strategy);
        context.strategyMethod();
    }
}
