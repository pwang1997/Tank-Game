package com.wzl.strategy;

import com.wzl.*;

public class FourDirectionFireStrategy implements FireStrategy {

    private static final FourDirectionFireStrategy INSTANCE = new FourDirectionFireStrategy();

    private FourDirectionFireStrategy(){};

    public static FourDirectionFireStrategy getInstance(Tank t) {
        return INSTANCE;
    }

    @Override
    public void fire(Tank t) {
        int bx = t.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = t.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        Dir[] dirs = Dir.values();
        for(Dir dir : dirs) {
//            new Bullet(bx, by, t.getDir(), t.group, t.tf);
            t.tf.bulletList.add(new Bullet(bx, by, dir, t.group, t.tf));
        }

        if(t.group == Group.GOOD)
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();


    }
}

