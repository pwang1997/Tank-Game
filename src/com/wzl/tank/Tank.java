package com.wzl.tank;

import com.wzl.abstractfactory.BaseTank;
import com.wzl.strategy.FireStrategy;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Tank extends BaseTank {

    public static final int SPEED = Integer.parseInt((String) PropertyMgr.get("tankSpeed"));
    public static final int WIDTH = ResourceMgr.INSTANCE.getBadtankL().getWidth();
    public static final int HEIGHT = ResourceMgr.INSTANCE.getBadtankL().getHeight();
    public Rectangle rect;
//    public TankFrame tf;
//    FireStrategy fs;


    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        super(x, y, dir, group, tf);
        this.rect = new Rectangle(x, y, WIDTH, HEIGHT);

    }

    public Rectangle getRect() {
        return rect;
    }

    public void paint(Graphics g) {
        if(!alive) {
            tf.enemy.remove(this);
            return;
        }
        switch(dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.INSTANCE.getGoodtankL() :
                        ResourceMgr.INSTANCE.getBadtankL(), x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.INSTANCE.getGoodtankR() :
                        ResourceMgr.INSTANCE.getBadtankR(), x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.INSTANCE.getGoodtankU() :
                        ResourceMgr.INSTANCE.getBadtankU(), x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.INSTANCE.getGoodtankD() :
                        ResourceMgr.INSTANCE.getBadtankD(), x, y, null);
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

    public void fire() {
        fs.fire(this);
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
