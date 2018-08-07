package com.pubbix.util.brick.common;

import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;

import com.pubbix.R;
import com.wayfair.brickkit.BrickViewHolder;
import com.wayfair.brickkit.brick.BaseBrick;

public class ImageBrick extends BaseBrick {
    @DrawableRes int imageSrc;

    public ImageBrick(int imageSrc) {
        this.imageSrc = imageSrc;
    }

    @Override
    public void onBindData(BrickViewHolder brickViewHolder) {
        ImageBrickHolder holder = (ImageBrickHolder) brickViewHolder;
        holder.imageView.setImageResource(imageSrc);
    }

    @Override
    public int getLayout() {
        return R.layout.brick_image;
    }

    @Override
    public BrickViewHolder createViewHolder(View view) {
        return new ImageBrickHolder(view);
    }

    private static class ImageBrickHolder extends BrickViewHolder {
        private ImageView imageView;

        ImageBrickHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
