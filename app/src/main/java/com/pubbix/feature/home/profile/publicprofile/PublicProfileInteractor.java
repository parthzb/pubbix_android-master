package com.pubbix.feature.home.profile.publicprofile;

import com.pubbix.base.BaseInteractor;
import com.pubbix.di.ScreenScope;

import javax.inject.Inject;

@ScreenScope
public class PublicProfileInteractor extends BaseInteractor<PublicProfileContract.Repository>
implements PublicProfileContract.Interactor{
    private PublicProfileContract.Presenter presenter;
    private PublicProfileContract.Repository repository;

    @Inject
    PublicProfileInteractor(PublicProfileContract.Repository repository) {
        this.repository = repository;
        this.repository.setInteractor(this);
    }

    @Override
    public void setPresenter(PublicProfileContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
