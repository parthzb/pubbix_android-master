package com.pubbix.feature.home.profile.edit.generic;

import android.content.Context;

import com.pubbix.base.BaseRouter;
import com.pubbix.base.BaseView;
import com.pubbix.data.model.Profile;
import com.pubbix.feature.common.Enums.EditTextType;
import com.pubbix.feature.home.profile.viewmodel.GenericEditViewModel;

public interface GenericProfileEditContract {
    interface View extends BaseView {
        void addEditTextBrick(GenericEditViewModel viewModel);

        void addGenderRadioGroupBrick(GenericEditViewModel viewModel);
    }

    interface Router extends BaseRouter {
        void setContext(Context context);

        void goBack();
    }

    interface Presenter {
        void setUpViews(EditTextType type, String initialValue);

        void onUserAccountUpdated();

        void onBackButtonClicked();
    }

    interface Interactor extends GenericEditViewModel.Interactions {
        void onUpdateUserSuccess(final Profile profile);
        void onUpdateUserFailure(Throwable throwable);
        void setPresenter(Presenter presenter);
    }

    interface Repository {
        void updateFullName(String updatedValue);

        void updateEmail(String updatedValue);

        void updatePhone(String updatedValue);

        void updateFoundation(String updatedValue);

        void updateGender(int gender);

        void updateBioDescription(String updatedValue);

        void setInteractor(Interactor interactor);
    }
}
