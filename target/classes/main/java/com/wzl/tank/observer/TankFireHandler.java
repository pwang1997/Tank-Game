package com.wzl.tank.observer;

import com.wzl.tank.Tank;

public class TankFireHandler implements Observer{
    @Override
    public void actionOnFire(TankFireEvent event) {
        Tank t = event.getSource();
        t.fire();
    }
}
