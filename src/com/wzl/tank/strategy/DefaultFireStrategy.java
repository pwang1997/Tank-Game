package com.wzl.tank.strategy;

import com.wzl.tank.*;

public class DefaultFireStrategy implements FireStrategy {

    private static final DefaultFireStrategy INSTANCE = new DefaultFireStrategy();

    private DefaultFireStrategy(){};

    public static DefaultFireStrategy getInstance(Tank t) {
        return INSTANCE;
    }

    @Override
    public void fire(Tank t) {
        int bx = t.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = t.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        GameModel.getInstance().add(new Bullet(bx, by, t.dir, t.group));
        if(t.group == Group.GOOD)
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }
}
