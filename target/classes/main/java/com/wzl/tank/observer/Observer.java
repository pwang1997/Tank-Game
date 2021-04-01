package com.wzl.tank.observer;

import java.io.Serializable;

public interface Observer extends Serializable {
    void actionOnFire(TankFireEvent event);
}
