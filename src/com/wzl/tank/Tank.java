package com.wzl.tank;

import com.wzl.tank.observer.TankFireEvent;
import com.wzl.tank.observer.TankFireHandler;
import com.wzl.tank.observer.Observer;
import com.wzl.tank.strategy.DefaultFireStrategy;
import com.wzl.tank.strategy.FireStrategy;
import com.wzl.tank.strategy.FourDirectionFireStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.awt.*;

public class Tank extends GameObject {

    public static final int SPEED = Integer.parseInt((String) PropertyMgr.get("tankSpeed"));
    public static final int WIDTH = ResourceMgr.INSTANCE.getBadtankL().getWidth();
    public static final int HEIGHT = ResourceMgr.INSTANCE.getBadtankL().getHeight();
    public Rectangle rect;

    public Group group;
    int prevX, prevY;
    public Dir dir;
    public boolean alive = true;
    public boolean moving = true;
    public Random random = new Random();
    public FireStrategy fs;

    public Tank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.rect = new Rectangle(this.x, this.y, WIDTH, HEIGHT);

        if(this.group == Group.GOOD) {
            fs = FourDirectionFireStrategy.getInstance(this);
        } else {
            fs = DefaultFireStrategy.getInstance(this);
        }
        if (this.group == Group.BAD) {
            GameModel.getInstance().add(this);
        }
    }

    public Rectangle getRect() {
        return rect;
    }

    public void paint(Graphics g) {
        if(!alive) {
            GameModel.getInstance().remove(this);
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

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    public void fire() {
         fs.fire(this);
    }

    private void move() {
        prevX = x;
        prevY = y;

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

    public Group getGroup() {
        return this.group;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Dir getDir() {
        return this.dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void setMoving(boolean b) {
        this.moving = b;
    }

    public void stop() {
        this.moving = false;
    }

    public void back() {
        this.x = prevX;
        this.y = prevY;
    }

    private List<TankFireHandler> fireObservers = Arrays.asList(new TankFireHandler());
    public void handleFireKey() {
        TankFireEvent event = new TankFireEvent(this);
        for(TankFireHandler o: fireObservers) {
            o.actionOnFire(event);
        }
    }
}
