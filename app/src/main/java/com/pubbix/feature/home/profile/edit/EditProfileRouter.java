package com.pubbix.feature.home.profile.edit;

import android.content.Context;

import com.pubbix.di.ScreenScope;
import com.pubbix.feature.common.Enums;
import com.pubbix.feature.home.profile.edit.generic.GenericProfileEditFragment;
import com.pubbix.ui.DefaultScreenNavigator;

import javax.inject.Inject;

@ScreenScope
public class EditProfileRouter implements EditProfileContract.Router {
    private DefaultScreenNavigator navigator;
    private EditProfileContract.Presenter presenter;
    private Context context;

    @Inject
    public EditProfileRouter(DefaultScreenNavigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void goToGenericProfileEdit(Enums.EditTextType type, String initialValue) {
        navigator.changeScreen(GenericProfileEditFragment.newInstance(type, initialValue));
    }

    @Override
    public void goBack() {
        navigator.pop();
    }

    @Override
    public void setPresenter(EditProfileContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
