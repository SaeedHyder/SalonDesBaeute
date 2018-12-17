package com.app.salonbeaute.fragments;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.salonbeaute.R;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.helpers.UIHelper;
import com.app.salonbeaute.interfaces.RecyclerClickListner;
import com.app.salonbeaute.ui.binders.StaffBinder;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.app.salonbeaute.ui.views.CustomRatingBar;
import com.app.salonbeaute.ui.views.CustomRecyclerView;
import com.app.salonbeaute.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ParlourAboutFragment extends BaseFragment implements RecyclerClickListner {
    @BindView(R.id.txt_salon_title)
    AnyTextView txtSalonTitle;
    @BindView(R.id.txt_salon_detai)
    AnyTextView txtSalonDetai;
    @BindView(R.id.txt_address)
    AnyTextView txtAddress;
    @BindView(R.id.txt_number)
    AnyTextView txtNumber;
    @BindView(R.id.rbParlourRating)
    CustomRatingBar rbParlourRating;
    @BindView(R.id.txt_rating)
    AnyTextView txtRating;
    @BindView(R.id.txt_reviews)
    AnyTextView txtReviews;
    @BindView(R.id.rv_staff)
    CustomRecyclerView rvStaff;
    @BindView(R.id.txt_heading)
    AnyTextView txtHeading;
    @BindView(R.id.txt_text)
    AnyTextView txtText;
    Unbinder unbinder;
    @BindView(R.id.viewAll)
    AnyTextView viewAll;

    private ArrayList<String> staffCollection;

    public static ParlourAboutFragment newInstance() {
        Bundle args = new Bundle();

        ParlourAboutFragment fragment = new ParlourAboutFragment();
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
        View view = inflater.inflate(R.layout.fragment_parlour_about, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setStaffData();

    }

    private void setStaffData() {

        staffCollection = new ArrayList<>();
        staffCollection.add("");
        staffCollection.add("");
        staffCollection.add("");
        staffCollection.add("");
        staffCollection.add("");
        staffCollection.add("");
        staffCollection.add("");

        rvStaff.BindRecyclerView(new StaffBinder(getDockActivity(), prefHelper, this), staffCollection,
                new LinearLayoutManager(getDockActivity(), LinearLayoutManager.HORIZONTAL, false)
                , new DefaultItemAnimator());


    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideTitleBar();
    }


    @Override
    public void onClick(Object entity, int position) {

    }

    @OnClick({R.id.txt_reviews, R.id.viewAll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_reviews:
                getDockActivity().addDockableFragment(ReviewDetailFragment.newInstance(),"ReviewDetailFragment");
                break;
            case R.id.viewAll:
               getDockActivity().replaceDockableFragment(StaffFragment.newInstance(),"StaffFragment",true);
                break;
        }
    }
}
