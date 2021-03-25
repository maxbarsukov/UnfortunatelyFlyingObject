package com.maxbarsukov.skyescapee.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.maxbarsukov.skyescapee.Main;
import com.maxbarsukov.skyescapee.helpers.InputHandler;
import com.maxbarsukov.skyescapee.world.GameRenderer;
import com.maxbarsukov.skyescapee.world.GameWorld;


public class GameScreen implements Screen {

    private static final String TAG = "GameScreen";
    public static final float WIDTH = Main.WIDTH * 0.617f;
    public static final float HEIGHT = Main.HEIGHT * 0.617f;

    private GameWorld world;
    private GameRenderer renderer;
    private float runTime;

    GameScreen() {
        Gdx.app.log("GameScreen", "attached");

        world = new GameWorld();
        renderer = new GameRenderer(world);
        Gdx.input.setInputProcessor(new InputHandler(world.getPlayer(), world));
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log(TAG, "resize");
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "show");
    }

    @Override
    public void hide() {
        Gdx.app.log(TAG, "hide");
    }

    @Override
    public void pause() {
        Gdx.app.log(TAG, "pause");
    }

    @Override
    public void resume() {
        Gdx.app.log(TAG, "resume");
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
