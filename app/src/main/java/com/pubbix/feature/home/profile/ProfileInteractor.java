package com.pubbix.feature.home.profile;

import com.pubbix.base.BaseInteractor;
import com.pubbix.di.ScreenScope;

import javax.inject.Inject;

@ScreenScope
public class ProfileInteractor extends BaseInteractor<ProfileContract.Repository>
implements ProfileContract.Interactor{
    private ProfileContract.Presenter presenter;
    private ProfileContract.Repository repository;

    @Inject
    ProfileInteractor(ProfileContract.Repository repository) {
        this.repository = repository;
        this.repository.setInteractor(this);
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
