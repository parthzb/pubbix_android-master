package com.pubbix.feature.common.viewmodel;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.pubbix.R;
import com.pubbix.feature.common.datamodel.ListingDataModel;
import com.pubbix.util.ColorHelper;
import com.wayfair.brickkit.brick.ViewModel;

public class ListingViewModel extends ViewModel<ListingDataModel> {
    private Resources resources;
    private Context context;
    private Interactions interactions;
    private int colorIndex;

    public ListingViewModel(@Nullable ListingDataModel dataModel, int colorIndex,
                            Resources resources, Context context, Interactions interactions) {
        super(dataModel);
        this.colorIndex = colorIndex;
        this.resources = resources;
        this.context = context;
        this.interactions = interactions;
    }

    @Bindable
    public String getInitialImage() {
        return dataModel.images.get(0);
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        if (url == null) {
            imageView.setImageDrawable(imageView.getContext().getDrawable(R.mipmap.profile));
        } else {
            Glide.with(imageView.getContext()).load(url).into(imageView);
        }
    }

    @Bindable
    public String getCategoryName() {
        return dataModel.categoryName;
    }

    @Bindable
    public String getTitle() {
        return dataModel.title;
    }

    @Bindable
    public String getPrice() {
        return convertPrice(dataModel.price, dataModel.currency);
    }

    @Bindable
    public int getCategoryLabelColor() {
        return ContextCompat.getColor(context, ColorHelper.getRandomCategoryNameColor(colorIndex));
    }

    @Bindable
    public String getMiddleLabel() {
        return resources.getString(R.string.sold);
    }


    @Bindable
    public int getMiddleLabelVisibility() {
        return dataModel.status.equalsIgnoreCase("sold") ? View.VISIBLE : View.GONE;
    }

    @Bindable
    public View.OnClickListener getOnClickListener(){
        return view -> interactions.onListingClicked(dataModel);
    }


    private String convertPrice(Integer price, String currency) {
        return price + " " + currency;
    }

    public interface Interactions {
        void onListingClicked(ListingDataModel listingDataModel);
    }
}
