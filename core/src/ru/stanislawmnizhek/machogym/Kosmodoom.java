package ru.stanislawmnizhek.machogym;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Random;

public class Kosmodoom extends ApplicationAdapter {
    private Config config = new Config();
    private Random random = new Random();
    private Control control;
    private BitmapFont font;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Player player;
    private Array<Enemy> enemies = new Array<>();
    private Array<Coin> coins = new Array<>();
    private int score;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, config.width, config.height);

        batch = new SpriteBatch();
        player = new Player();
        control = new Control();

        enemies.add(new Enemy());

        for (int i = 0; i < 5; i = i + 1) {
            coins.add(new Coin());
        }

        font = Font.getFont();
    }

    @Override
    public void render() {
        if (player.isPlayerDead()) {
            dispose();
        }

        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        // Machos
        batch.draw(
                player.getSprite(),
                player.getX(), player.getY()
        );
        for (final Enemy enemy : enemies) {
            batch.draw(enemy.getSprite(), enemy.getX(), enemy.getY());
        }
        for (final Coin coin : coins) {
            batch.draw(coin.getSprite(), coin.getX(), coin.getY());
        }

        // UI
        font.draw(batch, "score " + score, 10, config.height - 10);
        font.draw(batch, "life: " + player.getLifeAmount(), 10, config.height - 20);
        batch.end();


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
                        random.nextInt(config.width + 1)
                );
                enemy.setY(
                        random.nextInt(config.height + 1)
                );
            }
        }

        for (Coin coin : coins) {
            if (player.isCollidesWith(coin)) {
                score = score + 1;
                coin.setX(
                        random.nextInt(config.width + 1)
                );
                coin.setY(
                        random.nextInt(config.height + 1)
                );
            }
        }

        if ((score != 0) && (0 == (score % 5))) {
            score = score + 1;
            enemies.add(new Enemy());
        }
    }


    @Override
    public void dispose() {
        player.dispose();
        batch.dispose();
    }
}
