package me.sunny.demo.dp.behavioral.observer.magezine;

import java.util.ArrayList;
import java.util.List;

public class MagazineSubject implements Subject {
    //存放订阅者
    private List<Observer> observers = new ArrayList<Observer>();

    //期刊版本
    private int version;

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void deleteObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update(version);
        }
    }

    //该杂志发行了新版本
    public void publish() {
        //新版本
        this.version++;
        //信息更新完毕，通知所有观察者
        notifyObserver();
    }
}
