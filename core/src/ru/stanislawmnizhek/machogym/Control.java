package ru.stanislawmnizhek.machogym;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Control {
    private Config config;
    public final String UP = "up";
    public final String DOWN = "down";
    public final String LEFT = "left";
    public final String RIGHT = "right";
    public final String IDLE = "idle";
    private String direction = IDLE;

    public Control(Config config) {
        this.config = config;
    }

    public void handleInput(Player player, float delta) {
        if (player.getLifeAmount() == 0) {
            return;
        }

        float x = player.getX();
        float y = player.getY();
        direction = IDLE;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.setX(x -= config.movementStep * delta);
            direction = LEFT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.setX(x += config.movementStep * delta);
            direction = RIGHT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.setY(y += config.movementStep * delta);
            direction = UP;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.setY(y -= config.movementStep * delta);
            direction = DOWN;
        }
    }

    public String getDirection() {
        return direction;
    }
}
