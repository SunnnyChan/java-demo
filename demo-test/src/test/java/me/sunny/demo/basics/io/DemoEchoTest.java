package me.sunny.demo.basics.io;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DemoEchoTest {

  public static void main(String[] args) {
    // 事件处理器
    EchoServerHandler serverHandler = new EchoServerHandler();
    // boss线程组
    EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    // worker线程组
    EventLoopGroup workerGroup = new NioEventLoopGroup(1);
    try {
      ServerBootstrap bootstrap = new ServerBootstrap();
      bootstrap.group(bossGroup, workerGroup)
          .channel(NioServerSocketChannel.class)
          .childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
              socketChannel.pipeline().addLast(serverHandler);
            }
          });
      // 绑定端口号
      ChannelFuture future = bootstrap.bind(9090).sync();
      future.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      // 终止worker线程组
      workerGroup.shutdownGracefully();
      // 终止boss线程组
      bossGroup.shutdownGracefully();
    }
  }
}

// Socket 连接处理器
class EchoServerHandler extends ChannelInboundHandlerAdapter {
  // 处理读事件
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    ctx.write(msg);
  }

  // 处理读完成事件
  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    ctx.flush();
  }

  // 处理异常事件
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }
}
