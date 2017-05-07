package tk.kosmodoom.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import tk.kosmodoom.Kosmodoom;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Kosmodoom";
		config.width = 800;
		config.height = 600;
		new LwjglApplication(new Kosmodoom(), config);
	}
}
