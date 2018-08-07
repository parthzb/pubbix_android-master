package com.pubbix.feature.home.homepage.brick;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pubbix.R;
import com.pubbix.feature.home.homepage.viewmodel.MainCategoryViewModel;
import com.wayfair.brickkit.BrickViewHolder;
import com.wayfair.brickkit.brick.BaseBrick;

public class MainCategoryBrick extends BaseBrick {

    private MainCategoryViewModel viewModel;

    public MainCategoryBrick(MainCategoryViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void onBindData(BrickViewHolder brickViewHolder) {
        MainCategoryBrickViewHolder holder = (MainCategoryBrickViewHolder) brickViewHolder;
        holder.categoryName.setText(viewModel.getCategoryName());
        setImage(holder.categoryImage, viewModel.getImageUrl());

    }

    @Override
    public int getLayout() {
        return R.layout.brick_main_category;
    }

    @Override
    public BrickViewHolder createViewHolder(View view) {
        return new MainCategoryBrickViewHolder(view);
    }

    private void setImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(imageUrl)
                .thumbnail(0.5f)
                .into(imageView);
    }

    protected static class MainCategoryBrickViewHolder extends BrickViewHolder {
        private final ImageView categoryImage;
        private final TextView categoryName;

        MainCategoryBrickViewHolder(View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.category_name);
            categoryImage = itemView.findViewById(R.id.category_image);
        }
    }
}
