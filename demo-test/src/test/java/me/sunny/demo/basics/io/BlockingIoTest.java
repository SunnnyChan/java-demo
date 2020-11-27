package me.sunny.demo.basics.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class BlockingIoTest {

  public static void main(String[] args) throws IOException {
    ServerSocket ss = new ServerSocket();
    ss.bind(new InetSocketAddress("112.126.103.179", 8091));
    int idx = 0;
    while (true) {
      final Socket socket = ss.accept();//阻塞方法
      new Thread(() -> { handle(socket); }, "线程[" + idx + "]").start();
    }
  }

  static void handle(Socket socket) {
    byte[] bytes = new byte[1024];
    try {
      String serverMsg = "  server sss[ 线程：" + Thread.currentThread().getName() + "]";
      socket.getOutputStream().write(serverMsg.getBytes());
      socket.getOutputStream().flush();
    } catch (Exception e) { e.printStackTrace(); }
  }
}
