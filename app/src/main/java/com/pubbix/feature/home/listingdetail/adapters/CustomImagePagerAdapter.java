package com.pubbix.feature.home.listingdetail.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.pubbix.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomImagePagerAdapter extends PagerAdapter {
    private Context context;
    private List<String> images;
    private LayoutInflater layoutInflater;


    public CustomImagePagerAdapter(Context context, List<String> images) {
        this.context = context;
        this.images = images;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NotNull View view, @NotNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.listing_detail_view_pager, container, false);

        ImageView imageView = itemView.findViewById(R.id.backdrop);
        if (!images.get(position).equals("")) {
            Glide.with(context)
                    .load(images.get(position).trim())
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC))
                    .apply(RequestOptions.overrideOf(350, 200))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);
        }
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
