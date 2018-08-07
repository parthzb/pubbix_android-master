package com.pubbix.feature.home.inbox;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pubbix.R;
import com.pubbix.base.BaseFragment;
import com.pubbix.databinding.FragmentInboxBinding;

import java.util.UUID;

public class InboxFragment extends BaseFragment<InboxPresenter>{

    private FragmentInboxBinding binding;

    public static Fragment newInstance() {
        Bundle bundle = new Bundle();
        bundle.putString("instance_id", UUID.randomUUID().toString());
        Fragment fragment = new InboxFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_inbox,
                container,
                false);
        return binding.getRoot();
    }

    @Override
    protected boolean shouldShowBottomNavigation() {
        return true;
    }
}
