package com.maxbarsukov.skyescapee.accessors;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteAccessor implements TweenAccessor<Sprite> {

    public static final int ALPHA = 1;

    @Override
    public int getValues(Sprite target, int tweenType, float[] returnValues) {
        if (tweenType == ALPHA) {
            returnValues[0] = target.getColor().a;
            return 1;
        }
        return 0;
    }

    @Override
    public void setValues(Sprite target, int tweenType, float[] newValues) {
        if (tweenType == ALPHA) {
            target.setColor(1, 1, 1, newValues[0]);
        }
    }
}
