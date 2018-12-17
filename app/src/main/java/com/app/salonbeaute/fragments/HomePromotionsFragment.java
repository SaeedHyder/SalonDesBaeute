package com.app.salonbeaute.fragments;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.salonbeaute.R;
import com.app.salonbeaute.entities.ParlourServicesEnt;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.ui.adapters.CustomPageAdapter;
import com.app.salonbeaute.ui.adapters.ParlourPromotionsAdapter;
import com.app.salonbeaute.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomePromotionsFragment extends BaseFragment {
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    Unbinder unbinder;

    private ArrayList<ParlourServicesEnt> promotionsCollection;
    private ParlourPromotionsAdapter promotionAdapter;

    public static HomePromotionsFragment newInstance() {
        Bundle args = new Bundle();

        HomePromotionsFragment fragment = new HomePromotionsFragment();
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
        View view = inflater.inflate(R.layout.fragment_parlour_promotions, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setViewPager();
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideTitleBar();
    }

    private void setViewPager() {

        ArrayList<String> titles=new ArrayList<>();
        titles.add("Full Body Wash");
        titles.add("Full Body Polish");
        titles.add("Whitening Manicure");
        titles.add("Whitening Pedicure");
        titles.add("Full Legs");
        titles.add("Facial");
        promotionsCollection = new ArrayList<>();
        promotionsCollection.add(new ParlourServicesEnt(R.drawable.package_header_01,R.drawable.package_bullet_01));
        promotionsCollection.add(new ParlourServicesEnt(R.drawable.package_header_02,R.drawable.package_bullet_02));
        promotionsCollection.add(new ParlourServicesEnt(R.drawable.package_header_03,R.drawable.package_bullet_03));


        setPagerSetting();
        promotionAdapter = new ParlourPromotionsAdapter(getMainActivity() ,titles, promotionsCollection);
        viewPager.setAdapter(promotionAdapter);


    }

    private void setPagerSetting() {
        if (viewPager != null) {
            viewPager.setClipToPadding(false);
            viewPager.setPageMargin(15);
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


}
