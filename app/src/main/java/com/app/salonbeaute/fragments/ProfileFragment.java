package com.app.salonbeaute.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.app.salonbeaute.R;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.helpers.UIHelper;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.app.salonbeaute.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ProfileFragment extends BaseFragment {
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.btnEditProfile)
    ImageView btnEditProfile;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.txt_email)
    AnyTextView txtEmail;
    @BindView(R.id.txtGender)
    AnyTextView txtGender;
    @BindView(R.id.txtMobileNo)
    AnyTextView txtMobileNo;
    @BindView(R.id.txtAddress)
    AnyTextView txtAddress;
    @BindView(R.id.txtCity)
    AnyTextView txtCity;
    @BindView(R.id.txtState)
    AnyTextView txtState;
    @BindView(R.id.txtShippingAddress)
    AnyTextView txtShippingAddress;
    Unbinder unbinder;

    public static ProfileFragment newInstance() {
        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @OnClick({R.id.btnBack, R.id.btnEditProfile})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                getDockActivity().popFragment();
                break;
            case R.id.btnEditProfile:
                getDockActivity().replaceDockableFragment(EditProfileFragment.newInstance(), "EditProfileFragment", true);
                break;
        }
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideTitleBar();
    }

}
