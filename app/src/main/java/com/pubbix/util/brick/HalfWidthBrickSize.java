package com.pubbix.util.brick;

import com.wayfair.brickkit.size.SimpleBrickSize;

public class HalfWidthBrickSize extends SimpleBrickSize {

    public HalfWidthBrickSize() {
        super(BrickHelper.HALF_WIDTH);
    }

    @Override
    protected int size() {
        return BrickHelper.HALF_WIDTH;
    }
}
