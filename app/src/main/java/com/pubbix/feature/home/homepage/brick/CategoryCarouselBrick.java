package com.pubbix.feature.home.homepage.brick;

import android.support.v7.widget.OrientationHelper;

import com.pubbix.feature.home.homepage.datamodel.MainCategoryDataModel;
import com.pubbix.feature.home.homepage.viewmodel.MainCategoryViewModel;
import com.pubbix.util.brick.common.CarouselBrick;
import com.wayfair.brickkit.BrickViewHolder;
import com.wayfair.brickkit.padding.SimpleBrickPadding;

import java.util.ArrayList;
import java.util.List;

public class CategoryCarouselBrick extends CarouselBrick {


    public CategoryCarouselBrick() {
        super(new SimpleBrickPadding(8), OrientationHelper.HORIZONTAL);
    }

    @Override
    public void onBindData(BrickViewHolder holder) {
        super.onBindData(holder);
        for (MainCategoryViewModel mainCategoryViewModel : getTestData()) {
            dataManager.addLast(new MainCategoryBrick(mainCategoryViewModel));
        }
    }

    private List<MainCategoryViewModel> getTestData() {
        setHidden(false);
        List<MainCategoryViewModel> mainCategoryViewModels = new ArrayList<>();

        mainCategoryViewModels.add(new MainCategoryViewModel(
                new MainCategoryDataModel("Discover", "https://cdn.luxedb.com/wp-content/uploads/2012/04/Discover-the-Fascinating-Thanda-Resort-in-South-Africa-8.jpg")));

        mainCategoryViewModels.add(new MainCategoryViewModel(
                new MainCategoryDataModel("Property", "http://oms.entegral.net/uploads/o97/Zimbali%20priced%20at%20R32m.jpg")));

        mainCategoryViewModels.add(new MainCategoryViewModel(
                new MainCategoryDataModel("Service", "http://www.secureitsource.com/wp-content/uploads/2015/10/Services-08-1438x628.jpg")));

        mainCategoryViewModels.add(new MainCategoryViewModel(
                new MainCategoryDataModel("For Sales", "https://www.gumtree.co.za/pages/images/autos/G61.jpg")));

        return mainCategoryViewModels;
    }
}
