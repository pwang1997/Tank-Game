package com.wzl.tank.netty;

import com.wzl.tank.Dir;
import com.wzl.tank.GameModel;
import com.wzl.tank.Group;
import com.wzl.tank.Tank;

import java.io.*;
import java.util.UUID;

public class TankStartMovingMsg extends Msg{
    public TankStartMovingMsg() {}

    public TankStartMovingMsg(Tank t) {
        this.id = t.id;
        this.x = t.x;
        this.y = t.y;
        this.dir = t.dir;
        this.moving = true;
        this.group = t.group;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    @Override
    public void handle() {
        if(this.id.equals(GameModel.getInstance().getMainTank().getId())) return;

        System.out.println(this.toString());
        Tank t = GameModel.getInstance().findByUUID(this.id);

        if(t != null) {
            t.setMoving(true);
            t.setX(this.x);
            t.setY(this.y);
            t.setDir(this.dir);
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
            dos.writeInt(dir.ordinal());
            dos.writeBoolean(moving); //2 bytes
            dos.writeInt(group.ordinal());
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
            this.dir = Dir.values()[dis.readInt()];
            this.moving = dis.readBoolean();
            this.group = Group.values()[dis.readInt()];
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
        return MsgType.TankStartMoving;
    }
}
