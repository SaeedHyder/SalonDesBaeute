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
import android.widget.ImageView;
import android.widget.RadioButton;

import com.app.salonbeaute.R;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.helpers.UIHelper;
import com.app.salonbeaute.ui.views.AnyEditTextView;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.app.salonbeaute.ui.views.TitleBar;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.hbb20.CountryCodePicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SignupFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.txt_name)
    AnyEditTextView txtName;
    @BindView(R.id.txt_email)
    AnyEditTextView txtEmail;
    @BindView(R.id.txt_password)
    AnyEditTextView txtPassword;
    @BindView(R.id.checkbox_show_password)
    CheckBox checkboxShowPassword;
    @BindView(R.id.txt_confirm_password)
    AnyEditTextView txtConfirmPassword;
    @BindView(R.id.checkbox_confirm_password)
    CheckBox checkboxConfirmPassword;
    @BindView(R.id.termsCondition)
    CheckBox termsCondition;
    @BindView(R.id.signupButton)
    Button signupButton;
    Unbinder unbinder;
    @BindView(R.id.txttermsCondition)
    AnyTextView txttermsCondition;
    @BindView(R.id.Countrypicker)
    CountryCodePicker Countrypicker;
    @BindView(R.id.edtPhone)
    AnyEditTextView edtPhone;

    PhoneNumberUtil phoneUtil;

    public static SignupFragment newInstance() {
        Bundle args = new Bundle();

        SignupFragment fragment = new SignupFragment();
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
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        checkboxShowPassword.setOnCheckedChangeListener(this);
        checkboxConfirmPassword.setOnCheckedChangeListener(this);
        Countrypicker.registerCarrierNumberEditText(edtPhone);
        phoneUtil = PhoneNumberUtil.getInstance();
        txttermsCondition.setText(Html.fromHtml("By Creating your account you agree to out\n <font color='#BB2A41'> Terms & Condition</font>"));


    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        // TODO Auto-generated method stub
        super.setTitleBar(titleBar);
        titleBar.hideTitleBar();
    }


    @OnClick({R.id.txttermsCondition, R.id.signupButton, R.id.btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txttermsCondition:
                getDockActivity().addDockableFragment(TermsAndConditionFragment.newInstance(), "TermsAndConditionFragment");
                break;
            case R.id.signupButton:
                if (isvalidated()) {
                    /*  serviceHelper.enqueueCall(webService.signup(edtName.getText().toString(), edtEmail.getText().toString(), edtPassword.getText().toString(), "+"+Countrypicker.getSelectedCountryCode(),
                                edtPhone.getText().toString().trim(), "", "", AppConstants.Device_Type, FirebaseInstanceId.getInstance().getToken(),
                                "en", mSocialMediaID, "", ""), SIGNUP);*/
                   getDockActivity().replaceDockableFragment(VerifySignupCodeFragment.newInstance(),"VerifySignupCodeFragment");
                }
                break;
            case R.id.btn_back:
                getDockActivity().popFragment();
                break;
        }
    }

    private boolean isPhoneNumberValid() {

        try {
            Phonenumber.PhoneNumber number = phoneUtil.parse(edtPhone.getText().toString(), Countrypicker.getSelectedCountryNameCode());
            if (phoneUtil.isValidNumber(number)) {
                return true;
            } else {

                return false;
            }
        } catch (NumberParseException e) {
            System.err.println("NumberParseException was thrown: " + e.toString());
            edtPhone.setError(getDockActivity().getResources().getString(R.string.enter_valid_number_error));
            return false;

        }
    }

    private boolean isvalidated() {
        if (txtName.getText().toString().isEmpty() || txtName.getText().toString().length() < 3) {
            txtName.setError(getString(R.string.enter_name));
            if (txtName.requestFocus()) {
                setEditTextFocus(txtName);
            }
            return false;
        } else if (txtEmail.getText() == null || txtEmail.getText().toString().isEmpty() ||
                !Patterns.EMAIL_ADDRESS.matcher(txtEmail.getText().toString()).matches()) {
            txtEmail.setError(getString(R.string.enter_valid_email));
            if (txtEmail.requestFocus()) {
                setEditTextFocus(txtEmail);
            }
            return false;
        } else if (edtPhone.getText().toString().isEmpty()) {

            edtPhone.setError(getString(R.string.enter_phone));
            if (edtPhone.requestFocus()) {
                setEditTextFocus(edtPhone);
            }
            return false;
        } else if (!isPhoneNumberValid()) {
            edtPhone.setError(getDockActivity().getResources().getString(R.string.enter_valid_number_error));
            if (edtPhone.requestFocus()) {
                setEditTextFocus(edtPhone);
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
        } else if (txtConfirmPassword.getText().toString().isEmpty()) {
            txtConfirmPassword.setError(getString(R.string.enter_password));
            if (txtConfirmPassword.requestFocus()) {
                setEditTextFocus(txtConfirmPassword);
            }
            return false;
        } else if (!txtConfirmPassword.getText().toString().equals(txtPassword.getText().toString())) {
            txtConfirmPassword.setError(getString(R.string.confirm_password_error));
            if (txtConfirmPassword.requestFocus()) {
                setEditTextFocus(txtConfirmPassword);
            }
            return false;
        } else if (!termsCondition.isChecked()) {
            UIHelper.showShortToastInDialoge(getDockActivity(), "Select terms and condition to proceed");
            return false;
        } else
            return true;

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.checkbox_show_password:
                setPasswordTransformation(isChecked, txtPassword);
                break;

            case R.id.checkbox_confirm_password:
                setPasswordTransformation(isChecked, txtConfirmPassword);
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


}
