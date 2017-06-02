package ru.stanislawmnizhek.machogym;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;


public class Player extends Macho {
    private int life;
    private Config config;
    private Assets assets;
    public Animation<TextureRegion> playerIdleAnimation;
    public Animation<TextureRegion> playerUpAnimation;
    public Animation<TextureRegion> playerDownAnimation;
    public Animation<TextureRegion> playerLeftAnimation;
    public Animation<TextureRegion> playerRightAnimation;

    public Player(Assets assets, Config config) {
        super(config);
        this.config = config;
        this.assets = assets;
        initAssets();
        life = this.config.lifeAmount;
    }

    private void initAssets() {
        playerIdleAnimation = new Animation<>(0.35f, assets.getTexture("player_idle"));
        playerUpAnimation = new Animation<>(0.35f, assets.getTexture("player_up"));
        playerDownAnimation = new Animation<>(0.35f, assets.getTexture("player_down"));
        playerLeftAnimation = new Animation<>(0.35f, assets.getTexture("player_left"));
        playerRightAnimation = new Animation<>(0.35f, assets.getTexture("player_right"));
    }

    public Animation<TextureRegion> getAnimation(Control control) {
        Animation<TextureRegion> currentAnimation;

        if (control.getDirection().equals(control.UP)) {
            currentAnimation = playerUpAnimation;
        } else if (control.getDirection().equals(control.DOWN)) {
            currentAnimation = playerDownAnimation;
        } else if (control.getDirection().equals(control.LEFT)) {
            currentAnimation = playerLeftAnimation;
        } else if (control.getDirection().equals(control.RIGHT)) {
            currentAnimation = playerRightAnimation;
        } else {
            currentAnimation = playerIdleAnimation;
        }

        return currentAnimation;
    }

    public boolean isCollidesWith(Macho obj) {
        return Intersector.overlaps(this.machoRect, obj.getMachoRect());
    }

    public int getLifeAmount() {
        return life;
    }

    public void removeOneLife() {
        life--;
    }

    public boolean isPlayerDead() {
        return life == 0;
    }
}
