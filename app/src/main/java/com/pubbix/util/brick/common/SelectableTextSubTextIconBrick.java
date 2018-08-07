package com.pubbix.util.brick.common;

import android.support.annotation.DrawableRes;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pubbix.R;
import com.wayfair.brickkit.BrickViewHolder;
import com.wayfair.brickkit.brick.BaseBrick;
import com.wayfair.brickkit.brick.TouchableBrick;

public class SelectableTextSubTextIconBrick extends BaseBrick implements TouchableBrick {
    private String title;
    private String subTitle;
    @DrawableRes
    private int iconId;
    private View.OnClickListener onTouch;

    public SelectableTextSubTextIconBrick(String title, String subTitle, int iconId,
                                          View.OnClickListener onTouch) {
        this.title = title;
        this.subTitle = subTitle;
        this.iconId = iconId;
        this.onTouch = onTouch;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    @Override
    public void onBindData(BrickViewHolder brickViewHolder) {
        SelectableTextSubTextIconBrickHolder holder = (SelectableTextSubTextIconBrickHolder) brickViewHolder;
        holder.title.setText(title);
        holder.subTitle.setText(subTitle);
        holder.icon.setImageDrawable(holder.icon.getContext().getDrawable(iconId));
        holder.layout.setOnClickListener(onTouch);

    }

    @Override
    public int getLayout() {
        return R.layout.brick_selectable_icon_title_subtitle;
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
        private final TextView subTitle;
        private final ImageView icon;


        SelectableTextSubTextIconBrickHolder(View itemView) {
            super(itemView);
            this.layout = itemView.findViewById(R.id.layout);
            this.title = itemView.findViewById(R.id.title);
            this.subTitle = itemView.findViewById(R.id.subtitle);
            this.icon = itemView.findViewById(R.id.icon);
        }
    }
}
