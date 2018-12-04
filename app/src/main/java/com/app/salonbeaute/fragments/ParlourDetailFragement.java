package com.app.salonbeaute.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.salonbeaute.R;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.app.salonbeaute.ui.views.CustomRatingBar;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ParlourDetailFragement extends BaseFragment {
    @BindView(R.id.imageSlider)
    SliderLayout imageSlider;
    @BindView(R.id.pagerIndicator)
    PagerIndicator pagerIndicator;
    @BindView(R.id.txtServiceType)
    AnyTextView txtServiceType;
    @BindView(R.id.txtDistance)
    AnyTextView txtDistance;
    @BindView(R.id.rbParlourRating)
    CustomRatingBar rbParlourRating;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    Unbinder unbinder;

    public static ParlourDetailFragement newInstance() {
        Bundle args = new Bundle();

        ParlourDetailFragement fragment = new ParlourDetailFragement();
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
        View view = inflater.inflate(R.layout.fragment_parlour_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout.addTab(tabLayout.newTab().setText(R.string.services));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.promotions));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.about));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}