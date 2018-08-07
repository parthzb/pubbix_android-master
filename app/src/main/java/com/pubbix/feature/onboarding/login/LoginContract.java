package com.pubbix.feature.onboarding.login;

import com.facebook.CallbackManager;
import com.pubbix.base.BaseRouter;
import com.pubbix.base.BaseView;
import com.pubbix.data.model.Profile;
import com.pubbix.data.model.User;

public interface LoginContract {
    interface View extends BaseView {
        void setLoginByFacebookButtonText(String text);

        void setLoginByPhoneButtonText(String text);

        void setLoginByEmailButtonText(String text);
    }

    interface Router extends BaseRouter {
        void goToHomepage(LoginFragment loginFragment);

        void goToRegistration();
    }

    interface Presenter {
        void onFacebookButtonClicked();

        void onAccountKitLoginByPhoneClicked();

        void onAccountKitLoginByEmailClicked();

        void onRegisterButtonClicked();

        void onHomepageButtonClicked();

        void setFacebookConnect(CallbackManager callbackManager);

        void handleNewUser();

        void handleReturningUser();
    }

    interface Interactor {
        void isReturningUser(final LoginPresenter.AuthenticationType authenticationType, final String identifier);

        void onRetrieveUserByFacebookIdSuccess(final Profile profile);

        void onRetrieveUserByPhoneSuccess(final Profile profile);

        void onRetrieveUserByEmailSuccess(final Profile profile);

        void onRetrieveUserFailure(Throwable throwable);

        void setPresenter(Presenter presenter);
    }

    interface Repository {
        void retrieveUserByFacebookId(final String facebookId);

        void retrieveUserByPhone(final String phone);

        void retrieveUserByEmail(final String email);

        void setUserLoginStatus(final User user);

        void setInteractor(Interactor interactor);
    }

}
