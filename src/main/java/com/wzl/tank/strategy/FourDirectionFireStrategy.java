package com.wzl.tank.strategy;

import com.wzl.tank.*;

public class FourDirectionFireStrategy implements FireStrategy {

    private static final FourDirectionFireStrategy INSTANCE = new FourDirectionFireStrategy();

    private FourDirectionFireStrategy(){};

    public static FourDirectionFireStrategy getInstance(Tank t) {
        return INSTANCE;
    }

    @Override
    public void fire(Tank t) {
        int bx = t.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = t.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        Dir[] dirs = Dir.values();
        for(Dir dir : dirs) {
//            t.tf.bulletList.add(new Bullet(bx, by, dir, t.group, t.tf));
            GameModel.getInstance().add(new Bullet(bx, by, dir, t.group));
        }

        if(t.group == Group.GOOD)
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();


    }
}

