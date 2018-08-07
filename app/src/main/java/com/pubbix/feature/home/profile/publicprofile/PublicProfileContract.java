package com.pubbix.feature.home.profile.publicprofile;

import android.content.Context;

import com.pubbix.base.BaseRouter;
import com.pubbix.base.BaseView;
import com.pubbix.data.model.User;
import com.pubbix.feature.home.profile.viewmodel.PublicProfileViewModel;

public interface PublicProfileContract {
    interface View extends BaseView {
        void setToolbarViews(User user);
        void setPublicProfileViewModel(PublicProfileViewModel publicProfileViewModel);
    }

    interface Router extends BaseRouter {
        void goToEditPage();
        void goBack();
        void setContext(Context context);
    }

    interface Presenter {
        void onEditButtonClicked();
        void onBackButtonClicked();
        void setUpViews();
    }

    interface Interactor {
        void setPresenter(Presenter presenter);
    }

    interface Repository{
        void setInteractor(Interactor interactor);
    }
}
