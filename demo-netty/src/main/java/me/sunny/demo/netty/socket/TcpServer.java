package me.sunny.demo.netty.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TcpServer {
    private final Integer port;

    public TcpServer(Integer port) {
        this.port = port;
    }

    public void start() {
        //EventLoopGroup是用来处理IO操作的多线程事件循环器
        //负责接收客户端连接线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //负责处理客户端i/o事件、task任务、监听任务组
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        //启动 NIO 服务的辅助启动类
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup);
        //配置 Channel
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new TcpServerIniterHandler());

        //BACKLOG用于构造服务端套接字ServerSocket对象，
        // 标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度
        bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        //是否启用心跳保活机制
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);

        try {
            //绑定服务端口监听
            Channel channel = bootstrap.bind(this.port).sync().channel();
            System.out.println("server run in port : " + this.port);
            //服务器关闭监听
            /*
              channel.closeFuture() 只是简单的返回channel对象中的closeFuture对象，
              对于每个Channel对象，都会有唯一的一个CloseFuture，用来表示关闭的Future，
              sync() 会将当前线程阻塞在CloseFuture上。
            */
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //关闭事件流组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new TcpServer(8899).start();
    }
}
