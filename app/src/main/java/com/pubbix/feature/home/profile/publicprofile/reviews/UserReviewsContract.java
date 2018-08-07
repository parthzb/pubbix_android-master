package com.pubbix.feature.home.profile.publicprofile.reviews;

import android.content.Context;

import com.pubbix.base.BaseRouter;
import com.pubbix.base.BaseView;

public interface UserReviewsContract {
    interface View extends BaseView {
    }

    interface Router extends BaseRouter {
        void setContext(Context context);
    }

    interface Presenter {
    }

    interface Interactor {
        void setPresenter(Presenter presenter);
    }

    interface Repository{
        void setInteractor(Interactor interactor);
    }
}
