package com.wzl.tank.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class TankJoinMsgEncoder extends MessageToByteEncoder<Msg> {

    protected void encode(ChannelHandlerContext channelHandlerContext, Msg msg, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(msg.getMsgType().ordinal()); // message type
        byte[] bytes = msg.toBytes();
        byteBuf.writeInt(bytes.length); // content length
        byteBuf.writeBytes(bytes);
    }
}
