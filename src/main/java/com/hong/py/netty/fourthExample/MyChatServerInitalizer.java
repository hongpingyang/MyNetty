package com.hong.py.netty.fourthExample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.concurrent.TimeUnit;


public class MyChatServerInitalizer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("idleStateHandler",new IdleStateHandler(5,7,10, TimeUnit.SECONDS));
        pipeline.addLast(new MyChatServerHandler());
    }
}
