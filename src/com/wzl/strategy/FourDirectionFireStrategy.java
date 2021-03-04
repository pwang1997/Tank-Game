package com.wzl.strategy;

import com.wzl.abstractfactory.BaseTank;
import com.wzl.tank.*;

public class FourDirectionFireStrategy implements FireStrategy {

    private static final FourDirectionFireStrategy INSTANCE = new FourDirectionFireStrategy();

    private FourDirectionFireStrategy(){};

    public static FourDirectionFireStrategy getInstance(BaseTank t) {
        return INSTANCE;
    }

    @Override
    public void fire(BaseTank t) {
        int bx = t.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = t.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        Dir[] dirs = Dir.values();
        for(Dir dir : dirs) {
//            t.tf.bulletList.add(new Bullet(bx, by, dir, t.group, t.tf));
            t.gm.bulletList.add(t.gm.gf.createBullet(bx, by, dir, t.group, t.gm));
        }

        if(t.group == Group.GOOD)
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();


    }
}

