package com.wzl.tank.observer;

import com.wzl.tank.Tank;

public class TankFireEvent {

    Tank source;

    public TankFireEvent(Tank source) {
        this.source = source;
    }

    public Tank getSource() {
        return this.source;
    }

}
