package com.app.salonbeaute.fragments;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.app.salonbeaute.R;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.helpers.UIHelper;
import com.app.salonbeaute.interfaces.RecyclerClickListner;
import com.app.salonbeaute.ui.binders.ReviewsDetailBinder;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.app.salonbeaute.ui.views.CustomRatingBar;
import com.app.salonbeaute.ui.views.CustomRecyclerView;
import com.app.salonbeaute.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ReviewDetailFragment extends BaseFragment implements RecyclerClickListner {
    @BindView(R.id.title_image)
    ImageView titleImage;
    @BindView(R.id.txt_title)
    AnyTextView txtTitle;
    @BindView(R.id.rbParlourRating)
    CustomRatingBar rbParlourRating;
    @BindView(R.id.txt_reviews)
    AnyTextView txtReviews;
    @BindView(R.id.rv_reviews)
    CustomRecyclerView rvReviews;
    @BindView(R.id.yesCount)
    AnyTextView yesCount;
    @BindView(R.id.yesButton)
    Button yesButton;
    @BindView(R.id.noButton)
    Button noButton;
    @BindView(R.id.noCount)
    AnyTextView noCount;
    Unbinder unbinder;
    @BindView(R.id.txt_overAllRating)
    AnyTextView txtOverAllRating;
    @BindView(R.id.rbRatingPerformance)
    CustomRatingBar rbRatingPerformance;
    @BindView(R.id.txt_reviewsPerformance)
    AnyTextView txtReviewsPerformance;
    @BindView(R.id.sb_excellent)
    SeekBar sbExcellent;
    @BindView(R.id.excellent_count)
    AnyTextView excellentCount;
    @BindView(R.id.sb_very_good)
    SeekBar sbVeryGood;
    @BindView(R.id.very_good_count)
    AnyTextView veryGoodCount;
    @BindView(R.id.sb_good)
    SeekBar sbGood;
    @BindView(R.id.good_count)
    AnyTextView goodCount;
    @BindView(R.id.sb_average)
    SeekBar sbAverage;
    @BindView(R.id.average_count)
    AnyTextView averageCount;
    @BindView(R.id.sb_poor)
    SeekBar sbPoor;
    @BindView(R.id.poor_count)
    AnyTextView poorCount;

    private ArrayList<String> collection;

    public static ReviewDetailFragment newInstance() {
        Bundle args = new Bundle();

        ReviewDetailFragment fragment = new ReviewDetailFragment();
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
        View view = inflater.inflate(R.layout.fragment_review_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setData();
        disableSeekBar();
    }

    private void disableSeekBar() {

        sbExcellent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        sbVeryGood.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        sbGood.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        sbAverage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        sbPoor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
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

        rvReviews.BindRecyclerView(new ReviewsDetailBinder(getDockActivity(), prefHelper, this), collection,
                new LinearLayoutManager(getDockActivity(), LinearLayoutManager.VERTICAL, false)
                , new DefaultItemAnimator());
    }


    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.showSearchButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDockActivity().replaceDockableFragment(SearchFragment.newInstance(), "SearchFragment");
            }
        });
        titleBar.setSubHeading(getResString(R.string.reviews));
    }

    @OnClick({R.id.yesButton, R.id.noButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.yesButton:
                UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.will_be_implemented));
                break;
            case R.id.noButton:
                UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.will_be_implemented));
                break;
        }
    }

    @Override
    public void onClick(Object entity, int position) {
        getDockActivity().addDockableFragment(WriteReviewFragment.newInstance(),"WriteReviewFragment");
    }


}
