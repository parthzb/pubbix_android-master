package com.pubbix.util.brick.common;

import android.view.View;
import android.widget.TextView;

import com.pubbix.R;
import com.wayfair.brickkit.BrickViewHolder;
import com.wayfair.brickkit.brick.BaseBrick;
import com.wayfair.brickkit.padding.BrickPadding;
import com.wayfair.brickkit.size.BrickSize;

public class ParagraphBrick extends BaseBrick {
    private String paragraph;

    public ParagraphBrick(String paragraph) {
        this.paragraph = paragraph;
    }

    public ParagraphBrick(BrickSize spanSize, BrickPadding padding, String paragraph) {
        super(spanSize, padding);
        this.paragraph = paragraph;
    }

    public ParagraphBrick(BrickSize spanSize, String paragraph) {
        super(spanSize);
        this.paragraph = paragraph;
    }

    public ParagraphBrick(BrickPadding padding, String paragraph) {
        super(padding);
        this.paragraph = paragraph;
    }

    @Override
    public void onBindData(BrickViewHolder brickViewHolder) {
        ParagraphBrickHolder holder = (ParagraphBrickHolder) brickViewHolder;
        holder.textView.setText(paragraph);
    }

    @Override
    public int getLayout() {
        return R.layout.brick_paragraph_statement;
    }

    @Override
    public BrickViewHolder createViewHolder(View view) {
        return new ParagraphBrickHolder(view);
    }

    private static class ParagraphBrickHolder extends BrickViewHolder {
        private final TextView textView;

        ParagraphBrickHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.paragraph);
        }
    }
}
