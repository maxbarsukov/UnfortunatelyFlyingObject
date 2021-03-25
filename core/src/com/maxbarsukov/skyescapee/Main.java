package com.maxbarsukov.skyescapee;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.maxbarsukov.skyescapee.helpers.AssetLoader;
import com.maxbarsukov.skyescapee.screens.SplashScreen;


public class Main extends Game {
	public static final String TITLE = "Skyescapee";
	public static final float MUSIC_VOLUME = 0.03f; //3%
	public static final float SFX_VOLUME = 0.5f; //50%
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final int SCORE_POSITION = 25;

	@Override
	public void create() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		AssetLoader.load();
		setScreen(new SplashScreen(this));
		initMusic();
	}

	private void initMusic() {
		AssetLoader.MUSIC.setVolume(MUSIC_VOLUME);
		AssetLoader.MUSIC.play();
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
