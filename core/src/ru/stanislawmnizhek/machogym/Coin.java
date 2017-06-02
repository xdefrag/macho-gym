package ru.stanislawmnizhek.machogym;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Coin extends Macho {
    private Animation<TextureRegion> coin;
    private Assets assets;

    public Coin() {
        assets = new Assets();
        initAssets();
    }

    @Override
    protected void initRect() {
        Random random = new Random();
        this.machoRect = new Rectangle();
        this.machoRect.x = random.nextInt(this.config.width);
        this.machoRect.y = random.nextInt(this.config.height);
        this.machoRect.width = this.config.spriteRes / 2;
        this.machoRect.height = this.config.spriteRes / 2;
    }

    private void initAssets() {
        coin = new Animation<>(0.35f, assets.getTexture("protein"));
    }

    public Animation<TextureRegion> getAnimation() {
        return coin;
    }
}
