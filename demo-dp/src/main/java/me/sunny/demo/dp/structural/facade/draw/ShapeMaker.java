package me.sunny.demo.dp.structural.facade.draw;

public class ShapeMaker {
    private Circle mCircle;
    private Rectangle mRectangle;
    public ShapeMaker(){
        mCircle = new Circle();
        mRectangle = new Rectangle();
    }

    public void drawCircle(){
        mCircle.draw();
    }
    public void drawRectangle(){
        mRectangle.draw();
    }
}
