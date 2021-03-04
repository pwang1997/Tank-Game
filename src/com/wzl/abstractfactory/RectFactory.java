package com.wzl.abstractfactory;

import com.wzl.tank.Dir;
import com.wzl.tank.GameModel;
import com.wzl.tank.Group;
import com.wzl.tank.TankFrame;

import java.lang.reflect.InvocationTargetException;

public class RectFactory extends GameFactory{
    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, GameModel gm) {
        try {
            return new RectTank(x, y, dir, group, gm);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, GameModel gm) {
        return new RectBullet(x, y, dir, group, gm);
    }

    @Override
    public BaseExplode createExplode(int x, int y, GameModel gm) {
        return new RectExplode(x, y, gm);
    }
}
