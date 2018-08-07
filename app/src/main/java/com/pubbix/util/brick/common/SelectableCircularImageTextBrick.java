package com.pubbix.util.brick.common;

import android.support.annotation.DrawableRes;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import com.pubbix.R;
import com.wayfair.brickkit.BrickViewHolder;
import com.wayfair.brickkit.brick.BaseBrick;
import com.wayfair.brickkit.brick.TouchableBrick;

import de.hdodenhof.circleimageview.CircleImageView;

public class SelectableCircularImageTextBrick extends BaseBrick implements TouchableBrick {
    private String title;
    @DrawableRes
    private int imageId;
    private View.OnClickListener onTouch;

    public SelectableCircularImageTextBrick(String title, int imageId, View.OnClickListener onTouch) {
        this.title = title;
        this.imageId = imageId;
        this.onTouch = onTouch;
    }

    @Override
    public void onBindData(BrickViewHolder brickViewHolder) {
        SelectableTextSubTextIconBrickHolder holder = (SelectableTextSubTextIconBrickHolder) brickViewHolder;
        holder.title.setText(title);
        holder.profileImage.setImageDrawable(holder.profileImage.getContext().getDrawable(imageId));
        holder.layout.setOnClickListener(onTouch);
    }

    @Override
    public int getLayout() {
        return R.layout.brick_selectable_image_text;
    }

    @Override
    public BrickViewHolder createViewHolder(View view) {
        return new SelectableTextSubTextIconBrickHolder(view);
    }

    @Override
    public View.OnClickListener onTouch() {
        return onTouch;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private static class SelectableTextSubTextIconBrickHolder extends BrickViewHolder {
        private final ConstraintLayout layout;
        private final TextView title;
        private CircleImageView profileImage;


        SelectableTextSubTextIconBrickHolder(View itemView) {
            super(itemView);
            this.layout = itemView.findViewById(R.id.layout);
            this.title = itemView.findViewById(R.id.title);
            this.profileImage = itemView.findViewById(R.id.profile_image);
        }
    }
}
