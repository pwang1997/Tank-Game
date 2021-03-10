package com.wzl.tank;

import java.awt.*;

public class Explode extends GameObject {
    public static final int WIDTH = ResourceMgr.INSTANCE.getExplodes()[0].getWidth();

    public static final int HEIGHT = ResourceMgr.INSTANCE.getExplodes()[0].getHeight();

    private int step = 0;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        new Thread(() -> new Audio("audio/explode.wav").play()).start();
        GameModel.getInstance().add(this);

    }

    public void paint(Graphics g) {
        if(step < 16) g.drawImage(ResourceMgr.INSTANCE.getExplodes()[step++], x, y, null);
        else {
            GameModel.getInstance().remove(this);
        }
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
