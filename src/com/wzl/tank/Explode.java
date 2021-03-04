package com.wzl.tank;

import com.wzl.abstractfactory.BaseExplode;

import java.awt.*;

public class Explode extends GameObject {
    public static final int WIDTH = ResourceMgr.INSTANCE.getExplodes()[0].getWidth();

    public static final int HEIGHT = ResourceMgr.INSTANCE.getExplodes()[0].getHeight();

    private int x, y;
    private int step = 0;
    private boolean alive = true;
    GameModel gm = null;

    public Explode(int x, int y, GameModel gm) {
        this.x = x;
        this.y = y;
        this.gm = gm;

        Thread t = new Thread(() -> new Audio("audio/explode.wav").play());
        t.start();
    }

    public void paint(Graphics g) {
        if(step < 16) g.drawImage(ResourceMgr.INSTANCE.getExplodes()[step++], x, y, null);
        else {
            gm.remove(this);
            return;
        }
    }
}
