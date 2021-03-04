//package com.wzl.abstractfactory;
//
//import com.wzl.tank.*;
//
//import java.lang.reflect.InvocationTargetException;
//
//public class DefaultFactory extends GameFactory{
//    @Override
//    public BaseTank createTank(int x, int y, Dir dir, Group group, GameModel gm) {
//        try {
//            return new Tank(x, y, dir, group, gm);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public BaseBullet createBullet(int x, int y, Dir dir, Group group, GameModel gm) {
//        return new Bullet(x, y, dir, group, gm);
//    }
//
//    @Override
//    public BaseExplode createExplode(int x, int y, GameModel gm) {
//        return new Explode(x, y, gm);
//    }
//}
