package ru.stanislawmnizhek.machogym.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.stanislawmnizhek.machogym.Config;
import ru.stanislawmnizhek.machogym.MachoGym;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	    Config gameConfig = new Config();
		config.title = "MACHO MACHO GYM";
		config.width = gameConfig.width * 2;
		config.height = gameConfig.height * 2;
		new LwjglApplication(new MachoGym(), config);
	}
}
