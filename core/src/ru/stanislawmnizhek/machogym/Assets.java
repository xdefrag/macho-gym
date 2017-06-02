package ru.stanislawmnizhek.machogym;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.badlogic.gdx.utils.Array;

public class Assets {
    private TextureAtlas atlas;
    private Animation animation;

    public Assets() {
        if (atlas == null) {
            atlas = new TextureAtlas(Gdx.files.internal("machopack.atlas"));
        }
    }

    public Array<TextureAtlas.AtlasRegion> getTexture(String name) {
        return atlas.findRegions(name);
    }
}
