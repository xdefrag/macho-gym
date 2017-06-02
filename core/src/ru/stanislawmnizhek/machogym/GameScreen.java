package ru.stanislawmnizhek.machogym;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class GameScreen implements Screen {
    final MachoGym game;

    private Random random = new Random();
    private Control control;
    private OrthographicCamera camera;
    private Player player;
    private Array<Enemy> enemies = new Array<>();
    private Array<Coin> coins = new Array<>();
    private float elapsedTime = 0;

    public GameScreen(final MachoGym game) {
        this.game = game;
        this.game.score = 0;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.config.width, game.config.height);

        player = new Player(game.assets, game.config);
        control = new Control(game.config);

        enemies.add(new Enemy(game.assets, game.config));

        for (int i = 0; i < 5; i = i + 1) {
            coins.add(new Coin(game.assets, game.config));
        }
    }

    @Override
    public void render(float delta) {
        if (player.isPlayerDead()) {
            game.setScreen(new GameOver(game));
            dispose();
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        // Machos
        elapsedTime += Gdx.graphics.getDeltaTime();

        game.bg.drawBackground(game.batch, elapsedTime);

        game.batch.draw(
                player.getAnimation(control).getKeyFrame(elapsedTime, true),
                player.getX(), player.getY()
        );
        for (final Enemy enemy : enemies) {
            game.batch.draw(enemy.getAnimation().getKeyFrame(elapsedTime, true),
                    enemy.getX(), enemy.getY());
        }
        for (final Coin coin : coins) {
            game.batch.draw(coin.getAnimation().getKeyFrame(elapsedTime, true),
                    coin.getX(), coin.getY());
        }

        // UI
        game.font.draw(game.batch, "score " + game.score, 10, game.config.height - 10);
        game.font.draw(game.batch, "life: " + player.getLifeAmount(), 10, game.config.height - 20);
        game.batch.end();


        for (final Enemy enemy : enemies) {
            enemy.handleRandomMoving(Gdx.graphics.getDeltaTime());
            enemy.checkXY();
        }

        for (final Coin coin : coins) {
            coin.checkXY();
        }

        control.handleInput(player, Gdx.graphics.getDeltaTime());
        player.checkXY();

        for (Enemy enemy : enemies) {
            if (player.isCollidesWith(enemy)) {
                player.removeOneLife();
                enemy.setX(
                        random.nextInt(game.config.width + 1)
                );
                enemy.setY(
                        random.nextInt(game.config.height + 1)
                );
            }
        }

        for (Coin coin : coins) {
            if (player.isCollidesWith(coin)) {
                game.score = game.score + 1;
                coin.setX(
                        random.nextInt(game.config.width + 1)
                );
                coin.setY(
                        random.nextInt(game.config.height + 1)
                );
            }
        }

        if ((game.score != 0) && (0 == (game.score % 5))) {
            game.score = game.score + 1;
            enemies.add(new Enemy(game.assets, game.config));
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
