package com.app.salonbeaute.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.salonbeaute.R;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.interfaces.RecyclerClickListner;
import com.app.salonbeaute.ui.binders.SearchBinder;
import com.app.salonbeaute.ui.views.AnyEditTextView;
import com.app.salonbeaute.ui.views.CustomRecyclerView;
import com.app.salonbeaute.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SearchFragment extends BaseFragment implements RecyclerClickListner {
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.rl_search)
    CustomRecyclerView rVSearch;
    Unbinder unbinder;
    @BindView(R.id.editText_search)
    AnyEditTextView editTextSearch;
    @BindView(R.id.btnCross)
    ImageView btnCross;

    private ArrayList<String> collection;

    public static SearchFragment newInstance() {
        Bundle args = new Bundle();

        SearchFragment fragment = new SearchFragment();
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setTabLayout();
        tabLayoutListner();
        tabsMargin();


    }


    private void setTabLayout() {

        if (tabLayout != null) {
            tabLayout.removeAllTabs();
            tabLayout.addTab(tabLayout.newTab().setText("Hair Cut"));
            tabLayout.addTab(tabLayout.newTab().setText("Hair Care"));
            tabLayout.addTab(tabLayout.newTab().setText("Hair Highlighting"));
            tabLayout.addTab(tabLayout.newTab().setText("Hair Cut"));
            tabLayout.addTab(tabLayout.newTab().setText("Hair Care"));
            tabLayout.addTab(tabLayout.newTab().setText("Hair Highlighting"));

            TabLayout.Tab tab = tabLayout.getTabAt(0);
            tab.select();

            setData();

        }
    }

    private void tabLayoutListner() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                setData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void tabsMargin() {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            View tab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            p.setMargins(15, 0, 15, 0);
            tab.requestLayout();
        }
    }

    private void setData() {

        collection = new ArrayList<>();
        collection.add("");
        collection.add("");
        collection.add("");
        collection.add("");
        collection.add("");
        collection.add("");

        rVSearch.BindRecyclerView(new SearchBinder(getDockActivity(), prefHelper, this), collection,
                new LinearLayoutManager(getDockActivity(), LinearLayoutManager.VERTICAL, false)
                , new DefaultItemAnimator());
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideTitleBar();
    }


    @Override
    public void onClick(Object entity, int position) {
        getDockActivity().addDockableFragment(ParlourDetailFragement.newInstance(), "ParlourDetailFragement");
    }


    @OnClick({R.id.btnBack, R.id.btnCross})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                getDockActivity().popFragment();
                break;
            case R.id.btnCross:
                editTextSearch.getText().clear();
                break;
        }
    }
}
