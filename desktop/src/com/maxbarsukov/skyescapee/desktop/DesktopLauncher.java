package com.maxbarsukov.skyescapee.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.maxbarsukov.skyescapee.Game;

public class DesktopLauncher {
	public static final int HEIGHT = 800;
	public static final int WIDTH = 480;

	public static final String TITLE = "Skyescapee";

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = TITLE;
		config.height = HEIGHT;
		config.width = WIDTH;
		new LwjglApplication(new Game(), config);
	}
}
