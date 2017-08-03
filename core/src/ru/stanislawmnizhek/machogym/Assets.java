package ru.stanislawmnizhek.machogym;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

class Assets {
    private TextureAtlas atlas;

    Assets() {
        if (atlas == null) {
            atlas = new TextureAtlas(Gdx.files.internal("machopack.atlas"));
        }
    }

    Array<TextureAtlas.AtlasRegion> getTexture(String name) {
        return atlas.findRegions(name);
    }
}
