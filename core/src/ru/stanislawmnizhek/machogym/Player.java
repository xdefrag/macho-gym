package ru.stanislawmnizhek.machogym;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Player extends Macho {
    private Texture playerIdle;
    private int life;

    public Player() {
        initAssets();
        life = this.config.lifeAmount;
    }

    private void initAssets() {
        playerIdle = new Texture(Gdx.files.internal("player_idle_1.png"));
    }

    public Texture getSprite() {
        return playerIdle;
    }

    public boolean isCollidesWith(Macho obj) {
        if (Math.abs(this.machoRect.y - obj.getY()) < config.spriteRes
                && Math.abs(this.machoRect.x - obj.getX()) < config.spriteRes) {
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
