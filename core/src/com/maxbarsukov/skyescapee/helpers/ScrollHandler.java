package com.maxbarsukov.skyescapee.helpers;

import com.maxbarsukov.skyescapee.entities.Ground;
import com.maxbarsukov.skyescapee.entities.Player;
import com.maxbarsukov.skyescapee.entities.Tube;
import com.maxbarsukov.skyescapee.screens.GameScreen;
import com.maxbarsukov.skyescapee.world.interfaces.Score;

import static com.maxbarsukov.skyescapee.Main.SFX_VOLUME;
import static com.maxbarsukov.skyescapee.entities.Tube.TUBE_GAP;
import static com.maxbarsukov.skyescapee.helpers.AssetLoader.GROUND;
import static com.maxbarsukov.skyescapee.helpers.AssetLoader.TUBE;


public class ScrollHandler {
    private static final int SCROLL_SPEED = -60;

    private Score score;
    private Ground frontGround;
    private Ground backGround;
    private Tube tube1;
    private Tube tube2;
    private Tube tube3;

    public ScrollHandler(Score score, float yPos) {
        this.score = score;
        frontGround = new Ground(0, yPos, GROUND.getWidth(), GROUND.getHeight(), SCROLL_SPEED);
        backGround = new Ground(frontGround.getTailX(), yPos, GROUND.getWidth(), GROUND.getHeight(),
                SCROLL_SPEED);

        tube1 = new Tube(GameScreen.WIDTH, 0, TUBE.getWidth(), TUBE.getHeight(), SCROLL_SPEED);
        tube2 =
                new Tube(tube1.getTailX() + TUBE_GAP, 0, TUBE.getWidth(), TUBE.getHeight(), SCROLL_SPEED);
        tube3 =
                new Tube(tube2.getTailX() + TUBE_GAP, 0, TUBE.getWidth(), TUBE.getHeight(), SCROLL_SPEED);
    }

    public void update(float delta) {
        updateGround(delta);
        updateTubes(delta);
    }

    public void updateGround(float delta) {
        frontGround.update(delta);
        backGround.update(delta);

        if (frontGround.isScrolled()) {
            frontGround.reset(backGround.getTailX());
        } else if (backGround.isScrolled()) {
            backGround.reset(frontGround.getTailX());
        }
    }

    public void updateTubes(float delta) {
        tube1.update(delta);
        tube2.update(delta);
        tube3.update(delta);

        // Check if scrolled left and reset accordingly
        if (tube1.isScrolled()) {
            tube1.reset(tube3.getTailX() + TUBE_GAP);
        } else if (tube2.isScrolled()) {
            tube2.reset(tube1.getTailX() + TUBE_GAP);
        } else if (tube3.isScrolled()) {
            tube3.reset(tube2.getTailX() + TUBE_GAP);
        }
    }

    public Ground getFrontGround() {
        return frontGround;
    }

    public Ground getBackGround() {
        return backGround;
    }

    public Tube getTube1() {
        return tube1;
    }

    public Tube getTube2() {
        return tube2;
    }

    public Tube getTube3() {
        return tube3;
    }

    public void stop() {
        frontGround.stop();
        backGround.stop();
        tube1.stop();
        tube2.stop();
        tube3.stop();
    }

    public boolean tubeCollides(Player player) {
        checkScore(player);
        return tube1.collides(player) || tube2.collides(player) || tube3.collides(player);
    }

    public boolean groundCollides(Player player) {
        return backGround.collides(player) || frontGround.collides(player);
    }

    public boolean collides(Player player) {
        return tubeCollides(player) || groundCollides(player);
    }

    private void checkScore(Player player) {
        if (!tube1.isScored() && tube1.getX() + (tube1.getWidth() / 2) < player.getTailX()) {
            score.addScore(1);
            tube1.setScored(true);
            AssetLoader.SFX_POINT.play(SFX_VOLUME);
        } else if (!tube2.isScored() && tube2.getX() + (tube2.getWidth() / 2) < player.getTailX()) {
            score.addScore(1);
            tube2.setScored(true);
            AssetLoader.SFX_POINT.play(SFX_VOLUME);
        } else if (!tube3.isScored() && tube3.getX() + (tube3.getWidth() / 2) < player.getTailX()) {
            score.addScore(1);
            tube3.setScored(true);
            AssetLoader.SFX_POINT.play(SFX_VOLUME);
        }
    }

    public void onRestart(int y) {
        onRestartGround(y);
        onRestartTubes();
    }

    private void onRestartGround(int y) {
        frontGround.onReset(0, y, SCROLL_SPEED);
        backGround.onReset(frontGround.getTailX(), y, SCROLL_SPEED);
    }

    private void onRestartTubes() {
        tube1.onReset(GameScreen.WIDTH, 0, SCROLL_SPEED);
        tube2.onReset(tube1.getTailX() + TUBE_GAP, 0, SCROLL_SPEED);
        tube3.onReset(tube2.getTailX() + TUBE_GAP, 0, SCROLL_SPEED);
    }
}
