package ru.stanislawmnizhek.machogym;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;

class Background {
    private final MachoGym game;

    private Random random;
    private Animation<TextureRegion> planet;
    private Animation<TextureRegion> starsLayer1;
    private Animation<TextureRegion> starsLayer2;
    private Animation<TextureRegion> starsLayer3;
    private float planetTimer = 0f;
    private int planetX = 0;
    private int planetY = 0;
    private boolean isPlanetDirectionRight = true;
    private int starsX = 0;
    private int starsY = 0;

    Background(MachoGym game) {
        this.game = game;

        random = new Random();
        initAssets();
        planetY = random.nextInt(game.config.height);
    }

    private void initAssets() {
        planet = new Animation<TextureRegion>(0.33f, game.assets.getTexture("planet"));
        starsLayer1 = new Animation<TextureRegion>(0, game.assets.getTexture("stars_layer1"));
        starsLayer2 = new Animation<TextureRegion>(0, game.assets.getTexture("stars_layer2"));
        starsLayer3 = new Animation<TextureRegion>(0, game.assets.getTexture("stars_layer3"));
    }

    void drawBackground(SpriteBatch batch, float delta) {
        // STARS
        batch.draw(
                starsLayer1.getKeyFrame(delta, false),
                starsX, starsY
        );
        batch.draw(
                starsLayer2.getKeyFrame(delta, false),
                starsX, starsY - 3
        );
        batch.draw(
                starsLayer3.getKeyFrame(delta, false),
                starsX + 1, starsY
        );

        // PLANET
        planetTimer += delta;

        if (planetTimer > 1000f) {
            batch.draw(
                    planet.getKeyFrame(delta, true),
                    planetX, planetY
            );

            if (isPlanetDirectionRight) {
                planetX += 1;
            } else {
                planetX -= 1;
            }

            if (planetX > game.config.width + game.config.spriteRes || planetX < 0 - game.config.spriteRes) {
                planetTimer = 0f;
                planetY = random.nextInt(game.config.height);
                isPlanetDirectionRight = !isPlanetDirectionRight;
            }
        }


    }
}