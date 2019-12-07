package com.hong.py.netty.thirdExample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyChatClient {

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup eventGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventGroup).channel(NioSocketChannel.class).
                    handler(new MyChatClientInitalizer());
            //监听8899端口
            ChannelFuture channelFuture = bootstrap.connect("localhost",8899).sync();
            Channel channel = channelFuture.channel();

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            for (; ; ) {
                channel.writeAndFlush(br.readLine() + "\n");
            }

            //channelFuture.channel().close().sync();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            eventGroup.shutdownGracefully();
        }

    }
}
