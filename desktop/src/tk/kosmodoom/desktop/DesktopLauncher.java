package tk.kosmodoom.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import tk.kosmodoom.Config;
import tk.kosmodoom.Kosmodoom;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	    Config gameConfig = new Config();
		config.title = "Kosmodoom";
		config.width = gameConfig.width;
		config.height = gameConfig.height;
		new LwjglApplication(new Kosmodoom(), config);
	}
}
