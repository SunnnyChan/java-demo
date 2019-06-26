package me.sunny.demo.dp;

import me.sunny.demo.dp.behavioral.observer.CustomerObserver;
import me.sunny.demo.dp.behavioral.observer.MagazineSubject;
import org.testng.annotations.Test;

public class ObserverTest {

    @Test
    public void test() {
        //创建主题(被观察者)
        MagazineSubject magazine = new MagazineSubject();

        //创建三个不同的观察者
        CustomerObserver a = new CustomerObserver("Customer(Observer)-A");
        CustomerObserver b = new CustomerObserver("Customer(Observer)-B");
        CustomerObserver c = new CustomerObserver("Customer(Observer)-C");

        //将观察者注册到主题中
        magazine.addObserver(a);
        magazine.addObserver(b);
        magazine.addObserver(c);

        //更新主题的数据，当数据更新后，会自动通知所有已注册的观察者
        magazine.publish();
    }

}
