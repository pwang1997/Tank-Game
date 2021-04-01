package com.wzl.tank.strategy;

import com.wzl.tank.Tank;

import java.io.Serializable;

public interface FireStrategy extends Serializable {
    void fire(Tank t);
}
