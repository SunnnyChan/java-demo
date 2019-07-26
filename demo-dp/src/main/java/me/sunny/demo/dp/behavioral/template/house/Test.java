package me.sunny.demo.dp.behavioral.template.house;

public class Test {
    @org.testng.annotations.Test
    public void test() {
        HouseTemplate houseType = new WoodenHouse();
        houseType.buildHouse();
        System.out.println("************");
        houseType = new GlassHouse();
        houseType.buildHouse();
    }
}
