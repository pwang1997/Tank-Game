package com.wzl.abstractfactory;

import com.wzl.tank.Dir;
import com.wzl.tank.GameModel;
import com.wzl.tank.Group;
import com.wzl.tank.TankFrame;

public abstract class GameFactory {
    public abstract BaseTank createTank(int x, int y, Dir dir, Group group, GameModel gm);
    public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, GameModel gm);
    public abstract BaseExplode createExplode(int x, int y, GameModel gm);
}
