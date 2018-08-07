package com.pubbix.util.brick.common;

import android.support.design.widget.TextInputLayout;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.pubbix.R;
import com.pubbix.feature.common.Enums.EditTextType;
import com.pubbix.util.Tools;
import com.wayfair.brickkit.BrickViewHolder;
import com.wayfair.brickkit.brick.BaseBrick;

public class CustomEditTextBrick extends BaseBrick {
    private EditTextType type;
    private String text;
    private String hint;
    private int counterMaxLength;

    public CustomEditTextBrick(EditTextType type, String text,
                               String hint, int counterMaxLength) {
        this.type = type;
        this.text = text;
        this.hint = hint;
        this.counterMaxLength = counterMaxLength;
    }

    @Override
    public void onBindData(BrickViewHolder brickViewHolder) {
        CustomEditTextHolder holder = (CustomEditTextHolder) brickViewHolder;
        if (Tools.isNullOrBlank(text)) {
            holder.text.setText(text);
        } else {
            holder.text.setHint(hint);
        }
        holder.textInputLayout.setCounterEnabled(true);
        if (counterMaxLength > 20) {
            holder.textInputLayout.setCounterMaxLength(counterMaxLength);
        }

        setEditTextInputType(holder);
    }

    private void setEditTextInputType(CustomEditTextHolder holder) {
        switch (type) {
            case NAME:
                holder.text.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case EMAIL:
                holder.text.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;
            case BIOGRAPHY:
                holder.text.setSingleLine(false);
                holder.text.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
                holder.text.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                break;
            case IDENTITY:
                holder.text.setSingleLine(false);
                holder.text.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
                holder.text.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                break;
        }
    }

    @Override
    public int getLayout() {
        return R.layout.brick_generic_edit_text;
    }

    @Override
    public BrickViewHolder createViewHolder(View view) {
        return new CustomEditTextHolder(view);
    }

    private static class CustomEditTextHolder extends BrickViewHolder {
        private final EditText text;
        private final TextInputLayout textInputLayout;

        CustomEditTextHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.field);
            textInputLayout = itemView.findViewById(R.id.inputLayout);
        }
    }
}
