package me.sunny.demo.netty;

import me.sunny.demo.netty.socket.TcpClient;

public class NettyTest {
    public static void main(String[] args) {
        try {
            new TcpClient("127.0.0.1", 8899).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
