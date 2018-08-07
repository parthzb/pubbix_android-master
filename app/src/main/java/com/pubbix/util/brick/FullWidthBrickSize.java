package com.pubbix.util.brick;

import com.wayfair.brickkit.size.SimpleBrickSize;

public class FullWidthBrickSize extends SimpleBrickSize {

    public FullWidthBrickSize() {
        super(BrickHelper.MAX_SPANS);
    }

    @Override
    protected int size() {
        return BrickHelper.FULL_WIDTH;
    }
}
