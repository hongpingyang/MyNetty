package com.hong.py.netty.fifthExample;

import com.hong.py.proto.MyDataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class MyClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int randomint = new Random().nextInt(3);
        MyDataInfo.MyMessage message=null;

        if (randomint == 0) {

            message=MyDataInfo.MyMessage.newBuilder().
                    setDataType(MyDataInfo.MyMessage.dataType.PersonType).
                    setPerson(MyDataInfo.Person.newBuilder().
                            setName("张三").
                            setAge(20).
                            setAddress("北京").
                            build()).
                    build();
        } else if (randomint == 1) {
            message=MyDataInfo.MyMessage.newBuilder().
                    setDataType(MyDataInfo.MyMessage.dataType.DogType).
                    setDog(MyDataInfo.Dog.newBuilder().
                            setName("一只狗").
                            setAge(20).
                            build()).
                    build();
        } else {
            message=MyDataInfo.MyMessage.newBuilder().
                    setDataType(MyDataInfo.MyMessage.dataType.CatType).
                    setCat(MyDataInfo.Cat.newBuilder().
                            setName("一只猫").
                            setCity("上海").
                            build()).
                    build();
        }

        ctx.channel().writeAndFlush(message);
    }
}
