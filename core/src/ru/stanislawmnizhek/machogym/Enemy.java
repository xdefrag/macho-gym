package ru.stanislawmnizhek.machogym;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Enemy extends Macho {
    private Random random;
    private float timer = 0f;
    private Assets assets;
    private Animation<TextureRegion> enemyIdleAnimation;
    private Animation<TextureRegion> enemyUpAnimation;
    private Animation<TextureRegion> enemyDownAnimation;
    private Animation<TextureRegion> enemyLeftAnimation;
    private Animation<TextureRegion> enemyRightAnimation;
    private Animation<TextureRegion> enemyHappyAnimation;
    private Animation<TextureRegion> currentAnimation;
    private int xRandom;
    private int yRandom;
    private boolean isHappy = false;

    public Enemy(Assets assets, Config config) {
        super(config);
        this.config = config;
        this.assets = assets;
        initAssets();
    }

    @Override
    protected void initRect() {
        random = new Random();
        this.machoRect = new Rectangle();
        this.machoRect.x = 0;
        this.machoRect.y = random.nextInt(this.config.height);
        this.machoRect.width = this.config.spriteRes;
        this.machoRect.height = this.config.spriteRes;
    }

    private void initAssets() {
        enemyIdleAnimation = new Animation<TextureRegion>(0.35f, assets.getTexture("enemy_idle"));
        enemyUpAnimation = new Animation<TextureRegion>(0.35f, assets.getTexture("enemy_up"));
        enemyDownAnimation = new Animation<TextureRegion>(0.35f, assets.getTexture("enemy_down"));
        enemyLeftAnimation = new Animation<TextureRegion>(0.35f, assets.getTexture("enemy_left"));
        enemyRightAnimation = new Animation<TextureRegion>(0.35f, assets.getTexture("enemy_right"));
        enemyHappyAnimation = new Animation<TextureRegion>(0.35f, assets.getTexture("enemy_happy"));
    }

    public Animation<TextureRegion> getAnimation() {
        if (currentAnimation == null) {
            currentAnimation = enemyIdleAnimation;
        }
        return isHappy ? enemyHappyAnimation : currentAnimation;
    }

    public void handleRandomMoving(float delta) {
        timer += delta;
        if (timer >= 1f) {
            xRandom = random.nextInt(2) - 1;
            yRandom = random.nextInt(2) - 1;
        }

        if (xRandom > yRandom) {
           if (xRandom > 0) {
               currentAnimation = enemyRightAnimation;
           } else {
               currentAnimation = enemyLeftAnimation;
           }
        } else {
            if (yRandom > 0) {
                currentAnimation = enemyUpAnimation;
            } else {
                currentAnimation = enemyDownAnimation;
            }
        }

        this.machoRect.x += xRandom;
        this.machoRect.y += yRandom;

        if (timer >= 1f) timer -= 1f;
    }

    public void setHappy(boolean isHappy) {
        this.isHappy = isHappy;
    }
}
