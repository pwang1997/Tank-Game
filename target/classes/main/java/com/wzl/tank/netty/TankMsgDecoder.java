package com.wzl.tank.netty;

import com.wzl.tank.Dir;
import com.wzl.tank.Group;
import com.wzl.tank.Tank;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.UUID;

public class TankMsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        /**
         * x, y, dir, group: 4bytes
         * moving: 1 byte
         * id: 16 bytes
         * eg: netty.factorial https://netty.io/4.1/xref/io/netty/example/factorial/package-summary.html
         * out.writeByte((byte) 'F'); // magic number
         * out.writeInt(dataLength);  // data length
         * out.writeBytes(data);      // data
         */
        if(in.readableBytes() < 8) return; // TCP 拆包 粘包

        in.markReaderIndex();

        MsgType msgType = MsgType.values()[in.readInt()];
        int length = in.readInt();

        if(in.readableBytes() < length) {
            in.resetReaderIndex();
            return;
        }

        byte[] bytes = new byte[length];
        in.readBytes(bytes);
        Msg msg = null;
        switch(msgType) {
            case TankJoin:
                msg = new TankJoinMsg();
                msg.parse(bytes);
                out.add(msg);
                break;
//            case TankStartMoving:
//                msg = new TankStartMovingMsg();
//                msg.parse(bytes);
//                out.add(msg);
//                break;
            default:
                break;
        }

    }
}