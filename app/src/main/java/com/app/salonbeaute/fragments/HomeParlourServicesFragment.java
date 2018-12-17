package com.app.salonbeaute.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.app.salonbeaute.R;
import com.app.salonbeaute.entities.ServicesEnt;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.helpers.UIHelper;
import com.app.salonbeaute.interfaces.RecyclerClickListner;
import com.app.salonbeaute.ui.adapters.ArrayListAdapter;
import com.app.salonbeaute.ui.binders.HomeServicesBinder;
import com.app.salonbeaute.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeParlourServicesFragment extends BaseFragment implements RecyclerClickListner {
    @BindView(R.id.gv_categories_data)
    GridView gvCategoriesData;
    Unbinder unbinder;

    private ArrayListAdapter<ServicesEnt> adapter;
    private ArrayList<ServicesEnt> GridCollection;

    public static HomeParlourServicesFragment newInstance() {
        Bundle args = new Bundle();

        HomeParlourServicesFragment fragment = new HomeParlourServicesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ArrayListAdapter<>(getDockActivity(), new HomeServicesBinder(getDockActivity(), prefHelper, this));

        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_parlour_services, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setGridViewData();


    }

    private void setGridViewData() {

        GridCollection = new ArrayList<>();
        GridCollection.add(new ServicesEnt("Short Hair Cut", "3 Service Avaliable", R.drawable.hair_icon, R.drawable.corner_image_green));
        GridCollection.add(new ServicesEnt("Full Hair Cut", "3 Service Avaliable", R.drawable.wax_icon, R.drawable.corner_image_blue));
        GridCollection.add(new ServicesEnt("Color Dye", "3 Service Avaliable", R.drawable.facial_icon1, R.drawable.corner_image_orange));
        GridCollection.add(new ServicesEnt("Extenso", "3 Service Avaliable", R.drawable.makeup_icon1, R.drawable.corner_image_pink));
        GridCollection.add(new ServicesEnt("Depp Conditioning", "3 Service Avaliable", R.drawable.massage_icon, R.drawable.corner_image_purple));
        GridCollection.add(new ServicesEnt("Hair Mask Treatment", "3 Service Avaliable", R.drawable.hair_icon, R.drawable.corner_image_green));

        adapter.clearList();
        gvCategoriesData.setAdapter(adapter);
        adapter.addAll(GridCollection);
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideTitleBar();
    }


    @Override
    public void onClick(Object entity, int position) {
        getDockActivity().replaceDockableFragment(AddCartFragment.newInstance(), "AddCartFragment");
    }
}
