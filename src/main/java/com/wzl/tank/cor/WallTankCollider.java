package com.wzl.tank.cor;

import com.wzl.tank.GameObject;
import com.wzl.tank.Tank;
import com.wzl.tank.Wall;

public class WallTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Wall) {
            Tank t = (Tank) o1;
            Wall w = (Wall) o2;

            if(t.getRect().intersects((w.getRect()))) {
                t.back();
            }
        } else if(o1 instanceof Wall && o2 instanceof Tank) {
            return collide(o2, o1);
        }
        return true;
    }
}
