package com.wzl.tank;

import com.wzl.tank.cor.BulletTankCollider;
import com.wzl.tank.cor.Collider;
import com.wzl.tank.cor.ColliderChain;
import com.wzl.tank.cor.TankTankCollider;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    private List<GameObject> objects = new ArrayList<>();

    ColliderChain chain = new ColliderChain();

    Tank myTank = new Tank(TankFrame.GAME_WIDTH / 2, TankFrame.GAME_HEIGHT / 2, Dir.DOWN, Group.GOOD, this);

    public GameModel() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
        // initialize enemy tanks
        for(int i = 0; i < initTankCount; i++) {
            this.objects.add(new Tank(50 + i * 100, 200, Dir.DOWN, Group.BAD, this));
        }
    }

    public void add(GameObject go){
        this.objects.add(go);
    }

    public void remove(GameObject go) {
        this.objects.remove(go);
    }

    public void paint(Graphics g){
        myTank.paint(g);

        for(int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        for(int i = 0; i < objects.size(); i++) {
            for(int j = i+1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                chain.collide(o1, o2);
            }
        }

//        for(int i = 0; i < bulletList.size(); i++) {
//            for(int j = 0; j < enemy.size(); j++) {
//                bulletList.get(i).collideWith(enemy.get(j));
//            }
//        }
    }

    public Tank getMainTank() {
        return this.myTank;
    }
}
