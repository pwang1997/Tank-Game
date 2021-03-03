package com.wzl.abstractfactory;

import com.wzl.tank.*;

import java.awt.*;

public class RectBullet extends BaseBullet{
    private static final int SPEED = Integer.parseInt((String) PropertyMgr.get("bulletSpeed"));
    public static final int HEIGHT = ResourceMgr.INSTANCE.getBulletD().getHeight();
    public static final int WIDTH = ResourceMgr.INSTANCE.getBulletD().getWidth();

    private int x, y;
    private Dir dir;
    private TankFrame tf = null;
    boolean alive = true;
    Group group;
    Rectangle rect;

    public RectBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        this.rect = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
    }

    public void paint(Graphics g) {
        if(!alive) {
            tf.bulletList.remove(this);
            return;
        }
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 20, 20);
        g.setColor(c);
        move();
        rect.x = this.x;
        rect.y = this.y;
    }

    public void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }

        if(x < 0 || y < 0 || x > tf.GAME_WIDTH || y > tf.GAME_HEIGHT) {
            alive = false;
        }
    }

    public void collideWith(BaseTank tank) {
        if(this.group == tank.getGroup()) return;

        if(this.rect.intersects(tank.getRect())) {
            tank.die();
            this.die();
            int eX = tank.getX() + tank.WIDTH / 2 - Explode.WIDTH / 2;
            int eY = tank.getY() + tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            tf.explodes.add(tf.rf.createExplode(eX,eY,tf));
        }
    }

    public void die() {
        this.alive = false;
    }
}
