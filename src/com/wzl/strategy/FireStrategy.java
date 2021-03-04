package com.wzl.strategy;

import com.wzl.abstractfactory.BaseTank;
import com.wzl.abstractfactory.RectTank;
import com.wzl.tank.Tank;

public interface FireStrategy {
    void fire(Tank t);

}
