package com.pubbix.feature.home.profile.edit.generic;

import android.content.Context;

import com.pubbix.R;
import com.pubbix.base.BasePresenter;
import com.pubbix.data.event.SnackbarEvent;
import com.pubbix.di.ScreenScope;
import com.pubbix.feature.common.Enums;
import com.pubbix.feature.common.Enums.EditTextType;
import com.pubbix.feature.home.profile.datamodel.GenericEditDataModel;
import com.pubbix.feature.home.profile.viewmodel.GenericEditViewModel;
import com.pubbix.util.UserHelper;
import com.pubbix.util.rx.RxEventBus;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

@ScreenScope
public class GenericProfileEditPresenter extends BasePresenter<GenericProfileEditFragment, GenericProfileEditContract.Interactor>
        implements GenericProfileEditContract.Presenter {
    private GenericProfileEditFragment view;
    private GenericProfileEditContract.Router router;
    private GenericProfileEditContract.Interactor interactor;
    private Context context;
    private UserHelper userHelper;
    private GenericEditViewModel viewModel;
    private RxEventBus eventBus;

    @Inject
    public GenericProfileEditPresenter(@NotNull GenericProfileEditFragment profileFragment,
                                       @NotNull GenericProfileEditContract.Router router,
                                       @NotNull GenericProfileEditContract.Interactor interactor,
                                       @NotNull UserHelper userHelper,
                                       @NotNull RxEventBus eventBus) {
        view = profileFragment;
        context = view.getContext();
        this.router = router;
        this.router.setContext(context);
        this.interactor = interactor;
        this.interactor.setPresenter(this);
        this.userHelper = userHelper;
        this.eventBus = eventBus;
    }

    @Override
    public void setUpViews(EditTextType type, String initialValue) {
        viewModel = new GenericEditViewModel(new GenericEditDataModel(initialValue), type, interactor);
        switch (type) {
            case NAME:
            case EMAIL:
            case PHONE:
            case BIOGRAPHY:
            case IDENTITY:
                view.addEditTextBrick(viewModel);
                break;
            case GENDER:
                view.addGenderRadioGroupBrick(viewModel);
                break;
        }
    }

    @Override
    public void onUserAccountUpdated() {
        eventBus.postEvent(new SnackbarEvent(context.getString(R.string.account_information_update_success), Enums.SnackBarMessageType.INFO));
        router.goBack();
    }

    @Override
    public void onBackButtonClicked() {
        router.goBack();
    }


}
