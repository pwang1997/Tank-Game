package com.wzl.tank;

import com.wzl.abstractfactory.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {


    public List<BaseTank> enemy = new ArrayList<>();
    public List<BaseBullet> bulletList = new ArrayList<>();
    public List<BaseExplode> explodes = new ArrayList<>();
    public GameFactory gf = new RectFactory(); // change in bullet/collideWith

    BaseTank myTank = gf.createTank(TankFrame.GAME_WIDTH / 2, TankFrame.GAME_HEIGHT / 2, Dir.DOWN, Group.GOOD, this);


    public GameModel() {

    }

    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("Bullet Count: " + bulletList.size() +
                "\nEnemy Count: " + enemy.size(), 10, 60);
        g.setColor(c);

        myTank.paint(g);

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

    public BaseTank getMainTank() {
        return this.myTank;
    }
}
