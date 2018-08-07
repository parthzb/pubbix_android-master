package com.pubbix.util.brick.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pubbix.R;
import com.pubbix.util.brick.FullWidthBrickSize;
import com.wayfair.brickkit.BrickDataManager;
import com.wayfair.brickkit.BrickViewHolder;
import com.wayfair.brickkit.brick.BaseBrick;
import com.wayfair.brickkit.padding.BrickPadding;

public abstract class CarouselBrick extends BaseBrick {

    protected BrickDataManager dataManager = new BrickDataManager(DEFAULT_MAX_SPAN_COUNT);

    private int orientation;

    public CarouselBrick(BrickPadding brickPadding, int orientation) {
        super(new FullWidthBrickSize(), brickPadding);
        this.orientation = orientation;
    }

    @Override
    public void onBindData(BrickViewHolder holder) {
        CarouselBrickViewHolder viewHolder = (CarouselBrickViewHolder) holder;
        viewHolder.carousel.setNestedScrollingEnabled(false);
        dataManager.setRecyclerView(holder.itemView.getContext(), viewHolder.carousel, orientation,
                false, null);
    }

    @Override
    public int getLayout() {
        return R.layout.brick_carousel;
    }

    @Override
    public BrickViewHolder createViewHolder(View view) {
        return new CarouselBrickViewHolder(view);
    }

    public BrickDataManager getDataManager() {
        return dataManager;
    }

    private static class CarouselBrickViewHolder extends BrickViewHolder {
        final RecyclerView carousel;

        CarouselBrickViewHolder(View itemView) {
            super(itemView);
            carousel = itemView.findViewById(R.id.recycler_view);
        }
    }
}
