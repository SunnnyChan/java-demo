package me.sunny.demo.dp.structural.proxy.statics;

public class PeopleTalk implements Talk {
    public String userName;
    public String age;

    public PeopleTalk(String userName, String age) {
        this.userName = userName;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void talk(String msg) {
        System.out.println(msg + " 姓名: " + this.userName + " 年龄: " + this.age);
    }
}
