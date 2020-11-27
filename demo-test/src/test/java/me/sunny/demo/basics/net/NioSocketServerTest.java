package me.sunny.demo.basics.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioSocketServerTest {
  public void serve(int port) throws IOException {
    ServerSocketChannel serverChannel = ServerSocketChannel.open();
    ServerSocket ss = serverChannel.socket();
    InetSocketAddress address = new InetSocketAddress(port);
    ss.bind(address); // #1
    serverChannel.configureBlocking(false);
    Selector selector = Selector.open();
    serverChannel.register(selector, SelectionKey.OP_ACCEPT); // #2
    while (true) {
      try {
        selector.select(); // #3
      } catch (IOException ex) {
        ex.printStackTrace();
        // handle in a proper way
        break;
      }
      Set readyKeys = selector.selectedKeys(); // #4
      Iterator iterator = readyKeys.iterator();
      while (iterator.hasNext()) {
        SelectionKey key = (SelectionKey)iterator.next();
        try {
          if (key.isAcceptable()) {
            ServerSocketChannel server = (ServerSocketChannel)key
                .channel();
            long beforeTime = System.nanoTime();
            SocketChannel client = server.accept(); // #6
            System.out.println("Accept connection time: " + (System.nanoTime() - beforeTime) + " ns");
            if (client == null) {//Check if socketChannel has been created, it could be null, because it's non-blocking
              continue;
            }
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_WRITE
                    | SelectionKey.OP_READ,
                ByteBuffer.allocate(100));
          }
          if (key.isReadable()) {
            SocketChannel client = (SocketChannel)key.channel();
            ByteBuffer output = (ByteBuffer)key.attachment();
            client.read(output);
          }
          if (key.isWritable()) {
            SocketChannel client = (SocketChannel)key.channel();
            ByteBuffer output = (ByteBuffer)key.attachment();
            output.flip();
            client.write(output);
            output.compact();
          }
        } catch (IOException ex) {
          key.cancel();
          try {
            key.channel().close();
          } catch (IOException cex) {
          }
        }
        iterator.remove(); // #5
      }
    }
  }

  public static void main(String[] args) throws IOException {
    NioSocketServerTest server = new NioSocketServerTest();
    server.serve(8086);
  }
}
