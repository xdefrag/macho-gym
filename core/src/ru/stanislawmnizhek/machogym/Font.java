package ru.stanislawmnizhek.machogym;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

class Font {
    static BitmapFont getFont() {
//        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arcadeclassic.ttf"));
//        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
//        parameter.size = 16;
//        parameter.kerning = false;
//        parameter.genMipMaps = true;
//        parameter.minFilter = Texture.TextureFilter.MipMapNearestLinear;
//        BitmapFont font = fontGenerator.generateFont(parameter);
//        fontGenerator.dispose();
//        return font;
        BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/macho.fnt"));
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(0.3f);
        return font;
    }
}
