package com.app.salonbeaute.fragments;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.salonbeaute.R;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.helpers.DialogHelper;
import com.app.salonbeaute.ui.views.AnyEditTextView;
import com.app.salonbeaute.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ChangePasswordFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.txt_current_password)
    AnyEditTextView txtCurrentPassword;
    @BindView(R.id.checkbox_show_current_password)
    CheckBox checkboxShowCurrentPassword;
    @BindView(R.id.txt_new_password)
    AnyEditTextView txtNewPassword;
    @BindView(R.id.checkbox_show_new_password)
    CheckBox checkboxShowNewPassword;
    @BindView(R.id.txt_verify_password)
    AnyEditTextView txtVerifyPassword;
    @BindView(R.id.checkbox_show_confirm_password)
    CheckBox checkboxShowConfirmPassword;
    @BindView(R.id.changePassBtn)
    Button changePassBtn;
    Unbinder unbinder;
    @BindView(R.id.ll_current_password)
    LinearLayout llCurrentPassword;

    public static ChangePasswordFragment newInstance() {
        Bundle args = new Bundle();

        ChangePasswordFragment fragment = new ChangePasswordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkboxShowCurrentPassword.setOnCheckedChangeListener(this);
        checkboxShowNewPassword.setOnCheckedChangeListener(this);
        checkboxShowConfirmPassword.setOnCheckedChangeListener(this);

    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        // TODO Auto-generated method stub
        super.setTitleBar(titleBar);
        titleBar.hideTitleBar();
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

    @OnClick({R.id.changePassBtn, R.id.btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.changePassBtn:
                if (isvalidateForgotPassword()) {
                    DialogHelper dialoge = new DialogHelper(getDockActivity());
                    dialoge.forgotPassDialoge(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialoge.hideDialog();
                            getDockActivity().popBackStackTillEntry(0);
                            getDockActivity().replaceDockableFragment(LoginFragment.newInstance(), "LoginFragment");
                        }
                    },getResString(R.string.thank_you), getResString(R.string.number_verification),R.drawable.check_icon1);
                    dialoge.showDialog();
                }
                break;

            case R.id.btn_back:
                getDockActivity().popFragment();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {

            case R.id.checkbox_show_current_password:
                setPasswordTransformation(isChecked, txtCurrentPassword);
                break;
            case R.id.checkbox_show_new_password:
                setPasswordTransformation(isChecked, txtNewPassword);
                break;
            case R.id.checkbox_show_confirm_password:
                setPasswordTransformation(isChecked, txtVerifyPassword);
                break;
        }
    }

    private boolean isvalidateChangePassword() {
        if (txtCurrentPassword.getText() == null || (txtCurrentPassword.getText().toString().isEmpty())) {
            txtCurrentPassword.setError(getString(R.string.enter_password));
            return false;
        } else if (txtNewPassword.getText() == null || (txtNewPassword.getText().toString().isEmpty())) {
            txtNewPassword.setError(getString(R.string.enter_password));
            return false;
        } else if (txtNewPassword.getText().toString().equals(txtCurrentPassword.getText().toString())) {
            txtNewPassword.setError(getString(R.string.samePassword));
            return false;
        } else if (txtNewPassword.getText().toString().length() < 6) {
            txtNewPassword.setError(getString(R.string.passwordLength));
            return false;
        } else if (txtVerifyPassword.getText() == null || (txtVerifyPassword.getText().toString().isEmpty())) {
            txtVerifyPassword.setError(getString(R.string.enter_confirm_password));
            return false;
        } else if (txtVerifyPassword.getText().toString().length() < 6) {
            txtVerifyPassword.setError(getString(R.string.confirmpasswordLength));
            return false;
        } else if (!txtVerifyPassword.getText().toString().equals(txtNewPassword.getText().toString())) {
            txtVerifyPassword.setError(getString(R.string.conform_password_error));
            return false;
        } else {
            return true;
        }
    }

    private boolean isvalidateForgotPassword() {
        if (txtNewPassword.getText() == null || (txtNewPassword.getText().toString().isEmpty())) {
            txtNewPassword.setError(getString(R.string.enter_password));
            return false;
        }  else if (txtNewPassword.getText().toString().length() < 6) {
            txtNewPassword.setError(getString(R.string.passwordLength));
            return false;
        } else if (txtVerifyPassword.getText() == null || (txtVerifyPassword.getText().toString().isEmpty())) {
            txtVerifyPassword.setError(getString(R.string.enter_confirm_password));
            return false;
        } else if (txtVerifyPassword.getText().toString().length() < 6) {
            txtVerifyPassword.setError(getString(R.string.confirmpasswordLength));
            return false;
        } else if (!txtVerifyPassword.getText().toString().equals(txtNewPassword.getText().toString())) {
            txtVerifyPassword.setError(getString(R.string.conform_password_error));
            return false;
        } else {
            return true;
        }
    }


}
