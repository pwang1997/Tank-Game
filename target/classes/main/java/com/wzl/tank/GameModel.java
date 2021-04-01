package com.wzl.tank;

import com.wzl.tank.cor.BulletTankCollider;
import com.wzl.tank.cor.Collider;
import com.wzl.tank.cor.ColliderChain;
import com.wzl.tank.cor.TankTankCollider;

import java.awt.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GameModel{

    private static final GameModel INSTANCE = new GameModel();

//    private List<GameObject> objects = new ArrayList<>();
    HashMap<UUID, Tank> map = new HashMap<>();

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
        myTank = new Tank((int)(TankFrame.GAME_WIDTH * Math.random()),
                (int)(TankFrame.GAME_HEIGHT * Math.random()),
                Dir.values()[(int)(Math.random() * 4)],
                Group.values()[(int)(Math.random()*2)],
                UUID.randomUUID());
        map.put(myTank.getId(), myTank);
//        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
        // initialize enemy tanks
//        for(int i = 0; i < initTankCount; i++) {
//            Tank t = new Tank(50 + i * 100, 200, Dir.DOWN, Group.BAD);
//            map.put(t.getId(), t);
//        }

//        add(new Wall(150, 150, 200, 50));
//        add(new Wall(550, 150, 200, 50));
//        add(new Wall(300, 300, 50, 200));
//        add(new Wall(550, 300, 50, 200));
    }

    public void add(GameObject go){
//        this.objects.add(go);
        if(go instanceof Tank) {
            map.put(((Tank) go).getId(), (Tank) go);
            System.out.println(((Tank) go).getId());
        }
    }

    public void remove(GameObject go) {
//        this.objects.remove(go);
        if(go instanceof Tank)
            map.remove((Tank) go);
    }

    public void paint(Graphics g){
        myTank.paint(g);

//        for(int i = 0; i < objects.size(); i++) {
//            objects.get(i).paint(g);
//        }

        map.values().stream().forEach((e) -> e.paint(g));

//        for(int i = 0; i < objects.size(); i++) {
//            for(int j = i+1; j < objects.size(); j++) {
//                GameObject o1 = objects.get(i);
//                GameObject o2 = objects.get(j);
//                chain.collide(o1, o2);
//            }
//        }

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
//            oos.writeObject(objects);
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
//            objects = (List) ois.readObject();
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

    public Tank findByUUID(UUID id) {
        return map.get(id);
    }
}
