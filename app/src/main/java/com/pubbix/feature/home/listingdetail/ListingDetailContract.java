package com.pubbix.feature.home.listingdetail;

import android.content.Context;

import com.pubbix.base.BaseRouter;
import com.pubbix.base.BaseView;

public interface ListingDetailContract {
    interface View extends BaseView {
    }

    interface Router extends BaseRouter {
        void goBack();
        void setContext(Context context);
    }

    interface Presenter {
        void onBackPressed();
    }

    interface Interactor {
        void setPresenter(Presenter presenter);
    }

    interface Repository{
        void setInteractor(Interactor interactor);
    }
}
