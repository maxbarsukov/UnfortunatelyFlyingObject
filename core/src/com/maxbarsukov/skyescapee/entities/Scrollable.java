package com.maxbarsukov.skyescapee.entities;

import com.badlogic.gdx.math.Vector2;

public abstract class Scrollable {

    protected Vector2 position;
    protected Vector2 velocity;

    protected int width;
    protected int height;

    protected boolean isScrolled;

    Scrollable(float x, float y, int width, int height, float scrollSpeed) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(scrollSpeed, 0);
        isScrolled = false;
    }

    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));

        if (position.x + width < 0) {
            isScrolled = true;
        }
    }

    public void reset(float newX) {
        position.x = newX;
        isScrolled = false;
    }

    public boolean isScrolled() {
        return isScrolled;
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void stop() {
        velocity.x = 0;
    }

    public void onReset(float x, float y, float scrollSpeed) {
        position.set(x , y);
        velocity.x = scrollSpeed;
    }

    public abstract boolean collides(Player player);
}
