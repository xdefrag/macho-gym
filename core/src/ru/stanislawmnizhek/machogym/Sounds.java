package ru.stanislawmnizhek.machogym;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
    public Sound start;
    public Sound touched;
    public Sound power;
    public Sound gameover;
    public Music machogym;

    public Sounds() {
        initSounds();
    }

    private void initSounds() {
        start = Gdx.audio.newSound(Gdx.files.internal("sounds/play.ogg"));
        touched = Gdx.audio.newSound(Gdx.files.internal("sounds/touched.ogg"));
        power = Gdx.audio.newSound(Gdx.files.internal("sounds/power.ogg"));
        gameover = Gdx.audio.newSound(Gdx.files.internal("sounds/gameover.ogg"));
        machogym = Gdx.audio.newMusic(Gdx.files.internal("sounds/machogym.ogg"));
    }
}
