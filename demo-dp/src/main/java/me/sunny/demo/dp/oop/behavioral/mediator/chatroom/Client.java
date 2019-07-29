package me.sunny.demo.dp.oop.behavioral.mediator.chatroom;

public class Client {

    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom();
        User user1 = new User(1, "Jack");
        // 进入聊天室
        chatRoom.userEnter(user1);
        User user2 = new User(2, "Mike");
        chatRoom.userEnter(user2);
        User user3 = new User(3, "Sunny");
        chatRoom.userEnter(user3);

        user1.sendMessage("I'm " + user1.getName());
        user2.sendMessage("I'm " + user2.getName());
        user3.sendMessage("I'm " + user3.getName());
    }
}
