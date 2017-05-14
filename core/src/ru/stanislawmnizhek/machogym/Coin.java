package ru.stanislawmnizhek.machogym;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Coin extends Macho {
    private Texture coin;
    private Random random;

    public Coin() {
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
        coin = new Texture(Gdx.files.internal("player_idle_1.png"));
    }

    public Texture getSprite() {
        return coin;
    }
}
