package com.fastmessage.client.launch;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * 客户端启动类
 */
public class LaunchBootStrap {


    public static void main(String[] args) throws Exception{
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        EventLoopGroup group = new NioEventLoopGroup();

        serverBootstrap.group(group)
                .channel(NioServerSocketChannel.class) //设置nio类型的channel
                .localAddress(new InetSocketAddress(6765)) //设置监听端口
                .childHandler(new ChannelInitializer<NioSocketChannel>() { //有连接到达时会创建一个channel

                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        System.out.println(nioSocketChannel.toString());
                    }

                });
        ChannelFuture future = serverBootstrap.bind().sync(); //配置完成，绑定server，并通过sync同步方法阻塞直到绑定成功
        future.channel().closeFuture().sync(); //应用程序会一直等待，直到channel关闭

    }

}
