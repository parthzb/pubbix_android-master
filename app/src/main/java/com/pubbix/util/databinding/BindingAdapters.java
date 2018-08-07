package com.pubbix.util.databinding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.pubbix.R;

public class BindingAdapters {
    @BindingAdapter({"app:imageUrl","app:invalidationKey"})
    public static void loadImage(ImageView imageView, String url, String invalidationKey) {
        if (url == null) {
            imageView.setImageDrawable(imageView.getContext().getDrawable(R.mipmap.profile));
        } else {
            Glide.with(imageView.getContext())
                    .load(url)
                    .apply(RequestOptions.signatureOf(new ObjectKey(invalidationKey)))
                    .into(imageView);
        }
    }
}
