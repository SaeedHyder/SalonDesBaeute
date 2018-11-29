package com.app.salonbeaute.fragments;

import android.os.Bundle;
import android.text.Html;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.app.salonbeaute.R;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.helpers.UIHelper;
import com.app.salonbeaute.ui.views.AnyEditTextView;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.app.salonbeaute.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class LoginFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener {


    @BindView(R.id.txt_email)
    AnyEditTextView txtEmail;
    @BindView(R.id.txt_password)
    AnyEditTextView txtPassword;
    @BindView(R.id.checkbox_show_password)
    CheckBox checkboxShowPassword;
    @BindView(R.id.loginButton)
    Button loginButton;
    Unbinder unbinder;
    @BindView(R.id.txt_signup)
    AnyTextView txtSignup;
    @BindView(R.id.txt_forgot_password)
    AnyTextView txtForgotPassword;
    @BindView(R.id.ll_connect)
    LinearLayout llConnect;
    @BindView(R.id.btn_facebook)
    AnyTextView btnFacebook;
    @BindView(R.id.btn_google)
    AnyTextView btnGoogle;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
        checkboxShowPassword.setOnCheckedChangeListener(this);
        txtSignup.setText(Html.fromHtml("New User?<font color='#BB2A41'> Signup</font>"));


    }


    @Override
    public void setTitleBar(TitleBar titleBar) {
        // TODO Auto-generated method stub
        super.setTitleBar(titleBar);
        titleBar.hideTitleBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.checkbox_show_password:
                setPasswordTransformation(isChecked, txtPassword);
                break;
        }
    }

    private void setPasswordTransformation(boolean isChecked, AnyEditTextView textView) {
        if (isChecked) {
            textView.setTransformationMethod(null);
            textView.setSelection(textView.getText().length());
        } else {
            textView.setTransformationMethod(new PasswordTransformationMethod());
            textView.setSelection(textView.getText().length());
        }
    }


    @OnClick({R.id.loginButton, R.id.txt_signup, R.id.txt_forgot_password, R.id.btn_facebook, R.id.btn_google})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.loginButton:
                if (isvalidated()) {
                    prefHelper.setLoginStatus(true);
                    getDockActivity().popBackStackTillEntry(0);
                    getDockActivity().replaceDockableFragment(HomeFragment.newInstance(), "HomeFragmnet");
                }
                break;
            case R.id.txt_signup:
                getDockActivity().replaceDockableFragment(SignupFragment.newInstance(), "SignupFragment", true);
                break;
            case R.id.txt_forgot_password:
                getDockActivity().replaceDockableFragment(ForgotPasswordFragment.newInstance(), "ForgotPasswordFragment", true);
                break;
            case R.id.btn_facebook:
                UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.will_be_implemented));
                break;
            case R.id.btn_google:
                UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.will_be_implemented));
                break;
        }
    }

    private boolean isvalidated() {
        if (txtEmail.getText() == null || txtEmail.getText().toString().isEmpty() ||
                !Patterns.EMAIL_ADDRESS.matcher(txtEmail.getText().toString()).matches()) {
            txtEmail.setError(getString(R.string.enter_valid_email));
            if (txtEmail.requestFocus()) {
                setEditTextFocus(txtEmail);
            }
            return false;
        } else if (txtPassword.getText().toString().isEmpty()) {
            txtPassword.setError(getString(R.string.enter_password));
            if (txtPassword.requestFocus()) {
                setEditTextFocus(txtPassword);
            }
            return false;
        } else if (txtPassword.getText().toString().length() < 6) {
            txtPassword.setError(getString(R.string.passwordLength));
            if (txtPassword.requestFocus()) {
                setEditTextFocus(txtPassword);
            }
            return false;
        } else
            return true;

    }
}
