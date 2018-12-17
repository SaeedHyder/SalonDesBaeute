package com.app.salonbeaute.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.salonbeaute.R;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.helpers.UIHelper;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.app.salonbeaute.ui.views.CustomRatingBar;
import com.app.salonbeaute.ui.views.RangeSeekBar;
import com.app.salonbeaute.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FilterFragment extends BaseFragment {
    @BindView(R.id.locationIcon)
    ImageView locationIcon;
    @BindView(R.id.txt_current_location)
    AnyTextView txtCurrentLocation;
    @BindView(R.id.txt_country)
    AnyTextView txtCountry;
    @BindView(R.id.ll_location)
    LinearLayout llLocation;
    @BindView(R.id.changeButton)
    Button changeButton;
    @BindView(R.id.okBtn)
    Button okBtn;
    Unbinder unbinder;
    @BindView(R.id.ll_area)
    LinearLayout llArea;
    @BindView(R.id.rbParlourRating)
    CustomRatingBar rbParlourRating;
    @BindView(R.id.ll_specialities)
    LinearLayout llSpecialities;
    @BindView(R.id.ll_services)
    LinearLayout llServices;
    @BindView(R.id.hourRangeBar)
    RangeSeekBar hourRangeBar;
    @BindView(R.id.ll_rangeBar)
    LinearLayout llRangeBar;
    @BindView(R.id.ll_date)
    LinearLayout llDate;

    public static FilterFragment newInstance() {
        Bundle args = new Bundle();

        FilterFragment fragment = new FilterFragment();
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
        View view = inflater.inflate(R.layout.fragment_filter, container, false);
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
        titleBar.showRefreshFilterButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.will_be_implemented));
            }
        });
        titleBar.setSubHeading(getResString(R.string.filters));
    }


    @OnClick({R.id.changeButton, R.id.okBtn,R.id.ll_area, R.id.ll_specialities, R.id.ll_services, R.id.ll_date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.changeButton:
                UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.will_be_implemented));
                break;
            case R.id.okBtn:
                getDockActivity().popFragment();
                break;
            case R.id.ll_area:
                UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.will_be_implemented));
                break;
            case R.id.ll_specialities:
                UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.will_be_implemented));
                break;
            case R.id.ll_services:
                UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.will_be_implemented));
                break;
            case R.id.ll_date:
                UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.will_be_implemented));
                break;
        }
    }




}
