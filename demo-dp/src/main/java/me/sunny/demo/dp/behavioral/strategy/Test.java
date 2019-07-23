package me.sunny.demo.dp.behavioral.strategy;


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
