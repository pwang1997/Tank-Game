package com.wzl.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public enum ResourceMgr {
    INSTANCE;

    private BufferedImage goodtankL, goodtankU, goodtankR, goodtankD;
    private BufferedImage badtankL, badtankU, badtankR, badtankD;

    private BufferedImage bulletL, bulletU, bulletR, bulletD;
    private BufferedImage[] explodes = new BufferedImage[16];
    ResourceMgr() {
            try {
                goodtankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
                goodtankL = ImageUtil.rotateImage(goodtankU, -90);
                goodtankR = ImageUtil.rotateImage(goodtankU, 90);
                goodtankD = ImageUtil.rotateImage(goodtankU, 180);

                badtankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
                badtankL = ImageUtil.rotateImage(badtankU, -90);
                badtankR = ImageUtil.rotateImage(badtankU, 90);
                badtankD = ImageUtil.rotateImage(badtankU, 180);

                bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
                bulletL = ImageUtil.rotateImage(bulletU, -90);
                bulletR = ImageUtil.rotateImage(bulletU, 90);
                bulletD = ImageUtil.rotateImage(bulletU, 180);

                for (int i = 0; i < 16; i++) {
                    explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public BufferedImage getGoodtankL() {
        return goodtankL;
    }

    public BufferedImage getGoodtankU() {
        return goodtankU;
    }

    public BufferedImage getGoodtankR() {
        return goodtankR;
    }

    public BufferedImage getGoodtankD() {
        return goodtankD;
    }

    public BufferedImage getBadtankL() {
        return badtankL;
    }

    public BufferedImage getBadtankU() {
        return badtankU;
    }

    public BufferedImage getBadtankR() {
        return badtankR;
    }

    public BufferedImage getBadtankD() {
        return badtankD;
    }

    public BufferedImage getBulletL() {
        return bulletL;
    }

    public BufferedImage getBulletU() {
        return bulletU;
    }

    public BufferedImage getBulletR() {
        return bulletR;
    }

    public BufferedImage getBulletD() {
        return bulletD;
    }

    public BufferedImage[] getExplodes() {
        return explodes;
    }
}

