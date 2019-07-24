package me.sunny.demo.fw.lombok;

import org.testng.annotations.Test;

public class UserTest {

    @Test
    public void testSetGet() {
        User user = new User();
        user.setID(10);
        System.out.println(user.getID());
    }
}
