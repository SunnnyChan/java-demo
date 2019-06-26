package me.sunny.demo.dp.behavioral.observer;

public interface Observer {
    //当主题状态改变时,更新通知
    public void update(int version);
}
