package com.app.salonbeaute.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.salonbeaute.R;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.helpers.UIHelper;
import com.app.salonbeaute.ui.adapters.CustomPageAdapter;
import com.app.salonbeaute.ui.binders.NearestParlourBinder;
import com.app.salonbeaute.ui.views.CustomRecyclerView;
import com.app.salonbeaute.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.relex.circleindicator.CircleIndicator;


public class HomeFragment extends BaseFragment {


    @BindView(R.id.btnMenu)
    ImageView btnMenu;
    @BindView(R.id.btnNotificatoin)
    ImageView btnNotificatoin;
    @BindView(R.id.btnSearch)
    ImageView btnSearch;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.indicator)
    CircleIndicator indicator;
    Unbinder unbinder;
    @BindView(R.id.rv_nearestParlours)
    CustomRecyclerView rvNearestParlours;

    private CustomPageAdapter customPageAdapter;
    private ArrayList<String> imagesCollection;
    private ArrayList<String> parlourCollection;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setViewPager();
        setNearestParlours();

    }

    private void setNearestParlours() {
       /* if (entity.size() <= 0) {
            txtNoresult.setVisibility(View.VISIBLE);
            lvFitnessClasses.setVisibility(View.GONE);
        } else {
            txtNoresult.setVisibility(View.GONE);
            lvFitnessClasses.setVisibility(View.VISIBLE);
        }*/
        parlourCollection = new ArrayList<>();
        parlourCollection.add("drawable://" + R.drawable.img_01);
        parlourCollection.add("drawable://" + R.drawable.img_02);
        parlourCollection.add("drawable://" + R.drawable.img_01);
        parlourCollection.add("drawable://" + R.drawable.img_02);
        parlourCollection.add("drawable://" + R.drawable.img_01);
        parlourCollection.add("drawable://" + R.drawable.img_02);

        rvNearestParlours.BindRecyclerView(new NearestParlourBinder(getDockActivity(), prefHelper), parlourCollection,
                new LinearLayoutManager(getDockActivity(), LinearLayoutManager.HORIZONTAL, false)
                , new DefaultItemAnimator());
    }

    private void setViewPager() {

        imagesCollection = new ArrayList<>();
        imagesCollection.add("drawable://" + R.drawable.background1);
        imagesCollection.add("drawable://" + R.drawable.background3);
        imagesCollection.add("drawable://" + R.drawable.background2);
        imagesCollection.add("drawable://" + R.drawable.background5);
        imagesCollection.add("drawable://" + R.drawable.background6);
        imagesCollection.add("drawable://" + R.drawable.background3);

        setPagerSetting();
        customPageAdapter = new CustomPageAdapter(getMainActivity(), imagesCollection);
        viewPager.setAdapter(customPageAdapter);
        indicator.setViewPager(viewPager);
    }

    private void setPagerSetting() {
        if (viewPager != null) {
            viewPager.setClipToPadding(false);
            viewPager.setPageMargin(10);
       /* pager.setPadding(20, 8, 20, 8);
        pager.setOffscreenPageLimit(3);*/
            viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
                @Override
                public void transformPage(View page, float position) {
                    int pageWidth = viewPager.getMeasuredWidth() -
                            viewPager.getPaddingLeft() - viewPager.getPaddingRight();
                    int pageHeight = viewPager.getHeight();
                    int paddingLeft = viewPager.getPaddingLeft();
                    float transformPos = (float) (page.getLeft() -
                            (viewPager.getScrollX() + paddingLeft)) / pageWidth;
                    int max = pageHeight / 10;

                    if (transformPos < -1) { // [-Infinity,-1)
                        // This page is way off-screen to the left.
                        page.setAlpha(0.7f);// to make left transparent
                        page.setScaleY(0.9f);
                    } else if (transformPos <= 1) { // [-1,1]
                        page.setAlpha(1f);
                        page.setScaleY(1f);
                    } else { // (1,+Infinity]
                        // This page is way off-screen to the right.
                        page.setAlpha(0.7f);// to make right transparent
                        page.setScaleY(0.9f);
                    }
                }
            });
        }
    }


    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideTitleBar();
    }

    @OnClick({R.id.btnMenu, R.id.btnNotificatoin, R.id.btnSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnMenu:
                getMainActivity().drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.btnNotificatoin:
                getDockActivity().replaceDockableFragment(NotificationsFragment.newInstance(), "NotificationsFragment", true);
                break;
            case R.id.btnSearch:
                UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.will_be_implemented));
                break;
        }
    }


}

