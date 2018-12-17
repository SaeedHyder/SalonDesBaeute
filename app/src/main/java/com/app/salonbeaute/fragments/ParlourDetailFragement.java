package com.app.salonbeaute.fragments;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.app.salonbeaute.R;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.helpers.DialogHelper;
import com.app.salonbeaute.helpers.UIHelper;
import com.app.salonbeaute.ui.adapters.PromotionAdapter;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.app.salonbeaute.ui.views.CustomRatingBar;
import com.app.salonbeaute.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.relex.circleindicator.CircleIndicator;

import static com.app.salonbeaute.activities.DockActivity.KEY_FRAG_FIRST;

public class ParlourDetailFragement extends BaseFragment {

    @BindView(R.id.txtServiceType)
    AnyTextView txtServiceType;
    @BindView(R.id.txtDistance)
    AnyTextView txtDistance;
    @BindView(R.id.rbParlourRating)
    CustomRatingBar rbParlourRating;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.indicator)
    CircleIndicator indicator;
    @BindView(R.id.containerImages)
    RelativeLayout containerImages;
    @BindView(R.id.fragmentContainer)
    FrameLayout fragmentContainer;
    Unbinder unbinder;


    private ArrayList<String> imagesCollection;
    private PromotionAdapter viewPagerAdapter;

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

        setViewPager();
        tabLayout.addTab(tabLayout.newTab().setText(R.string.services));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.promotions));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.about));
        TabLayout.Tab tab = tabLayout.getTabAt(0);
        tab.select();
        setData(tab);

        tabLayoutListner();

    }

    private void tabLayoutListner() {

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                setData(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setData(TabLayout.Tab tab) {

        if (tab.getPosition() == 0) {
            replaceFragment(HomeParlourServicesFragment.newInstance());
        } else if (tab.getPosition() == 1) {
            replaceFragment(HomePromotionsFragment.newInstance());
        } else {
            replaceFragment(ParlourAboutFragment.newInstance());
        }
    }

    private void setViewPager() {

        imagesCollection = new ArrayList<>();
        imagesCollection.add("drawable://" + R.drawable.background1);
        imagesCollection.add("drawable://" + R.drawable.background3);
        imagesCollection.add("drawable://" + R.drawable.background2);
        imagesCollection.add("drawable://" + R.drawable.background5);
        imagesCollection.add("drawable://" + R.drawable.background6);
        imagesCollection.add("drawable://" + R.drawable.background3);

        viewPagerAdapter = new PromotionAdapter(getMainActivity(), imagesCollection);
        viewPager.setAdapter(viewPagerAdapter);
        indicator.setViewPager(viewPager);
    }


    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getResString(R.string.salon_des_beaute));
        titleBar.showCartButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDockActivity().addDockableFragment(PaymentDetailFragment.newInstance(), "PaymentDetailFragment");
            }
        });
        titleBar.showShareButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogHelper dialoge = new DialogHelper(getDockActivity());
                dialoge.shareDialoge(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DialogHelper dialoge = new DialogHelper(getDockActivity());
                        dialoge.shareDialoge(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.will_be_implemented));

                            }
                        });
                        dialoge.showDialog();
                    }
                });
                dialoge.showDialog();
            }
        });
    }

    public void replaceFragment(Fragment frag) {

        FragmentManager manager = getChildFragmentManager();
        ;
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, frag);
        transaction.addToBackStack(manager.getBackStackEntryCount() == 0 ? KEY_FRAG_FIRST : null).commit();

    }


}