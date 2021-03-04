package com.wzl.abstractfactory;

import com.wzl.tank.Audio;
import com.wzl.tank.GameModel;
import com.wzl.tank.ResourceMgr;
import com.wzl.tank.TankFrame;

import java.awt.*;

public class RectExplode extends BaseExplode{
    static final int WIDTH = ResourceMgr.INSTANCE.getExplodes()[0].getWidth();

    static final int HEIGHT = ResourceMgr.INSTANCE.getExplodes()[0].getHeight();

    private int x, y;
    private int step = 0;
    private boolean alive = true;
    GameModel gm = null;

    public RectExplode(int x, int y, GameModel gm) {
        this.x = x;
        this.y = y;
        this.gm = gm;

        Thread t = new Thread(() -> new Audio("audio/explode.wav").play());
        t.start();
    }
    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x, y, 10*step, 10*step );
        step++;
        if(step >= 5) {
            gm.explodes.remove(this);
        }
        g.setColor(c);
    }
}
