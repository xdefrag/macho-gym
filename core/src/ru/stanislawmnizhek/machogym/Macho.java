package ru.stanislawmnizhek.machogym;

import com.badlogic.gdx.math.Rectangle;

public class Macho {
    protected Rectangle machoRect;
    protected Config config;

    public Macho() {
        config = new Config();
        initRect();
    }

    protected void initRect() {
        machoRect = new Rectangle();
        machoRect.x = config.width / 2;
        machoRect.y = config.height / 2;
        machoRect.width = config.spriteRes;
        machoRect.height = config.spriteRes;
    }

    public void checkXY() {
        if (machoRect.x < 0) machoRect.x = config.width - config.spriteRes;
        if (machoRect.x > config.width - config.spriteRes) machoRect.x = 0;
        if (machoRect.y < 0) machoRect.y = config.height - config.spriteRes;
        if (machoRect.y > config.height - config.spriteRes) machoRect.y = 0;
    }

    public float getX() {
        return machoRect.x;
    }

    public float getY() {
        return machoRect.y;
    }

    public void setX(float x) {
        machoRect.x = x;
    }

    public void setY(float y) {
        machoRect.y = y;
    }

    public void dispose() {
//        playerIdle.dispose();
    }
}
