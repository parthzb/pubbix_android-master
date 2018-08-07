package com.pubbix.feature.home.profile.edit;

import android.content.Context;
import android.content.Intent;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.pubbix.R;
import com.pubbix.base.BasePresenter;
import com.pubbix.data.event.SnackbarEvent;
import com.pubbix.data.model.Address;
import com.pubbix.data.model.User;
import com.pubbix.di.ScreenScope;
import com.pubbix.feature.common.Enums;
import com.pubbix.feature.common.Enums.EditTextType;
import com.pubbix.feature.home.profile.datamodel.EditProfileDataModel;
import com.pubbix.feature.home.profile.edit.picker.DatePickerFragment;
import com.pubbix.feature.home.profile.viewmodel.EditProfileViewModel;
import com.pubbix.util.UserHelper;
import com.pubbix.util.rx.RxEventBus;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import timber.log.Timber;

import static com.pubbix.feature.home.profile.edit.EditProfileFragment.IMAGE_REQUEST;
import static com.pubbix.feature.home.profile.edit.EditProfileFragment.PLACES_PICKER_REQUEST;

@ScreenScope
public class EditProfilePresenter extends BasePresenter<EditProfileFragment, EditProfileContract.Interactor>
        implements EditProfileContract.Presenter {
    private EditProfileFragment view;
    private EditProfileContract.Router router;
    private EditProfileContract.Interactor interactor;
    private Context context;
    private UserHelper userHelper;
    private EditProfileViewModel viewModel;
    private RxEventBus eventBus;

    @Inject
    EditProfilePresenter(@NotNull EditProfileFragment profileFragment,
                         @NotNull EditProfileContract.Router router,
                         @NotNull EditProfileContract.Interactor interactor,
                         @NotNull UserHelper userHelper,
                         @NotNull RxEventBus eventBus) {
        view = profileFragment;
        context = view.getContext();
        this.router = router;
        this.router.setContext(context);
        this.router.setPresenter(this);
        this.interactor = interactor;
        this.interactor.setPresenter(this);
        this.userHelper = userHelper;
        this.eventBus = eventBus;
    }

    @Override
    public void fetchUser() {
        interactor.fetchUser(userHelper.getUser().getUserId());
    }

    @Override
    public void onProfilePictureUpdated(User user) {
        viewModel.updateProfilePicture(user.getProfileImage(), user.getProfilePictureLastModified());
        onUserAccountUpdated();
    }

    @Override
    public void onUserAccountUpdated() {
        eventBus.postEvent(new SnackbarEvent(context.getString(R.string.account_information_update_success), Enums.SnackBarMessageType.INFO));

    }

    @Override
    public void onLocationUpdated(User user) {
        viewModel.updateLocation(user.getAddress());
    }

    @Override
    public void onBirthdayUpdated(User user) {
        viewModel.updateBirthday(user.getDayOfBirth());
    }

    @Override
    public void onRetrieveUser(User user) {
        viewModel = new EditProfileViewModel(new EditProfileDataModel(user), this, context);
        view.addViewModel(viewModel);
    }

    @Override
    public void updateProfileImage(String currentValue) {
        eventBus.postEvent(new SnackbarEvent("Updating your profile picture...", Enums.SnackBarMessageType.INFO));
        interactor.updateProfileImage(userHelper.getUser().getUserId(), currentValue);
    }

    @Override
    public void updateLocation(Address address) {
        address.setUserId(userHelper.getUser().getUserId());
        interactor.updateLocation(address);
    }

    @Override
    public void updateBirthday(String currentValue) {
        interactor.updateBirthday(userHelper.getUser().getUserId(), currentValue);

    }

    @Override
    public void onProfileImageClicked() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        view.startActivityForResult(intent, IMAGE_REQUEST);
    }

    @Override
    public void onFullNameClicked(String currentValue) {
        router.goToGenericProfileEdit(EditTextType.NAME, currentValue);
    }

    @Override
    public void onEmailClicked(String currentValue) {
        router.goToGenericProfileEdit(EditTextType.EMAIL, currentValue);
    }

    @Override
    public void onPhoneNumber(String currentValue) {
        router.goToGenericProfileEdit(EditTextType.PHONE, currentValue);
    }

    @Override
    public void onLocationClicked() {
        if (view.getActivity() != null) {
            final PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
            final Intent intent;
            try {
                intent = builder.build(view.getActivity());
                view.startActivityForResult(intent, PLACES_PICKER_REQUEST);
            } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                Timber.e(e);
            }
        }
    }

    @Override
    public void onBirthdayClicked() {
        if (view != null && view.getFragmentManager() != null) {
            DatePickerFragment picker = new DatePickerFragment();
            picker.setPresenter(this);
            picker.show(view.getFragmentManager(), "datePicker");
        }
    }

    @Override
    public void onFoundationClicked(String currentValue) {
        router.goToGenericProfileEdit(EditTextType.IDENTITY, currentValue);
    }

    @Override
    public void onGenderClicked(String currentValue) {
        router.goToGenericProfileEdit(EditTextType.GENDER, currentValue);
    }

    @Override
    public void onBioDescriptionClicked(String currentValue) {
        router.goToGenericProfileEdit(EditTextType.BIOGRAPHY, currentValue);
    }

    @Override
    public void onBackButtonClicked() {
        router.goBack();
    }
}
