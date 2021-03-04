package com.wzl.abstractfactory;

import com.wzl.strategy.FireStrategy;
import com.wzl.tank.*;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public abstract class BaseTank {
    public static int HEIGHT;
    public static int WIDTH;
    public TankFrame tf;
    public Group group;
    public int x, y;
    public Dir dir;
    public boolean alive = true;
    public boolean moving = true;
    public Rectangle rect;
    public Random random = new Random();
    public FireStrategy fs;
    public GameModel gm;

    public BaseTank(int x, int y, Dir dir, Group group, GameModel gm) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gm = gm;
        this.group = group;
        this.rect = new Rectangle(this.x, this.y, WIDTH, HEIGHT);

        if(this.group == Group.GOOD) {
            String goodFSName = (String) PropertyMgr.get("goodFS");
            Constructor<FireStrategy> constructor = (Constructor<FireStrategy>) Class.forName(goodFSName).getDeclaredConstructor();
            constructor.setAccessible(true);
            fs = constructor.newInstance();
        } else {
            String goodFSName = (String) PropertyMgr.get("badFS");
            Constructor<FireStrategy> constructor = (Constructor<FireStrategy>) Class.forName(goodFSName).getDeclaredConstructor();
            constructor.setAccessible(true);
            fs = constructor.newInstance();
        }
    }{
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        this.rect = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        if(this.group == Group.GOOD) {
            String goodFSName = (String) PropertyMgr.get("goodFS");
            Constructor<FireStrategy> constructor = (Constructor<FireStrategy>) Class.forName(goodFSName).getDeclaredConstructor();
            constructor.setAccessible(true);
            this.fs = constructor.newInstance();
        } else {
            String goodFSName = (String) PropertyMgr.get("badFS");
            Constructor<FireStrategy> constructor = (Constructor<FireStrategy>) Class.forName(goodFSName).getDeclaredConstructor();
            constructor.setAccessible(true);
            this.fs = constructor.newInstance();
        }
    }


    public abstract void paint(Graphics g);

    public abstract void fire();

    public abstract void die();

    public Rectangle getRect() {
        return this.rect;
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
}
