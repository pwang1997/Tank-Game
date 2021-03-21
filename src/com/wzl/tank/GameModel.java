package com.wzl.tank;

import com.wzl.tank.cor.BulletTankCollider;
import com.wzl.tank.cor.Collider;
import com.wzl.tank.cor.ColliderChain;
import com.wzl.tank.cor.TankTankCollider;

import java.awt.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class GameModel{

    private static final GameModel INSTANCE = new GameModel();

    private List<GameObject> objects = new ArrayList<>();

    ColliderChain chain = new ColliderChain();

    static {
        INSTANCE.init();
    }

    Tank myTank;

    public static GameModel getInstance() {
        return INSTANCE;
    }

    public GameModel() { }

    private void init() {
        myTank = new Tank(TankFrame.GAME_WIDTH / 2, TankFrame.GAME_HEIGHT / 2, Dir.DOWN, Group.GOOD);

        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
        // initialize enemy tanks
        for(int i = 0; i < initTankCount; i++) {
            new Tank(50 + i * 100, 200, Dir.DOWN, Group.BAD);
        }

        add(new Wall(150, 150, 200, 50));
        add(new Wall(550, 150, 200, 50));
        add(new Wall(300, 300, 50, 200));
        add(new Wall(550, 300, 50, 200));
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

    public void save() {
        File f = new File("/Users/puckwang/IdeaProjects/" +
                "TankGame/src/com/wzl/tank/tank.data");
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(myTank);
            oos.writeObject(objects);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void load() {
        File f = new File("/Users/puckwang/IdeaProjects/" +
                "TankGame/src/com/wzl/tank/tank.data");
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(f));
            myTank = (Tank) ois.readObject();
            objects = (List) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
