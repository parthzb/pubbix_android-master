package com.pubbix.feature.home.profile.viewmodel;

import android.content.Context;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.signature.ObjectKey;
import com.pubbix.feature.home.profile.datamodel.EditProfileDataModel;
import com.pubbix.feature.home.profile.edit.EditProfileContract;
import com.wayfair.brickkit.brick.ViewModel;

import org.jetbrains.annotations.NotNull;

public class EditProfileViewModel extends ViewModel<EditProfileDataModel> {
    public final ObservableField<Drawable> profileImage = new ObservableField<>();
    public final ObservableField<String> fullName = new ObservableField<>();
    public final ObservableField<String> email = new ObservableField<>();
    public final ObservableField<String> phone = new ObservableField<>();
    public final ObservableField<String> gender = new ObservableField<>();
    public final ObservableField<String> location = new ObservableField<>();
    public final ObservableField<String> birthday = new ObservableField<>();
    public final ObservableField<String> biography = new ObservableField<>();
    private final BaseTarget<Drawable> bindableFieldTarget = new BaseTarget<Drawable>() {
        @Override
        public void onResourceReady(@NonNull Drawable resource,
                                    @Nullable Transition<? super Drawable> transition) {
            profileImage.set(resource);
        }

        @Override
        public void getSize(@NonNull SizeReadyCallback cb) {
            cb.onSizeReady(SIZE_ORIGINAL, SIZE_ORIGINAL);
        }

        @Override
        public void removeCallback(@NonNull SizeReadyCallback cb) {

        }
    };

    private EditProfileContract.Presenter presenter;
    private Context context;


    public EditProfileViewModel(@NotNull EditProfileDataModel dataModel,
                                @NotNull EditProfileContract.Presenter presenter,
                                @NotNull Context context) {
        super(dataModel);

        this.presenter = presenter;
        this.context = context;
        fullName.set(dataModel.fullName);
        email.set(dataModel.email);
        phone.set(dataModel.phoneNumber);
        gender.set(getGenderText(dataModel.gender));
        location.set(dataModel.address);
        birthday.set(dataModel.dayOfBirth);
        biography.set(dataModel.biography);
        updateProfilePicture(dataModel.profilePicture, dataModel.profilePictureLastModified);
    }

    public void updateProfilePicture(String profileImageUrl, String key) {
        Glide.with(context)
                .load(profileImageUrl)
                .apply(RequestOptions.signatureOf(new ObjectKey(key)))
                .into(bindableFieldTarget);
    }

    public void updateLocation(String updatedLocation) {
        location.set(updatedLocation);
    }

    public void updateBirthday(String updatedBirthday) {
        birthday.set(updatedBirthday);
    }

    @Bindable
    public View.OnClickListener getProfileImageClicked() {
        return view -> presenter.onProfileImageClicked();
    }

    @Bindable
    public View.OnClickListener getFullNameClicked() {
        return view -> presenter.onFullNameClicked(dataModel.fullName);
    }

    @Bindable
    public View.OnClickListener getEmailClicked() {
        return view -> presenter.onEmailClicked(dataModel.email);
    }

    @Bindable
    public View.OnClickListener getPhoneNumbeClicked() {
        return view -> presenter.onPhoneNumber(dataModel.phoneNumber);
    }

    @Bindable
    public View.OnClickListener getLocationClicked() {
        return view -> presenter.onLocationClicked();
    }

    @Bindable
    public View.OnClickListener getBirthdayClicked() {
        return view -> presenter.onBirthdayClicked();
    }

    @Bindable
    public View.OnClickListener getGenderClicked() {
        return view -> presenter.onGenderClicked(getGenderText(dataModel.gender));
    }

    @Bindable
    public View.OnClickListener getBioDescriptionClicked() {
        return view -> presenter.onBioDescriptionClicked(dataModel.biography);
    }

    private String getGenderText(int gender) {
        String genderText = "";
        if (gender == 0) {
            genderText = "Man";
        } else if (gender == 1) {
            genderText = "Woman";
        }
        this.gender.set(genderText);
        return genderText;
    }
}
