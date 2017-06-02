package ru.stanislawmnizhek.machogym;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Player extends Macho {
    private Assets assets;
    private int life;
    public Animation<TextureRegion> playerIdleAnimation;
    public Animation<TextureRegion> playerUpAnimation;
    public Animation<TextureRegion> playerDownAnimation;
    public Animation<TextureRegion> playerLeftAnimation;
    public Animation<TextureRegion> playerRightAnimation;

    public Player() {
        initAssets();
        life = this.config.lifeAmount;
    }

    private void initAssets() {
        assets = new Assets();
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
        if (Math.abs(this.machoRect.y - obj.getY()) < config.collisionRes
                && Math.abs(this.machoRect.x - obj.getX()) < config.collisionRes) {
            return true;
        }
        return false;
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
