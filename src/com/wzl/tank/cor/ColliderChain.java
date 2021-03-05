package com.wzl.tank.cor;

import com.wzl.tank.GameObject;
import com.wzl.tank.PropertyMgr;
import com.wzl.tank.Tank;
import com.wzl.tank.strategy.FireStrategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

public class ColliderChain implements Collider{

    private List<Collider> colliderList = new LinkedList<>();

    public ColliderChain() throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        String[] colliders = ((String) PropertyMgr.get("colliders")).split(",");
        for(String collider : colliders) {
            Constructor<Collider> constructor = (Constructor<Collider>) Class.forName(collider).getDeclaredConstructor();
            constructor.setAccessible(true);
            add(constructor.newInstance());
        }
    }

    public void add(Collider c) {
        colliderList.add(c);
    }

    public boolean collide(GameObject o1, GameObject o2) {
        for(int i =0; i < colliderList.size(); i++) {
            if(!colliderList.get(i).collide(o1, o2)) {
                return false;
            }
        }
        return true;
    }
}
