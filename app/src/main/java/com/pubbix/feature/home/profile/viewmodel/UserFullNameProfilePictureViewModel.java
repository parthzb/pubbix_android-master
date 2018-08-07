package com.pubbix.feature.home.profile.viewmodel;

import android.content.res.Resources;
import android.databinding.Bindable;
import android.support.annotation.Nullable;
import android.view.View;

import com.pubbix.R;
import com.pubbix.feature.home.profile.ProfileContract;
import com.pubbix.feature.home.profile.datamodel.UserFullNameProfilePictureDataModel;
import com.pubbix.util.UserHelper;
import com.wayfair.brickkit.brick.ViewModel;

public class UserFullNameProfilePictureViewModel extends ViewModel<UserFullNameProfilePictureDataModel> {

    private ProfileContract.Presenter presenter;
    private Resources resources;
    private UserHelper userHelper;

    public UserFullNameProfilePictureViewModel(@Nullable UserFullNameProfilePictureDataModel dataModel,
                                               ProfileContract.Presenter presenter,
                                               Resources resources,
                                               UserHelper userHelper) {
        super(dataModel);
        this.presenter = presenter;
        this.resources = resources;
        this.userHelper = userHelper;
    }

    @Bindable
    public String getFullName() {
        return userHelper.isLoggedIn() ? dataModel.getUserFullName()
                : resources.getString(R.string.user_default_full_name);
    }

    @Bindable
    public String getProfilePicture() {
        return dataModel.getProfilePicture();
    }

    @Bindable
    public String getInvalidationKey() {
        return dataModel.getProfilePictureLastModified() != null ? dataModel.getProfilePictureLastModified() :
                "";
    }

    @Bindable
    public String getViewEditProfileText() {
        return userHelper.isLoggedIn() ? resources.getString(R.string.view_and_edit_profile) :
                resources.getString(R.string.sign_in_to_unlock_features);
    }

    @Bindable
    public View.OnClickListener getViewEditProfileClicked() {
        return view -> presenter.onViewEditProfileClicked();
    }
}
