package com.maxbarsukov.skyescapee.entities;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Ground extends Scrollable {
    public static final int GROUND_OFFSET_Y = -20;

    private Rectangle bounds;

    public Ground(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
        bounds = new Rectangle(x, y, width, height);
    }

    @Override public void update(float delta) {
        super.update(delta);
        bounds.setPosition(position.x, position.y);
    }

    @Override public int getWidth() {
        return width;
    }

    @Override public int getHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    @Override public boolean collides(Player player) {
        return position.y + height > player.getY() && (Intersector.overlaps(player.getBounds(), bounds));
    }
}
