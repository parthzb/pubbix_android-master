package com.pubbix.feature.home.profile.edit.generic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pubbix.BR;
import com.pubbix.R;
import com.pubbix.base.BaseFragment;
import com.pubbix.feature.common.Enums;
import com.pubbix.feature.home.profile.viewmodel.GenericEditViewModel;
import com.pubbix.util.brick.FullWidthBrickSize;
import com.wayfair.brickkit.brick.ViewModelBrick;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class GenericProfileEditFragment extends BaseFragment<GenericProfileEditPresenter>
        implements GenericProfileEditContract.View {

    public static final String TAG = GenericProfileEditFragment.class.getSimpleName();
    private Enums.EditTextType type;
    public static final String TYPE = "type";
    public static final String INITIAL_VALUE = "value";

    public static Fragment newInstance(Enums.EditTextType type, String initialValue) {
        Bundle bundle = new Bundle();
        bundle.putString("instance_id", UUID.randomUUID().toString());
        bundle.putSerializable(INITIAL_VALUE, initialValue);
        bundle.putSerializable(TYPE, type);
        Fragment fragment = new GenericProfileEditFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_generic_edit_profile, container, false);
        dataManager.setRecyclerView(getContext(), view.findViewById(R.id.recycler_view),
                OrientationHelper.VERTICAL, false, view);
        dataManager.getRecyclerView().setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));

        Bundle bundle = getArguments();
        if (bundle != null) {
            String initialValue = bundle.getString(INITIAL_VALUE);
            type = (Enums.EditTextType) bundle.getSerializable(TYPE);
            presenter.setUpViews(type, initialValue);
        }

        setToolbar(view, type);

        return view;
    }

    private void setToolbar(View view, Enums.EditTextType type) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(getToolbarTitle(type));
        toolbar.setNavigationOnClickListener(v -> presenter.onBackButtonClicked());
    }

    @Override
    public void addEditTextBrick(GenericEditViewModel viewModel) {
        ViewModelBrick viewModelBrick = new ViewModelBrick.Builder(R.layout.brick_generic_edit_text)
                .setSpanSize(new FullWidthBrickSize())
                .addViewModel(BR.viewModel, viewModel)
                .build();
        dataManager.addLast(viewModelBrick);
    }

    @Override
    public void addGenderRadioGroupBrick(GenericEditViewModel viewModel) {
        ViewModelBrick viewModelBrick = new ViewModelBrick.Builder(R.layout.brick_generic_gender)
                .setSpanSize(new FullWidthBrickSize())
                .addViewModel(BR.viewModel, viewModel)
                .build();
        dataManager.addLast(viewModelBrick);
    }

    private String getToolbarTitle(Enums.EditTextType type) {
        String title = "Edit";
        switch (type) {
            case NAME:
                title = "Edit Your Full Name";
                break;
            case EMAIL:
                title = "Edit Your Email";
                break;
            case PHONE:
                title = "Edit Your Phone Number";
                break;
            case BIOGRAPHY:
                title = "Edit Your Biography";
                break;
            case IDENTITY:
                title = "Edit Identity";
                break;
            case GENDER:
                title = "Edit Gender";
        }
        return title;
    }

    @Override
    public void showMessage(@NotNull String message) {

    }

    @Override
    protected boolean shouldShowBottomNavigation() {
        return false;
    }
}
