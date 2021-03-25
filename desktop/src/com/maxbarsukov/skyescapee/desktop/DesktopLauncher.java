package com.maxbarsukov.skyescapee.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.maxbarsukov.skyescapee.Main;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Main.TITLE;
		config.height = Main.HEIGHT;
		config.width = Main.WIDTH;
		new LwjglApplication(new Main(), config);
	}
}
