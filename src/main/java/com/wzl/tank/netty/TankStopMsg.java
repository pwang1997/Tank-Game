package com.wzl.tank.netty;

import com.wzl.tank.Dir;
import com.wzl.tank.GameModel;
import com.wzl.tank.Tank;

import java.io.*;
import java.util.UUID;

public class TankStopMsg extends Msg{
    public TankStopMsg() {}

    public TankStopMsg(UUID id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public TankStopMsg(Tank t) {
        this(t.id, t.x, t.y);
    }
    @Override
    public void handle() {
        if(this.id.equals(GameModel.getInstance().getMainTank().getId()))
            return;

        Tank t = GameModel.getInstance().findByUUID(this.id);

        if(t != null) {
            t.setMoving(false);
            t.x = this.x;
            t.y = this.y;
        }
    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;

        try {
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);

            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeBoolean(moving); //2 bytes
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.flush();
            bytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(baos != null)
                    baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(dos != null)
                    dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    @Override
    public void parse(byte[] bytes) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(
                bytes
        ));

        try {
            this.x = dis.readInt();
            this.y = dis.readInt();
            this.moving = dis.readBoolean();
            this.id = new UUID(dis.readLong(), dis.readLong());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TankStop;
    }
}
