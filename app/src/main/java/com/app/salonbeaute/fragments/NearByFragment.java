package com.app.salonbeaute.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import com.app.salonbeaute.R;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.ui.views.TitleBar;
import com.google.android.gms.maps.MapFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.app.salonbeaute.activities.DockActivity.KEY_FRAG_FIRST;

public class NearByFragment extends BaseFragment {
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.btnNotificatoin)
    ImageView btnNotificatoin;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.fragmentContainer)
    FrameLayout fragmentContainer;
    Unbinder unbinder;

    private FragmentManager manager;

    public static NearByFragment newInstance() {
        Bundle args = new Bundle();

        NearByFragment fragment = new NearByFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        manager = getChildFragmentManager();
        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_near_by, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setTabLayout();
        tabLayoutistner();
    }


    private void setTabLayout() {

        if (tabLayout != null) {
            tabLayout.removeAllTabs();
            tabLayout.addTab(tabLayout.newTab().setText(getResString(R.string.map_view)));
            tabLayout.addTab(tabLayout.newTab().setText(getResString(R.string.list_view)));
            TabLayout.Tab tab = tabLayout.getTabAt(0);
            tab.select();
            setData(tab);


        }
    }

    private void tabLayoutistner() {

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
            replaceFragment(MapViewFragment.newInstance());
        } else {
            replaceFragment(ListViewFragment.newInstance());
        }
    }

    @OnClick({R.id.btnBack, R.id.btnNotificatoin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                getDockActivity().popFragment();
                break;
            case R.id.btnNotificatoin:
                getDockActivity().addDockableFragment(NotificationsFragment.newInstance(), "NotificationsFragment");
                break;
        }
    }

    public void replaceFragment(Fragment frag) {


        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragmentContainer, frag);
        transaction.addToBackStack(manager.getBackStackEntryCount() == 0 ? KEY_FRAG_FIRST : null).commit();


    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideTitleBar();
    }

    @Override
    public void onResume() {
        super.onResume();
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.tab_layout_color));
    }
}
