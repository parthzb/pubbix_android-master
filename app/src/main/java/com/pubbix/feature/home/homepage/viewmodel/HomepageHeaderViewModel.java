package com.pubbix.feature.home.homepage.viewmodel;

import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.view.View;

import com.pubbix.feature.home.homepage.datamodel.HomepageHeaderDataModel;
import com.pubbix.util.Tools;
import com.wayfair.brickkit.brick.ViewModel;

import org.jetbrains.annotations.NotNull;

public class HomepageHeaderViewModel extends ViewModel<HomepageHeaderDataModel> {
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> subTitle = new ObservableField<>();

    public HomepageHeaderViewModel(@NotNull HomepageHeaderDataModel dataModel) {
        super(dataModel);
        title.set(dataModel.title);
        subTitle.set(dataModel.subTitle);
    }

    @Bindable
    public int getSubTitleVisibility() {
        return Tools.isNullOrBlank(dataModel.subTitle) ? View.GONE : View.VISIBLE;
    }
}
