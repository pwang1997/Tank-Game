package com.wzl;

import java.awt.*;

public class Explode {
        static final int WIDTH = ResourceMgr.explodes[0].getWidth();

        static final int HEIGHT = ResourceMgr.explodes[0].getHeight();

        private int x, y;
        private int step = 0;
        private boolean alive = true;
        TankFrame tf = null;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

        Thread t = new Thread(() -> new Audio("audio/explode.wav").play());
        t.start();


    }

    public void paint(Graphics g) {
        if(step < 16) g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        else {
            tf.explodes.remove(this);
            return;
        }
    }
}
