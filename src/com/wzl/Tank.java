package com.wzl;

import java.awt.*;
import java.util.Random;

public class Tank {

    static final int SPEED = Integer.parseInt((String) PropertyMgr.get("tankSpeed"));
    static final int WIDTH = ResourceMgr.goodtankL.getWidth();
    static final int HEIGHT = ResourceMgr.goodtankL.getHeight();

    private int x, y;
    private Dir dir;
    boolean alive = true;
    boolean moving = true;
    Rectangle rect;
    Group group;
    Random random = new Random();
    private TankFrame tf;

    public Rectangle getRect() {
        return rect;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public Group getGroup() {
        return group;
    }



    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        this.rect = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
    }

    public void paint(Graphics g) {
        if(!alive) {
            tf.enemy.remove(this);
            return;
        }
        switch(dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodtankL :
                        ResourceMgr.badtankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodtankR :
                        ResourceMgr.badtankR, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodtankU :
                        ResourceMgr.badtankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodtankD :
                        ResourceMgr.badtankD, x, y, null);
                break;
        }
        if(random.nextInt(100) > 95 && this.group == Group.BAD) {
            setRandomDir();
        }

        if(random.nextInt(100) >= 95 && this.group == Group.BAD) {
            this.fire();
        }

        move();

        boundsCheck();

        rect.x = this.x;
        rect.y = this.y;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void fire() {
        int bx = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tf.bulletList.add(new Bullet(bx, by, this.dir, this.group, tf));
    }

    private void move() {
        if(!moving) {
            return;
        }
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
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void die() {
        this.alive = false;
    }

    private void setRandomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    private void boundsCheck() {
        if (x < WIDTH) x = WIDTH;
        if (y < HEIGHT) y = HEIGHT;
        if (TankFrame.GAME_WIDTH - x - Tank.WIDTH < 2) x = TankFrame.GAME_WIDTH - (Tank.WIDTH + 2);
        if (TankFrame.GAME_HEIGHT - Tank.HEIGHT - y < 2) y = TankFrame.GAME_HEIGHT - (Tank.HEIGHT + 2);
    }
}
