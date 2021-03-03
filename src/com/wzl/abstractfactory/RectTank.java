package com.wzl.abstractfactory;

import com.wzl.strategy.FireStrategy;
import com.wzl.tank.*;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class RectTank extends BaseTank{

    public static final int SPEED = Integer.parseInt((String) PropertyMgr.get("tankSpeed"));
    public static final int WIDTH = 40;
    public static final int HEIGHT = 40;
    public Rectangle rect;

//    public TankFrame tf;
//    FireStrategy fs;

    public RectTank(int x, int y, Dir dir, Group group, TankFrame tf) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        super(x, y, dir, group, tf);
        this.rect = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void paint(Graphics g) {
        if(!alive) {
            tf.enemy.remove(this);
            return;
        }

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.fillRect(x, y, WIDTH, HEIGHT );
        g.setColor(c);
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
