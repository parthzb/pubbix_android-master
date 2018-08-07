package com.pubbix.util.brick.common;

import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.TextView;

import com.pubbix.R;
import com.wayfair.brickkit.BrickViewHolder;
import com.wayfair.brickkit.brick.BaseBrick;
import com.wayfair.brickkit.brick.TouchableBrick;
import com.wayfair.brickkit.padding.BrickPadding;
import com.wayfair.brickkit.size.BrickSize;

public class SelectableTextIconBrick extends BaseBrick implements TouchableBrick {
    private CharSequence text;
    @DrawableRes
    private int iconId;
    private View.OnClickListener onTouch;
    private IconPosition position;

    public enum IconPosition {
        RIGHT,
        LEFT
    }

    public SelectableTextIconBrick(CharSequence text, int iconId, View.OnClickListener onTouch) {
        this.text = text;
        this.iconId = iconId;
        this.onTouch = onTouch;
    }

    public SelectableTextIconBrick(CharSequence text, int iconId, IconPosition position, View.OnClickListener onTouch) {
        this.text = text;
        this.iconId = iconId;
        this.onTouch = onTouch;
        this.position = position;
    }

    public SelectableTextIconBrick(BrickSize spanSize,
                                   BrickPadding padding,
                                   CharSequence text, int iconId, View.OnClickListener onTouch) {
        super(spanSize, padding);
        this.text = text;
        this.iconId = iconId;
        this.onTouch = onTouch;
    }

    public SelectableTextIconBrick(BrickSize spanSize, CharSequence text,
                                   int iconId, View.OnClickListener onTouch) {
        super(spanSize);
        this.text = text;
        this.iconId = iconId;
        this.onTouch = onTouch;
    }

    public SelectableTextIconBrick(BrickPadding padding, CharSequence text,
                                   int iconId, View.OnClickListener onTouch) {
        super(padding);
        this.text = text;
        this.iconId = iconId;
        this.onTouch = onTouch;
    }

    public void setText(CharSequence text) {
        this.text = text;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public void setOnTouch(View.OnClickListener onTouch) {
        this.onTouch = onTouch;
    }

    @Override
    public void onBindData(BrickViewHolder brickViewHolder) {
        SelectableTextIconBrickHolder holder = (SelectableTextIconBrickHolder) brickViewHolder;
        holder.textView.setText(text);

        if (position != null) {
            if (position == IconPosition.RIGHT) {
                holder.textView.setCompoundDrawablesRelativeWithIntrinsicBounds(null,
                        null,
                        holder.textView.getContext().getDrawable(iconId),
                        null);
            } else if (position == IconPosition.LEFT) {
                holder.textView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        holder.textView.getContext().getDrawable(iconId),
                        null,
                        null,
                        null);
            }
        } else {
            holder.textView.setCompoundDrawablesRelativeWithIntrinsicBounds(null,
                    null,
                    holder.textView.getContext().getDrawable(iconId),
                    null);
        }

        if (isEnabled()) {
            holder.itemView.setOnClickListener(onTouch());
        }
    }

    @Override
    public int getLayout() {
        return R.layout.brick_selectable_text_icon;
    }

    @Override
    public BrickViewHolder createViewHolder(View view) {
        return new SelectableTextIconBrickHolder(view);
    }

    @Override
    public View.OnClickListener onTouch() {
        return onTouch;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private static class SelectableTextIconBrickHolder extends BrickViewHolder {
        private final TextView textView;


        SelectableTextIconBrickHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.selectable_text_icon);
        }
    }
}
