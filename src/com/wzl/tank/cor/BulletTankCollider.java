package com.wzl.tank.cor;

import com.wzl.tank.*;

public class BulletTankCollider implements Collider{

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet b = (Bullet) o1;
            Tank t = (Tank) o2;
            if(b.group == t.group) return true;

            if(b.getRect().intersects(t.getRect())) {
                t.die();
                b.die();
                int eX = t.x + t.WIDTH / 2 - Explode.WIDTH / 2;
                int eY = t.y + t.HEIGHT / 2 - Explode.HEIGHT / 2;
                GameModel.getInstance().add(new Explode(eX,eY));
                return false;
            }
        } else if(o1 instanceof Tank && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
            return true;
    }
}
