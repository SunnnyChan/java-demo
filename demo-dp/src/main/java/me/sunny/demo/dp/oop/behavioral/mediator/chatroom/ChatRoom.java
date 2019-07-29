package me.sunny.demo.dp.oop.behavioral.mediator.chatroom;

import java.util.HashSet;
import java.util.Set;

import me.sunny.demo.dp.oop.behavioral.mediator.role.Mediator;

public class ChatRoom implements Mediator {
    private Set<User> users = new HashSet<>();

    public void userEnter(User user) {
        this.users.add(user);
        user.setChatRoom(this);
        System.out.println("User : " + user.getName() + " enter room.");
    }

    public void notifyOthers(String msg, User user){
        for (User userI : users) {
            if (user.getId() != userI.getId()) {
                System.out.println(userI.getName() + " receive a message from " + user.getName() + " : " + msg);
            }
        }
    }
}
