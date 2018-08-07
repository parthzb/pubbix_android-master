package com.pubbix.feature.onboarding.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.accountkit.AccountKitLoginResult;
import com.pubbix.R;
import com.pubbix.base.BaseFragment;
import com.pubbix.databinding.FragmentLoginBinding;
import com.pubbix.feature.onboarding.login.viewmodel.LoginViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static com.pubbix.feature.onboarding.login.LoginPresenter.APP_REQUEST_CODE;

public class LoginFragment extends BaseFragment<LoginPresenter> implements LoginContract.View{

    public static final String TAG = LoginFragment.class.getSimpleName();
    private FragmentLoginBinding mBinding;
    private CallbackManager mFacebookCallbackManager = CallbackManager.Factory.create();

    public static LoginFragment newInstance() {
        Bundle bundle = new Bundle();
        bundle.putString("instance_id", UUID.randomUUID().toString());
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // inflate layout, bind fields and etc
        super.onCreateView(inflater, container, savedInstanceState);
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_login,
                container,
                false);
        mBinding.setViewModel(new LoginViewModel(presenter, getResources()));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        presenter.setFacebookConnect(mFacebookCallbackManager);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == APP_REQUEST_CODE) { // confirm that this response matches your request
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            String toastMessage;
            if (loginResult.getError() != null) {
                toastMessage = loginResult.getError().getErrorType().getMessage();
            } else if (loginResult.wasCancelled()) {
                toastMessage = "Login Cancelled";
            } else {
                if (loginResult.getAccessToken() != null) {
                    toastMessage = "Success:" + loginResult.getAccessToken().getAccountId();
                } else {
                    toastMessage = String.format(
                            "Success:%s...",
                            loginResult.getAuthorizationCode().substring(0,10));
                }
                //Handle Returning User
                presenter.handleReturningUser();

            }

            // Surface the result to your user in an appropriate way.
            showMessage(toastMessage);
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void setLoginByFacebookButtonText(String text) {
        mBinding.btnFbLogin.setText(text);
    }

    @Override
    public void setLoginByPhoneButtonText(String text) {
        mBinding.btnLoginViaPhone.setText(text);
    }

    @Override
    public void setLoginByEmailButtonText(String text) {
        mBinding.btnLoginViaEmail.setText(text);
    }

    @Override
    protected boolean shouldShowBottomNavigation() {
        return false;
    }
}
