package com.maxbarsukov.skyescapee.entities;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private static final int FLAP = 230;
    private static final int GRAVITY = -460;
    private static final int VELOCITY_LIMIT = -345;
    private static final int FALL = -110;
    private static final int FLAP_AT = -70;

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private Circle bounds;

    private float rotation;
    private int width;
    private int height;
    private boolean alive;

    public Player(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, GRAVITY);
        bounds = new Circle();
        alive = true;
    }

    public boolean isFalling() {
        return velocity.y < FALL;
    }

    public boolean doFlap() {
        return alive && velocity.y > FLAP_AT;
    }

    public void die() {
        alive = false;
        velocity.y = 0;
    }

    public void decelerate() {
        acceleration.y = 0;
    }

    public void stop() {
        velocity.y = 0;
        acceleration.y = 0;
    }

    public float getX() {
        return position.x;
    }

    public float getTailX() {
        return position.x + width;
    }

    public float getY() {
        return position.y;
    }

    public float getTailY() {
        return position.y + height;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }

    public Circle getBounds() {
        return bounds;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void onRestart(float x, float y) {
        position.set(x, y);
        rotation = 0;
        velocity.set(0 , 0);
        acceleration.set(0, GRAVITY);
        alive = true;
    }
}
