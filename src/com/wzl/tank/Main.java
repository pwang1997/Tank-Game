package com.wzl.tank;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        TankFrame tf = new TankFrame();

        new Thread(() -> new Audio("audio/war1.wav").loop()).start();

        while(true) {
            Thread.sleep(50);
            tf.repaint();
        }

    }
}