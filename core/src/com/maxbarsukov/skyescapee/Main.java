package com.maxbarsukov.skyescapee;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Main extends Game {
	public static final String TITLE = "Skyescapee";
	public static final float MUSIC_VOLUME = 0.03f;
	public static final float SFX_VOLUME = 0.5f;
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final int SCORE_POSITION = 25;

	@Override
	public void create() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		initMusic();
	}

	private void initMusic() {
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
