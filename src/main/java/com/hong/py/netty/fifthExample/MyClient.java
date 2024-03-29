package com.hong.py.netty.fifthExample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;

public class MyClient {
    public static void main(String[] args) {
        EventLoopGroup eventGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventGroup).channel(NioSocketChannel.class).
                    handler(new MyClientInitalizer());
            //监听8899端口
            ChannelFuture channelFuture = bootstrap.connect("localhost",8899).sync();

            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventGroup.shutdownGracefully();
        }
    }
}
