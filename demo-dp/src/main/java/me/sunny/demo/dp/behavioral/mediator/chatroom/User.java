package me.sunny.demo.dp.behavioral.mediator.chatroom;

import lombok.Getter;
import lombok.Setter;
import me.sunny.demo.dp.behavioral.mediator.role.Colleague;

public class User implements Colleague {

    @Getter
    private int id;

    @Getter
    private String name;

    @Setter
    private ChatRoom chatRoom;

    public User(int id, String name) {
       this.id = id;
       this.name = name;
    }

    public void sendMessage(String msg) {
        System.out.println(this.name + " say: " + msg);
        this.chatRoom.notifyOthers(msg, this);
    }
}
