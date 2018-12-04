package com.app.salonbeaute.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.app.salonbeaute.R;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.helpers.UIHelper;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.app.salonbeaute.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SettingFragment extends BaseFragment {
    @BindView(R.id.toggleNotification)
    ToggleButton toggleNotification;
    @BindView(R.id.btn_change_password)
    AnyTextView btnChangePassword;
    @BindView(R.id.btn_reviews)
    AnyTextView btnReviews;
    @BindView(R.id.btn_terms_cond)
    AnyTextView btnTermsCond;
    @BindView(R.id.btn_privacy_policy)
    AnyTextView btnPrivacyPolicy;
    Unbinder unbinder;

    public static SettingFragment newInstance() {
        Bundle args = new Bundle();

        SettingFragment fragment = new SettingFragment();
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
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getResString(R.string.settings));
    }

    @OnClick({R.id.btn_change_password, R.id.btn_reviews, R.id.btn_terms_cond, R.id.btn_privacy_policy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_change_password:
                getDockActivity().replaceDockableFragment(ChangePasswordFragment.newInstance(true), "ChangePasswordFragment", true);
                break;
            case R.id.btn_reviews:
                getDockActivity().replaceDockableFragment(ReviewsFragment.newInstance(), "ReviewsFragment", true);
                break;
            case R.id.btn_terms_cond:
                getDockActivity().replaceDockableFragment(TermsAndConditionFragment.newInstance(), "TermsAndConditionFragment", true);
                break;
            case R.id.btn_privacy_policy:
                getDockActivity().replaceDockableFragment(PrivacyPolicyFragment.newInstance(), "PrivacyPolicyFragment", true);
                break;
        }
    }
}
