package ru.stanislawmnizhek.machogym;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Enemy extends Macho {
    private Texture enemyIdle;
    private Random random;
    private float timer = 0f;
    private int xRandom;
    private int yRandom;

    public Enemy() {
        initAssets();
    }

    @Override
    protected void initRect() {
        random = new Random();
        this.machoRect = new Rectangle();
        this.machoRect.x = random.nextInt(this.config.width);
        this.machoRect.y = random.nextInt(this.config.height);
        this.machoRect.width = this.config.spriteRes;
        this.machoRect.height = this.config.spriteRes;
    }

    private void initAssets() {
        enemyIdle = new Texture(Gdx.files.internal("player_idle_1.png"));
    }

    public Texture getSprite() {
        return enemyIdle;
    }

    public void handleRandomMoving(float delta) {
        timer += delta;
        if (timer >= 1f) {
            xRandom = random.nextInt(2) - 1;
            yRandom = random.nextInt(2) - 1;
        }

        this.machoRect.x += xRandom;
        this.machoRect.y += yRandom;

        if (timer >= 1f) timer -= 1f;
    }
}
