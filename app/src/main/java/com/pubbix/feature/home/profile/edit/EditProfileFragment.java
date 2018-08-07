package com.pubbix.feature.home.profile.edit;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.pubbix.R;
import com.pubbix.base.BaseFragment;
import com.pubbix.data.model.Address;
import com.pubbix.databinding.FragmentEditProfileBinding;
import com.pubbix.feature.home.profile.viewmodel.EditProfileViewModel;
import com.pubbix.util.Tools;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.UUID;

import timber.log.Timber;

import static android.app.Activity.RESULT_OK;

public class EditProfileFragment extends BaseFragment<EditProfilePresenter>
        implements EditProfileContract.View {

    public static final String TAG = EditProfileFragment.class.getSimpleName();
    static final int PLACES_PICKER_REQUEST = 2;
    static final int IMAGE_REQUEST = 3;
    private FragmentEditProfileBinding binding;

    public static Fragment newInstance() {
        Bundle bundle = new Bundle();
        bundle.putString("instance_id", UUID.randomUUID().toString());
        Fragment fragment = new EditProfileFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_edit_profile,
                container,
                false);
        setToolbar(binding.getRoot());
        return binding.getRoot();
    }

    private void setToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("Edit Your Profile");
        toolbar.setNavigationOnClickListener(v -> presenter.onBackButtonClicked());
    }

    @Override
    public void onStart() {
        super.onStart();
        dataManager.clear();
        binding.shimmerViewContainer.startShimmer();
        presenter.fetchUser();
    }

    @Override
    public void showMessage(@NotNull String message) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACES_PICKER_REQUEST && resultCode == RESULT_OK && data != null) {
            Place place = PlacePicker.getPlace(getActivity(), data);
            //final String address = String.format("%s", location.getAddress());
            final Address address = Tools.getAddress(new Geocoder(getContext()), place);
            presenter.updateLocation(address);
        } else if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            try {
                presenter.updateProfileImage(Tools.imageToString(
                        MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), path)));
            } catch (IOException e) {
                Timber.e(e);
            }
        }
    }

    @Override
    public void addViewModel(EditProfileViewModel editProfileViewModel) {
        binding.shimmerViewContainer.stopShimmer();
        binding.shimmerViewContainer.setVisibility(View.GONE);
        binding.setViewModel(editProfileViewModel);

    }

    @Override
    protected boolean shouldShowBottomNavigation() {
        return false;
    }
}
