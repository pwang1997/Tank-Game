package com.wzl;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        TankFrame tf = new TankFrame();

        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
        // initialize enemy tanks
        for(int i = 0; i < initTankCount; i++) {
            tf.enemy.add(new Tank(50 + i * 50, 200, Dir.DOWN, Group.BAD, tf));
        }

        new Thread(() -> new Audio("audio/war1.wav").loop()).start();

        while(true) {
            Thread.sleep(50);
            tf.repaint();
        }

    }
}