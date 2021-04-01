package com.wzl.tank.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class TankJoinMsgEncoder extends MessageToByteEncoder<TankJoinMsg> {

    protected void encode(ChannelHandlerContext channelHandlerContext, TankJoinMsg tankMsg, ByteBuf byteBuf) throws Exception {
        byteBuf.writeBytes(tankMsg.toBytes());
    }
}
