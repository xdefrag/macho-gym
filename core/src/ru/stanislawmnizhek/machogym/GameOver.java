package ru.stanislawmnizhek.machogym;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class GameOver implements Screen {
    private final MachoGym game;
    private OrthographicCamera camera;
    private GlyphLayout layoutInfo;
    private GlyphLayout layoutScore;
    private GlyphLayout layoutPress;

    GameOver(MachoGym game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.config.width, game.config.height);

        game.sounds.gameover.play();

        layoutInfo = new GlyphLayout(game.font, "YOU WERE TOUCHED");
        layoutScore = new GlyphLayout(game.font, "SCORE " + game.score);
        layoutPress = new GlyphLayout(game.font, "PRESS START TO WORKOUT AGAIN");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.bg.drawBackground(game.batch, delta);
        game.font.draw(game.batch, layoutInfo, (game.config.width - layoutInfo.width) / 2, game.config.height / 2 + 20);
        game.font.draw(game.batch, layoutScore, (game.config.width - layoutScore.width) / 2, game.config.height / 2);
        game.font.draw(game.batch, layoutPress, (game.config.width - layoutPress.width) / 2, game.config.height / 2 - 20);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER) || Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {

    }
}
