package com.maxbarsukov.skyescapee.entities;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import java.util.Random;

public class Tube extends Scrollable {

    public static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = -180;
    private static final int HIGHEST_OPENING = 200;

    private Random random;
    private Rectangle boundsBottom;
    private Rectangle boundsTop;
    private boolean scored;

    public Tube(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
        random = new Random();
        position.y = generateY();
        boundsBottom = new Rectangle(x, y, width, height);
        boundsTop = new Rectangle(x, y + TUBE_GAP + height, width, height);
    }

    @Override public void update(float delta) {
        // Call the update method in the superclass (Scrollable)
        super.update(delta);
        boundsBottom.setPosition(position.x, position.y);
        boundsTop.setPosition(position.x, position.y + TUBE_GAP + height);
    }

    @Override public void reset(float newX) {
        super.reset(newX);
        position.y = generateY();
        scored = false;
    }

    public boolean isScored() {
        return scored;
    }

    public void setScored(boolean scored) {
        this.scored = scored;
    }

    private int generateY() {
        return random.nextInt(HIGHEST_OPENING) + LOWEST_OPENING;
    }

    @Override public boolean collides(Player player) {
        return position.x < player.getTailX() && (Intersector.overlaps(player.getBounds(),
                boundsTop) || Intersector.overlaps(player.getBounds(), boundsBottom));
    }

    @Override public void onReset(float x, float y, float scrollSpeed) {
        velocity.x = scrollSpeed;
        reset(x);
    }
}
