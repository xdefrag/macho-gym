package tk.kosmodoom;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.Random;


public class Kosmodoom extends ApplicationAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture playerImage;
    private Texture enemyImage;
    private Rectangle player;
    private Rectangle enemy;
    private Array<Rectangle> enemies = new Array<>();
    private BitmapFont font;
    private int score;
    private int life = 3;
    private Random random;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);

        batch = new SpriteBatch();
        random = new Random();

        playerImage = new Texture(Gdx.files.internal("demigod_m.png"));
        player = new Rectangle();
        player.x = 0;
        player.y = 480;
        player.width = 32;
        player.height = 32;

        enemyImage = new Texture(Gdx.files.internal("vampire_m.png"));
        enemy = new Rectangle(
                random.nextInt(800 + 1),
                random.nextInt(600 + 1),
                32,
                32
        );
        enemies.add(enemy);

        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arcadeclassic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        font = fontGenerator.generateFont(parameter);
        fontGenerator.dispose();

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        font.draw(batch, "Macho Gym!!!", 350, 580);
        font.draw(batch, "score " + score, 380, 560);
        font.draw(batch, "life: " + life, 380, 540);
        batch.draw(playerImage, player.x, player.y);
        for (Rectangle enemy : enemies) {
            batch.draw(enemyImage, enemy.x, enemy.y);
        }
        batch.end();

        for (Rectangle enemy : enemies) {
            enemy.x = enemy.x + 2;
            enemy.y = enemy.y + 2;

            if (enemy.x > 800 - 32) enemy.x = 0;
            if (enemy.y > 600 - 32) enemy.y = 0;
        }


        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) player.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player.x += 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) player.y += 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) player.y -= 200 * Gdx.graphics.getDeltaTime();

        if (player.x < 0) player.x = 800 - 32;
        if (player.x > 800 - 32) player.x = 0;
        if (player.y < 0) player.y = 600 - 32;
        if (player.y > 600 - 32) player.y = 0;

        for (Rectangle enemy : enemies) {
            if (Math.abs(player.y - enemy.y) < 32 && Math.abs(player.x - enemy.x) < 32) {
                life = life - 1;
                enemy.x = random.nextInt(800 + 1);
                enemy.y = random.nextInt(600 + 1);
            }
        }

        if ((score != 0) &&(0 == (score % 5))) {
            score = score + 1;
            enemies.add(
                    new Rectangle(
                            random.nextInt(800 + 1),
                            random.nextInt(600 + 1),
                            32,
                            32
                    )
            );
        }

//        if (life == 0) {
//            playerImage.dispose();
//        }
    }

    @Override
    public void dispose() {
        playerImage.dispose();
        enemyImage.dispose();
        batch.dispose();
    }
}
