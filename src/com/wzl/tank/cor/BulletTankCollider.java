package com.wzl.tank.cor;

import com.wzl.tank.Bullet;
import com.wzl.tank.GameObject;
import com.wzl.tank.Tank;

public class BulletTankCollider implements Collider{

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet b = (Bullet) o1;
            Tank t = (Tank) o2;
            b.collideWith(t);
            return false;
        } else if(o1 instanceof Tank && o2 instanceof Bullet) {
            Bullet b = (Bullet) o2;
            Tank t = (Tank) o1;
            b.collideWith(t);
            return false;
        } else {
            return true;
        }
    }
}
