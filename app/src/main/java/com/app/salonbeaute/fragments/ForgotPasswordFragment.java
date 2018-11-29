package com.app.salonbeaute.fragments;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.app.salonbeaute.R;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.helpers.DialogHelper;
import com.app.salonbeaute.ui.views.AnyEditTextView;
import com.app.salonbeaute.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ForgotPasswordFragment extends BaseFragment {


    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.txt_email)
    AnyEditTextView txtEmail;
    @BindView(R.id.submitButton)
    Button submitButton;
    Unbinder unbinder;

    public static ForgotPasswordFragment newInstance() {
        Bundle args = new Bundle();

        ForgotPasswordFragment fragment = new ForgotPasswordFragment();
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
        View view = inflater.inflate(R.layout.fragment_forgot_pass, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @OnClick({R.id.submitButton, R.id.btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.submitButton:
                if (isvalidated())
                    getDockActivity().replaceDockableFragment(VerifyCodeForgotFragment.newInstance(), "VerifyCodeForgotFragment", true);
                break;

            case R.id.btn_back:
                getDockActivity().popFragment();
                break;
        }
    }


    @Override
    public void setTitleBar(TitleBar titleBar) {
        // TODO Auto-generated method stub
        super.setTitleBar(titleBar);
        titleBar.hideTitleBar();
    }

    private boolean isvalidated() {
        if (txtEmail.getText() == null || txtEmail.getText().toString().isEmpty() ||
                !Patterns.EMAIL_ADDRESS.matcher(txtEmail.getText().toString()).matches()) {
            txtEmail.setError(getString(R.string.enter_valid_email));
            if (txtEmail.requestFocus()) {
                setEditTextFocus(txtEmail);
            }
            return false;
        } else
            return true;

    }


}
