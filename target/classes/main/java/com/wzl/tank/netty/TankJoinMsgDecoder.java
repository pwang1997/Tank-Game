package com.wzl.tank.netty;

import com.wzl.tank.Dir;
import com.wzl.tank.Group;
import com.wzl.tank.Tank;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.UUID;

public class TankJoinMsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        /**
         * x, y, dir, group: 4bytes
         * moving: 1 byte
         * id: 16 bytes
         * eg: netty.factorial https://netty.io/4.1/xref/io/netty/example/factorial/package-summary.html
         * out.writeByte((byte) 'F'); // magic number
         * out.writeInt(dataLength);  // data length
         * out.writeBytes(data);      // data
         */
        if(byteBuf.readableBytes() < 33) return; // TCP 拆包 粘包

        TankJoinMsg msg = new TankJoinMsg();
        msg.x = byteBuf.readInt();
        msg.y = byteBuf.readInt();
        msg.dir = Dir.values()[byteBuf.readInt()];
        msg.moving = byteBuf.readBoolean();
        msg.group = Group.values()[byteBuf.readInt()];
        msg.id = new UUID(byteBuf.readLong(), byteBuf.readLong());

        list.add(msg);
    }
}