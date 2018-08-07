package com.pubbix.util.brick.common;

import android.support.design.button.MaterialButton;
import android.view.View;

import com.pubbix.R;
import com.wayfair.brickkit.BrickViewHolder;
import com.wayfair.brickkit.brick.BaseBrick;

public class CustomSaveButtonBrick extends BaseBrick {
    private String buttonText;
    private View.OnClickListener onTouch;

    public CustomSaveButtonBrick(String buttonText, View.OnClickListener onTouch) {
        this.buttonText = buttonText;
        this.onTouch = onTouch;
    }

    @Override
    public void onBindData(BrickViewHolder brickViewHolder) {
        ToolbarBrickHolder holder = (ToolbarBrickHolder) brickViewHolder;
        holder.button.setText(buttonText);
        holder.button.setOnClickListener(onTouch);
    }

    @Override
    public int getLayout() {
        return R.layout.brick_generic_save_button;
    }

    @Override
    public BrickViewHolder createViewHolder(View view) {
        return new ToolbarBrickHolder(view);
    }

    private static class ToolbarBrickHolder extends BrickViewHolder {
        private final MaterialButton button;

        ToolbarBrickHolder(View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.button);
        }
    }
}
