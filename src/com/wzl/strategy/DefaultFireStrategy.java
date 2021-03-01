package com.wzl.strategy;

import com.wzl.*;

public class DefaultFireStrategy implements FireStrategy {

    private static final DefaultFireStrategy INSTANCE = new DefaultFireStrategy();

    private DefaultFireStrategy(){};

    public static DefaultFireStrategy getInstance(Tank t) {
        return INSTANCE;
    }

    @Override
    public void fire(Tank t) {
        int bx = t.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = t.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        t.tf.bulletList.add(new Bullet(bx, by, t.getDir(), t.group, t.tf));
        if(t.group == Group.GOOD)
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }
}
