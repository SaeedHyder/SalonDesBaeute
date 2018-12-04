package com.app.salonbeaute.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.app.salonbeaute.R;
import com.app.salonbeaute.entities.ServicesEnt;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.interfaces.RecyclerClickListner;
import com.app.salonbeaute.ui.adapters.ArrayListAdapter;
import com.app.salonbeaute.ui.binders.CategoriesGridBinder;
import com.app.salonbeaute.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ParlourServicesFragment extends BaseFragment implements RecyclerClickListner{


    @BindView(R.id.gv_categories_data)
    GridView gvCategoriesData;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    Unbinder unbinder;

    private ArrayListAdapter<ServicesEnt> adapter;
    private ArrayList<ServicesEnt> GridCollection;

    public static ParlourServicesFragment newInstance() {
        Bundle args = new Bundle();

        ParlourServicesFragment fragment = new ParlourServicesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ArrayListAdapter<>(getDockActivity(), new CategoriesGridBinder(getDockActivity(), prefHelper, this));
        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parlour_services, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setTabLayout();
        tabViewListner();
        setGridViewData();
    }



    private void setTabLayout() {

        if (tabLayout != null) {
            tabLayout.removeAllTabs();
            tabLayout.addTab(tabLayout.newTab().setText("Hair"));
            tabLayout.addTab(tabLayout.newTab().setText("Wax"));
            tabLayout.addTab(tabLayout.newTab().setText("Facial"));
            tabLayout.addTab(tabLayout.newTab().setText("Make Up"));
            tabLayout.addTab(tabLayout.newTab().setText("Hair"));
            tabLayout.addTab(tabLayout.newTab().setText("Wax"));
            tabLayout.addTab(tabLayout.newTab().setText("Facial"));
            tabLayout.addTab(tabLayout.newTab().setText("Make Up"));
            TabLayout.Tab tab = tabLayout.getTabAt(0);
            tab.select();

           // setData();

        }
    }

    private void tabViewListner() {

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                //setData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }



    private void setGridViewData() {

        GridCollection = new ArrayList<>();
        GridCollection.add(new ServicesEnt("Short Hair Cut",R.drawable.hair_icon,R.drawable.corner_image_green));
        GridCollection.add(new ServicesEnt("Full Hair Cut",R.drawable.wax_icon,R.drawable.corner_image_blue));
        GridCollection.add(new ServicesEnt("Color Dye",R.drawable.facial_icon1,R.drawable.corner_image_orange));
        GridCollection.add(new ServicesEnt("Extenso",R.drawable.makeup_icon1,R.drawable.corner_image_pink));
        GridCollection.add(new ServicesEnt("Depp Conditioning",R.drawable.massage_icon,R.drawable.corner_image_purple));
        GridCollection.add(new ServicesEnt("Hair Mask Treatment",R.drawable.hair_icon,R.drawable.corner_image_green));

        adapter.clearList();
        gvCategoriesData.setAdapter(adapter);
        adapter.addAll(GridCollection);
    }




    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getResString(R.string.salon_des_beaute));
        titleBar.showNotificationButton(0);
        titleBar.showSearchButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDockActivity().replaceDockableFragment(NearByFragment.newInstance(), "NearByFragment");
            }
        });
    }


    @Override
    public void onClick(Object entity, int position) {
        getDockActivity().replaceDockableFragment(ParlourServiceDetailFragment.newInstance(), "ParlourServiceDetailFragment");

    }
}
