package com.maxbarsukov.skyescapee.world.interfaces;

public interface Status {
    boolean isReady();
    boolean isGameOver();
    boolean isHighScore();
    void start();
    void restart();
}
