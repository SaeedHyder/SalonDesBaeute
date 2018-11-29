package com.app.salonbeaute.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.app.salonbeaute.R;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.helpers.DialogHelper;
import com.app.salonbeaute.helpers.UIHelper;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.app.salonbeaute.ui.views.PinEntryEditText;
import com.app.salonbeaute.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class VerifySignupCodeFragment extends BaseFragment {
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.txt_number)
    AnyTextView txtNumber;
    @BindView(R.id.txt_pin_entry)
    PinEntryEditText txtPinEntry;
    @BindView(R.id.verifyButton)
    Button verifyButton;
    @BindView(R.id.btn_resend_code)
    AnyTextView btnResendCode;
    Unbinder unbinder;

    public static VerifySignupCodeFragment newInstance() {
        Bundle args = new Bundle();

        VerifySignupCodeFragment fragment = new VerifySignupCodeFragment();
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
        View view = inflater.inflate(R.layout.fragment_verify_code, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        // TODO Auto-generated method stub
        super.setTitleBar(titleBar);
        titleBar.hideTitleBar();
    }


    @OnClick({R.id.verifyButton, R.id.btn_resend_code, R.id.btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.verifyButton:
                if (isvalidated()) {
                    DialogHelper dialoge = new DialogHelper(getDockActivity());
                    dialoge.forgotPassDialoge(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialoge.hideDialog();
                            getDockActivity().popBackStackTillEntry(0);
                            getDockActivity().replaceDockableFragment(LoginFragment.newInstance(), "LoginFragment");
                        }
                    },getResString(R.string.awesome), getResString(R.string.number_verification),R.drawable.mobile_icon);
                    dialoge.showDialog();

                }
                break;
            case R.id.btn_resend_code:
                UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.will_be_implemented));
                break;

            case R.id.btn_back:
                getDockActivity().popFragment();
                break;
        }
    }

    private boolean isvalidated() {
        if (txtPinEntry.getText().toString().isEmpty() || txtPinEntry.getText().toString().equals("")) {
            UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.enter_valid_pincode));
            return false;
        } else if (txtPinEntry.getText().toString().length() < 4) {
            UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.enter_valid_pincode));
            return false;
        } else
            return true;

    }
}


