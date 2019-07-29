package me.sunny.demo.dp.oop.behavioral.observer.magezine;

public class CustomerObserver implements Observer {
    //订阅者名字
    private String name;

    public CustomerObserver(String name){
        this.name = name;
    }

    public void update(int version) {
        System.out.println("该杂志出新版本了");
        this.buy(version);
    }

    public void buy(int version){
        System.out.println(name + "购买了第" + version + "期的杂志!");
    }
}
