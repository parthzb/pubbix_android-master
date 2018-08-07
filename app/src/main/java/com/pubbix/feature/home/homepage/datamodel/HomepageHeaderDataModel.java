package com.pubbix.feature.home.homepage.datamodel;

import com.wayfair.brickkit.brick.DataModel;

public class HomepageHeaderDataModel extends DataModel {
    public String title;
    public String subTitle;

    public HomepageHeaderDataModel(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }
}
