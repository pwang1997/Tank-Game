package com.wzl.tank;

import com.wzl.abstractfactory.*;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class GameModel {


//    public List<Tank> enemy = new ArrayList<>();
//    public List<Bullet> bulletList = new ArrayList<>();
//    public List<Explode> explodes = new ArrayList<>();

    private List<GameObject> objects = new ArrayList<>();
//    public GameFactory gf = new RectFactory(); // change in bullet/collideWith

    Tank myTank = new Tank(TankFrame.GAME_WIDTH / 2, TankFrame.GAME_HEIGHT / 2, Dir.DOWN, Group.GOOD, this);

    public GameModel() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
        // initialize enemy tanks
        for(int i = 0; i < initTankCount; i++) {
            this.objects.add(new Tank(50 + i * 50, 200, Dir.DOWN, Group.BAD, this));
        }
    }

    public void add(GameObject go){
        this.objects.add(go);
    }

    public void remove(GameObject go) {
        this.objects.remove(go);
    }

    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.WHITE);
//        g.drawString("Bullet Count: " + bulletList.size() +
//                "\nEnemy Count: " + enemy.size(), 10, 60);
        g.setColor(c);

        myTank.paint(g);

        for(int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
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
