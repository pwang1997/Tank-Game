package com.wzl.tank;

import com.wzl.tank.netty.Client;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        TankFrame tf = new TankFrame();

//        new Thread(() -> new Audio("audio/war1.wav").loop()).start();

        new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tf.repaint();
            }
        }).start();

        Client.INSTANCE.connect();

    }
}