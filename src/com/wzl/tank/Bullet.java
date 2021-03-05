package com.wzl.tank;

import java.awt.*;

public class Bullet extends GameObject {
    private static final int SPEED = Integer.parseInt((String) PropertyMgr.get("bulletSpeed"));
    public static final int HEIGHT = ResourceMgr.INSTANCE.getBulletD().getHeight();
    public static final int WIDTH = ResourceMgr.INSTANCE.getBulletD().getWidth();

    private int x, y;
    private Dir dir;
    private GameModel gm = null;
    boolean alive = true;
    Group group;
    Rectangle rect;

    public Bullet(int x, int y, Dir dir, Group group, GameModel gm) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gm = gm;
        this.group = group;
        this.rect = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
    }

    public void paint(Graphics g) {
        if(!alive) {
            gm.remove(this);
            return;
        }

        switch(dir) {
            case LEFT:
                g.drawImage(ResourceMgr.INSTANCE.getBulletL(), x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.INSTANCE.getBulletR(), x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.INSTANCE.getBulletU(), x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.INSTANCE.getBulletD(), x, y, null);
                break;
        }
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

        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            alive = false;
        }
    }

    public boolean collideWith(Tank tank) {
        if(this.group == tank.group) return false;

        if(this.rect.intersects(tank.getRect())) {
            tank.die();
            this.die();
            int eX = tank.x + tank.WIDTH / 2 - Explode.WIDTH / 2;
            int eY = tank.y + tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            gm.add(new Explode(eX,eY,gm));
            return true;
        }

        return false;
    }

    public void die() {
        this.alive = false;
    }
}
