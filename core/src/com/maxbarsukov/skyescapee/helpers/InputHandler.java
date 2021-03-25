package com.maxbarsukov.skyescapee.helpers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.maxbarsukov.skyescapee.entities.Player;
import com.maxbarsukov.skyescapee.world.interfaces.Status;


public class InputHandler implements InputProcessor {

    private final Player player;
    private final Status status;

    public InputHandler(Player player, Status status) {
        this.player = player;
        this.status = status;
    }

    @Override
    public boolean keyDown(int keycode) {
        return keycode == Input.Keys.SPACE && handleBird();
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return handleBird();
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    private boolean handleBird() {
        if (status.isReady()) {
            status.start();
        }

        player.onClick();

        if (status.isGameOver() || status.isHighScore()) {
            status.restart();
        }

        return true;
    }
}
