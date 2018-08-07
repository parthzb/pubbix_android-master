package com.pubbix.feature.home.homepage.datamodel;

import com.wayfair.brickkit.brick.DataModel;

public class MainCategoryDataModel extends DataModel {

    private final String categoryName;
    private final String imageUrl;

    public MainCategoryDataModel(String categoryName, String imageUrl) {
        this.categoryName = categoryName;
        this.imageUrl = imageUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
