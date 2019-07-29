package me.sunny.demo.dp.oop.structural.adapter.cls;

public class Client {

    public static void main(String[] args) {
        Target adapterTarget = new Adapter();
        adapterTarget.request();
    }
}
