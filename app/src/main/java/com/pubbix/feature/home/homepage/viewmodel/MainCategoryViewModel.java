package com.pubbix.feature.home.homepage.viewmodel;

import android.support.annotation.Nullable;

import com.pubbix.feature.home.homepage.datamodel.MainCategoryDataModel;
import com.wayfair.brickkit.brick.ViewModel;

public class MainCategoryViewModel extends ViewModel<MainCategoryDataModel> {
    public MainCategoryViewModel(@Nullable MainCategoryDataModel dataModel) {
        super(dataModel);
    }

    public String getCategoryName() {
        return dataModel.getCategoryName();
    }

    public String getImageUrl() {
        return dataModel.getImageUrl();
    }

}
