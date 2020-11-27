package me.sunny.demo.basics.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioSocketServerTest {
  private static final ExecutorService executorPool = Executors.newFixedThreadPool(5);

  private static class Handler implements Runnable {
    private Socket clientSocket;

    public Handler(Socket clientSocket) {
      this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
      try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
        char chars[] = new char[64];
        int len = reader.read(chars);
        StringBuffer sb = new StringBuffer();
        sb.append(new String(chars, 0, len));
        System.out.println("From client: " + sb);
        writer.write(sb.toString());
        writer.flush();
      } catch (IOException e) {
        e.printStackTrace();
        try {
          clientSocket.close();
        } catch (IOException ex) {
          // ignore on close
        }
      }
    }
  }

  public void serve(int port) throws IOException {
    final ServerSocket socket = new ServerSocket(port);
    try {
      while (true) {
        long beforeTime = System.nanoTime();
        final Socket clientSocket = socket.accept();
        System.out.println("Establish connection time: " + (System.nanoTime() - beforeTime) + " ns");
        executorPool.execute(new Handler(clientSocket));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws IOException {
    BioSocketServerTest server = new BioSocketServerTest();
    server.serve(8086);
  }
}
