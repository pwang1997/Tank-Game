package com.wzl.tank.cor;

import com.wzl.tank.Bullet;
import com.wzl.tank.GameObject;
import com.wzl.tank.Wall;

public class BulletWallCollider implements Collider{

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Wall) {
            Bullet b = (Bullet) o1;
            Wall w = (Wall) o2;
            if(b.getRect().intersects(w.getRect())) {
                b.die();
            }
        } else if(o1 instanceof Wall && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
        return true;
    }
}
