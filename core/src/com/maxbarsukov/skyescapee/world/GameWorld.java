package com.maxbarsukov.skyescapee.world;

import com.maxbarsukov.skyescapee.entities.Player;
import com.maxbarsukov.skyescapee.helpers.AssetLoader;
import com.maxbarsukov.skyescapee.helpers.ScrollHandler;
import com.maxbarsukov.skyescapee.world.interfaces.GameEntities;
import com.maxbarsukov.skyescapee.world.interfaces.Status;

import static com.maxbarsukov.skyescapee.Main.SFX_VOLUME;
import static com.maxbarsukov.skyescapee.entities.Ground.GROUND_OFFSET_Y;


public class GameWorld implements GameEntities, Status {

    private static final int PLAYER_DEFAULT_X = 80;
    private static final int PLAYER_DEFAULT_Y = 300;

    private Player player;
    private ScrollHandler scroller;
    private int score;
    private GameState state;

    public GameWorld() {
        state = GameState.READY;
        player = new Player(PLAYER_DEFAULT_X, PLAYER_DEFAULT_Y, AssetLoader.TEXTURE_PLAYER_ANIMATION.getWidth() / AssetLoader.ANIMATION_PLAYER_FRAME_COUNT,
                AssetLoader.TEXTURE_PLAYER_ANIMATION.getHeight());
        scroller = new ScrollHandler(this, GROUND_OFFSET_Y);
    }

    public void update(float delta) {
        switch (state) {
            case READY:
                updateReady(delta);
                break;

            case PLAYING:
            default:
                updatePlaying(delta);
                break;
        }
    }

    private void updateReady(float delta) {
        scroller.updateGround(delta);
    }

    private void updatePlaying(float delta) {
        if (delta > .15f) {
            delta = .15f;
        }

        player.update(delta);
        scroller.update(delta);
        // Stop in case of collision
        if (player.isAlive() && scroller.collides(player)) {
            scroller.stop();
            AssetLoader.SFX_HIT.play(SFX_VOLUME / 20);
            AssetLoader.SFX_DIE.play(SFX_VOLUME / 5);
            player.die();
        }

        if (!player.isAlive() && scroller.groundCollides(player) && !isHighScore()) {
            player.stop();
            state = GameState.OVER;
            if (score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
                state = GameState.SCORE;
            }
        }
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public ScrollHandler getScroller() {
        return scroller;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void addScore(int increment) {
        score += increment;
    }

    @Override
    public boolean isReady() {
        return state == GameState.READY;
    }

    @Override
    public boolean isGameOver() {
        return state == GameState.OVER;
    }

    @Override
    public boolean isHighScore() {
        return state == GameState.SCORE;
    }

    @Override
    public void start() {
        state = GameState.PLAYING;
    }

    @Override
    public void restart() {
        state = GameState.READY;
        score = 0;
        player.onRestart(PLAYER_DEFAULT_X, PLAYER_DEFAULT_Y);
        scroller.onRestart(GROUND_OFFSET_Y);
    }
}
