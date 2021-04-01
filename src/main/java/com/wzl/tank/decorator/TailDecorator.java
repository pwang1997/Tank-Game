package com.wzl.tank.decorator;

import com.wzl.tank.GameObject;

import java.awt.*;

public class TailDecorator extends GODecorator{
    @Override
    public int getWidth() {
        return super.go.getWidth();
    }

    @Override
    public int getHeight() {
        return super.go.getHeight();
    }

    public TailDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x = go.x;
        this.y = go.y;

        super.go.paint(g);
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawLine(super.go.x, super.go.y,
                super.go.x + getWidth() + 2,
                super.go.y + getHeight() + 2);
        g.setColor(c);
    }
}
