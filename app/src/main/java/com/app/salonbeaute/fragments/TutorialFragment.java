package com.app.salonbeaute.fragments;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.app.salonbeaute.R;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.interfaces.TutorialInterface;
import com.app.salonbeaute.ui.adapters.CustomPageAdapter;
import com.app.salonbeaute.ui.adapters.TutorialAdapter;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.app.salonbeaute.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.relex.circleindicator.CircleIndicator;

public class TutorialFragment extends BaseFragment implements TutorialInterface {
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.indicator)
    CircleIndicator indicator;
    Unbinder unbinder;
    @BindView(R.id.btnSkip)
    AnyTextView btnSkip;
    @BindView(R.id.btnNext)
    AnyTextView btnNext;

    private ArrayList<String> imagesCollection;
    private TutorialAdapter tutorialAdapter;

    public static TutorialFragment newInstance() {
        Bundle args = new Bundle();

        TutorialFragment fragment = new TutorialFragment();
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
        View view = inflater.inflate(R.layout.fragment_tutorial, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setViewPager();

    }

    private void setViewPager() {

        imagesCollection = new ArrayList<>();
        imagesCollection.add("drawable://" + R.drawable.background5);
        imagesCollection.add("drawable://" + R.drawable.background6);
        imagesCollection.add("drawable://" + R.drawable.background2);
        imagesCollection.add("drawable://" + R.drawable.background3);

        tutorialAdapter = new TutorialAdapter(getMainActivity(), imagesCollection,this);
        viewPager.setAdapter(tutorialAdapter);
        indicator.setViewPager(viewPager);
    }


    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideTitleBar();
    }


    @OnClick({R.id.btnSkip, R.id.btnNext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSkip:
                getDockActivity().popBackStackTillEntry(0);
                getDockActivity().replaceDockableFragment(LoginFragment.newInstance(), "LoginFragment");
                break;
            case R.id.btnNext:
                if (viewPager.getCurrentItem() == tutorialAdapter.getCount()-1) {
                    getDockActivity().popBackStackTillEntry(0);
                    getDockActivity().replaceDockableFragment(LoginFragment.newInstance(), "LoginFragment");
                } else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                }
                break;
        }
    }

    @Override
    public void onClick() {
        prefHelper.setLoginStatus(false);
        prefHelper.setGuestStatus(true);
        getDockActivity().replaceDockableFragment(HomeFragment.newInstance(),"HomeFragment");
    }
}
