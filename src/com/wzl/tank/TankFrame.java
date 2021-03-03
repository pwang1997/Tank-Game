package com.wzl.tank;

import com.wzl.abstractfactory.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame{

    public static final int GAME_WIDTH = Integer.parseInt((String) PropertyMgr.get("gameWidth"));
    public static final int GAME_HEIGHT = Integer.parseInt((String) PropertyMgr.get("gameHeight"));

    public GameFactory df = new DefaultFactory();
    public GameFactory rf = new RectFactory(); // change in bullet/collideWith

//    Tank myTank = new Tank(GAME_WIDTH / 2, GAME_HEIGHT / 2, Dir.DOWN, Group.GOOD, this);
    BaseTank myTank = rf.createTank(GAME_WIDTH / 2, GAME_HEIGHT / 2, Dir.DOWN, Group.GOOD, this);

    public List<BaseTank> enemy = new ArrayList<>();
    public List<BaseBullet> bulletList = new ArrayList<>();
    public List<BaseExplode> explodes = new ArrayList<>();


    public TankFrame() throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("Tank war");
        setVisible(true);

        this.addKeyListener(new MyKeyListener());



        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * Solution for image flashing: Double Buffering.
     * Define a image in memory and draw the image in memory,
     * and then reflect the memory content to the screen.
     */
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("Bullet Count: " + bulletList.size() +
                "\nEnemy Count: " + enemy.size(), 10, 60);
        g.setColor(c);

        myTank.paint(g);

//        e.paint(g);

        for(int i = 0; i < enemy.size(); i++) {
            enemy.get(i).paint(g);
        }

        for(int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(g);
        }

        for(int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

        for(int i = 0; i < bulletList.size(); i++) {
            for(int j = 0; j < enemy.size(); j++) {
                bulletList.get(i).collideWith(enemy.get(j));
            }
        }

    }

    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
            repaint();
        }


        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if(!bL && !bR && !bD && !bU) {
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);

                if(bL) myTank.setDir(Dir.LEFT);
                if(bR) myTank.setDir(Dir.RIGHT);
                if(bU) myTank.setDir(Dir.UP);
                if(bD) myTank.setDir(Dir.DOWN);
            }
        }
    }
}
