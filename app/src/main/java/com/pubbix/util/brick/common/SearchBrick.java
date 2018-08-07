package com.pubbix.util.brick.common;

import android.view.View;

import com.pubbix.R;
import com.wayfair.brickkit.BrickViewHolder;
import com.wayfair.brickkit.brick.BaseBrick;

public class SearchBrick extends BaseBrick {

    public SearchBrick() {
    }

    @Override
    public void onBindData(BrickViewHolder brickViewHolder) {
        ParagraphBrickHolder holder = (ParagraphBrickHolder) brickViewHolder;
    }

    @Override
    public int getLayout() {
        return R.layout.brick_search_view;
    }

    @Override
    public BrickViewHolder createViewHolder(View view) {
        return new ParagraphBrickHolder(view);
    }

    private static class ParagraphBrickHolder extends BrickViewHolder {

        ParagraphBrickHolder(View itemView) {
            super(itemView);
        }
    }
}
