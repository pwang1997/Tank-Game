package com.wzl.tank.decorator;

import com.wzl.tank.GameObject;

import java.awt.*;

public class RectDecorator extends GODecorator{
    public RectDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x = go.x;
        this.y = go.y;

        super.go.paint(g);
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawRect(go.x, go.y, go.getWidth()+2, go.getHeight()+2);
        g.setColor(c);
    }

    @Override
    public int getWidth() {
        return go.getWidth();
    }

    @Override
    public int getHeight() {
        return super.go.getHeight();
    }
}
