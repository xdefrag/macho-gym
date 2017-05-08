package tk.kosmodoom;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.Random;


public class Kosmodoom extends ApplicationAdapter {
    private Config config = new Config();
    private Random random = new Random();
    private BitmapFont font;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture playerImage;
    private Texture enemyImage;
    private Texture coinImage;
    private Rectangle player;
    private Rectangle enemy;
    private Array<Rectangle> enemies = new Array<>();
    private Rectangle coin;
    private Array<Rectangle> coins = new Array<>();
    private int score;
    private int life = 3;
    private boolean isPlayerDead = false;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, config.width, config.height);

        batch = new SpriteBatch();

        playerImage = new Texture(Gdx.files.internal("demigod_m.png"));
        player = new Rectangle();
        player.x = config.width / 2;
        player.y = config.height / 2;
        player.width = 32;
        player.height = 32;

        enemyImage = new Texture(Gdx.files.internal("vampire_m.png"));
        enemy = new Rectangle(
                random.nextInt(config.width + 1),
                random.nextInt(config.height + 1),
                config.spriteRes,
                config.spriteRes
        );
        enemies.add(enemy);

        coinImage = new Texture(Gdx.files.internal("sausage.png"));
        for (int i = 0; i < 5; i = i + 1) {
            coin = new Rectangle(
                    random.nextInt(config.width + 1),
                    random.nextInt(config.height + 1),
                    16,
                    16
            );
            coins.add(coin);
        }

        font = Font.getFont();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        if (isPlayerDead) {
            font.draw(batch, "You are dead", config.width / 2 - 100, config.height / 2);
            font.draw(batch, "Score " + score, config.width / 2 - 100, config.height / 2 - 20);
        } else {

            font.draw(batch, "Zombies!!!", 10, config.height - 10);
            font.draw(batch, "score " + score, 10, config.height - 20);
            font.draw(batch, "life: " + life, 10, config.height - 30);
            batch.draw(playerImage, player.x, player.y);
            for (Rectangle enemy : enemies) {
                batch.draw(enemyImage, enemy.x, enemy.y);
            }
            for (Rectangle coin : coins) {
                batch.draw(coinImage, coin.x, coin.y);
            }
        }
        batch.end();

        for (Rectangle enemy : enemies) {
            enemy.x = enemy.x + random.nextInt(6) - 3;
            enemy.y = enemy.y + random.nextInt(6) - 3;

            if (enemy.x < 0) enemy.x = config.width - config.spriteRes;
            if (enemy.x > config.width - config.spriteRes) enemy.x = 0;
            if (enemy.y < 0) enemy.y = config.height - config.spriteRes;
            if (enemy.y > config.height - config.spriteRes) enemy.y = 0;

        }

        for (Rectangle coin : coins) {
            if (coin.x > config.width - config.spriteRes) coin.x = 0;
            if (coin.y > config.height - config.spriteRes) coin.y = 0;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) player.x -= config.movementStep * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player.x += config.movementStep * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) player.y += config.movementStep * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) player.y -= config.movementStep * Gdx.graphics.getDeltaTime();

        if (player.x < 0) player.x = config.width - config.spriteRes;
        if (player.x > config.width - config.spriteRes) player.x = 0;
        if (player.y < 0) player.y = config.height - config.spriteRes;
        if (player.y > config.height - config.spriteRes) player.y = 0;

        for (Rectangle enemy : enemies) {
            if (Math.abs(player.y - enemy.y) < config.spriteRes && Math.abs(player.x - enemy.x) < config.spriteRes) {
                life = life - 1;
                enemy.x = random.nextInt(config.width + 1);
                enemy.y = random.nextInt(config.height + 1);
            }
        }

        for (Rectangle coin : coins) {
            if (Math.abs(player.y - coin.y) < config.spriteRes && Math.abs(player.x - coin.x) < config.spriteRes) {
                score = score + 1;
                coin.x = random.nextInt(config.width + 1);
                coin.y = random.nextInt(config.height + 1);
            }
        }

        if ((score != 0) && (0 == (score % 5))) {
            score = score + 1;
            enemies.add(
                    new Rectangle(
                            random.nextInt(config.width + 1),
                            random.nextInt(config.height + 1),
                            config.spriteRes,
                            config.spriteRes
                    )
            );
        }

        if (life == 0) {
            playerImage.dispose();
            isPlayerDead = true;
        }
    }

    @Override
    public void dispose() {
        playerImage.dispose();
        enemyImage.dispose();
        batch.dispose();
    }
}
