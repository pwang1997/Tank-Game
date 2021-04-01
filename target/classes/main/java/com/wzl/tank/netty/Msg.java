package com.wzl.tank.netty;

import com.wzl.tank.Dir;
import com.wzl.tank.Group;

import java.util.UUID;

public abstract class Msg {
    public int x, y;
    public Dir dir;
    public boolean moving;
    public Group group;
    public UUID id;

    public abstract void handle();
    public abstract byte[] toBytes();
    public abstract void parse(byte[] bytes);
    public abstract MsgType getMsgType();
}
