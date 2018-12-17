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
import com.app.salonbeaute.ui.binders.ListViewBinder;
import com.app.salonbeaute.ui.binders.ReviewsBinder;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.app.salonbeaute.ui.views.CustomRecyclerView;
import com.app.salonbeaute.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ReviewsFragment extends BaseFragment implements RecyclerClickListner{
    @BindView(R.id.txt_no_data)
    AnyTextView txtNoData;
    @BindView(R.id.rv_reviews)
    CustomRecyclerView rvReviews;
    Unbinder unbinder;

    private ArrayList<String> collection;
    public static ReviewsFragment newInstance() {
        Bundle args = new Bundle();

        ReviewsFragment fragment = new ReviewsFragment();
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
        View view = inflater.inflate(R.layout.fragment_reviews, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setData();
    }

    private void setData() {

        collection = new ArrayList<>();
        collection.add("");
        collection.add("");
        collection.add("");
        collection.add("");
        collection.add("");
        collection.add("");
        collection.add("");
        collection.add("");
        collection.add("");

        rvReviews.BindRecyclerView(new ReviewsBinder(getDockActivity(), prefHelper,this), collection,
                new LinearLayoutManager(getDockActivity(), LinearLayoutManager.VERTICAL, false)
                , new DefaultItemAnimator());
    }


    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getResString(R.string.reviews));
        titleBar.showSearchButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDockActivity().replaceDockableFragment(SearchFragment.newInstance(), "SearchFragment");
            }
        });
    }



    @Override
    public void onClick(Object entity, int position) {
        getDockActivity().replaceDockableFragment(ReviewDetailFragment.newInstance(), "ReviewDetailFragment");
    }
}
