package me.sunny.demo.dp.structural.facade.draw;

public class Test {

    @org.testng.annotations.Test
    public void test() {
        ShapeMaker shapeMaker = new ShapeMaker();

        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
    }
}
