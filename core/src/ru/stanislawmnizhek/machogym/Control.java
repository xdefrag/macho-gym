package ru.stanislawmnizhek.machogym;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Control {
    private Config config;

    public Control() {
        config = new Config();
    }

    public void handleInput(Player player, float delta) {
        float x = player.getX();
        float y = player.getY();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            player.setX(x -= config.movementStep * delta);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            player.setX(x += config.movementStep * delta);
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            player.setY(y += config.movementStep * delta);
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            player.setY(y -= config.movementStep * delta);
    }
}
