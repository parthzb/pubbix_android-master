package com.pubbix.util.brick.common;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.pubbix.R;
import com.wayfair.brickkit.BrickViewHolder;
import com.wayfair.brickkit.brick.BaseBrick;

public class CustomToolbarBrick extends BaseBrick {
    private String title;
    private View.OnClickListener onTouch;

    public CustomToolbarBrick(String title, View.OnClickListener onTouch) {
        this.title = title;
        this.onTouch = onTouch;
    }

    @Override
    public void onBindData(BrickViewHolder brickViewHolder) {
        ToolbarBrickHolder holder = (ToolbarBrickHolder) brickViewHolder;
        holder.toolbarTitle.setText(title);
        holder.backButton.setOnClickListener(onTouch);
    }

    @Override
    public int getLayout() {
        return R.layout.brick_toolbar;
    }

    @Override
    public BrickViewHolder createViewHolder(View view) {
        return new ToolbarBrickHolder(view);
    }

    private static class ToolbarBrickHolder extends BrickViewHolder {
        private final TextView toolbarTitle;
        private final ImageButton backButton;

        ToolbarBrickHolder(View itemView) {
            super(itemView);
            toolbarTitle = itemView.findViewById(R.id.toolbar_title);
            backButton = itemView.findViewById(R.id.back_button);
        }
    }
}
