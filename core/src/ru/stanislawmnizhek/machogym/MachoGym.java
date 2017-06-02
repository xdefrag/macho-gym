package ru.stanislawmnizhek.machogym;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MachoGym extends Game {
    public SpriteBatch batch;
    public BitmapFont font;
    public BitmapFont fontTitle;
    public Config config;
    public Assets assets;
    public Background bg;
    public Sounds sounds;
    public int score;

    public void create() {
        batch = new SpriteBatch();
        font = Font.getFont();
        config = new Config();
        assets = new Assets();
        bg = new Background(this);
        sounds = new Sounds();
        score = 0;
        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
