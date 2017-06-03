package ru.stanislawmnizhek.machogym;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MainMenuScreen implements Screen {
    final MachoGym game;
    OrthographicCamera camera;
//    private GlyphLayout layoutTitle;
    private Animation<TextureRegion> logo;
    private GlyphLayout layoutInfo;

    public MainMenuScreen(final MachoGym game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.config.width, game.config.height);

//        layoutTitle = new GlyphLayout(game.font, "MACHO  MACHO  GYM");
        logo = new Animation<TextureRegion>(0, game.assets.getTexture("logo"));
        layoutInfo = new GlyphLayout(game.font, "PRESS START");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.bg.drawBackground(game.batch, delta);
//        game.font.draw(game.batch, layoutTitle, (game.config.width - layoutTitle.width) / 2, game.config.height / 2 + 20);
        game.batch.draw(logo.getKeyFrame(delta), game.config.width / 2 - 114, game.config.height / 2 + 20);
        game.font.draw(game.batch, layoutInfo, (game.config.width - layoutInfo.width) / 2, game.config.height / 2);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER) || Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            game.sounds.start.play();
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
