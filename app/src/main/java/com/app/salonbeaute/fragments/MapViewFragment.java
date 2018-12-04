package com.app.salonbeaute.fragments;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.salonbeaute.R;
import com.app.salonbeaute.entities.MapScreenItem;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.global.AppConstants;
import com.app.salonbeaute.helpers.UIHelper;
import com.app.salonbeaute.interfaces.RecyclerClickListner;
import com.app.salonbeaute.map.abstracts.GoogleMapOptions;
import com.app.salonbeaute.map.abstracts.MapMarkerItemBinder;
import com.app.salonbeaute.ui.binders.ListViewBinder;
import com.app.salonbeaute.ui.binders.MapViewBinder;
import com.app.salonbeaute.ui.views.AnyEditTextView;
import com.app.salonbeaute.ui.views.CustomRecyclerView;
import com.app.salonbeaute.ui.views.TitleBar;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MapViewFragment extends BaseFragment implements OnMapReadyCallback ,RecyclerClickListner{
    @BindView(R.id.edtSearchBox)
    AnyEditTextView edtSearchBox;
    @BindView(R.id.btn_filter)
    ImageView btnFilter;
    @BindView(R.id.card_view)
    CardView cardView;
    Unbinder unbinder;
    @BindView(R.id.rv_parlours)
    CustomRecyclerView rvParlours;

    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private View viewParent;
    private ArrayList<MapScreenItem> mapCollection ;
    private ArrayList<String> parlourCollection ;

    public static MapViewFragment newInstance() {
        Bundle args = new Bundle();

        MapViewFragment fragment = new MapViewFragment();
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
        if (viewParent != null) {
            ViewGroup parent = (ViewGroup) viewParent.getParent();
            if (parent != null)
                parent.removeView(viewParent);
        }
        try {
            viewParent = inflater.inflate(R.layout.fragment_map_view, container, false);

        } catch (InflateException e) {
            e.printStackTrace();
        }
        unbinder = ButterKnife.bind(this, viewParent);
        return viewParent;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mapFragment == null) {
            initMap();
        }
        setParlourData();

    }

    private void setParlourData() {


        parlourCollection = new ArrayList<>();
        parlourCollection.add("");
        parlourCollection.add("");
        parlourCollection.add("");
        parlourCollection.add("");
        parlourCollection.add("");


        rvParlours.BindRecyclerView(new MapViewBinder(getDockActivity(), prefHelper,this), parlourCollection,
                new LinearLayoutManager(getDockActivity(), LinearLayoutManager.HORIZONTAL, false)
                , new DefaultItemAnimator());
    }

    private void initMap() {
        mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(25.218322,55.309210), AppConstants.zoomIn));

        setMarkers();
    }

    private void setMarkers() {

        mapCollection = new ArrayList<>();

        mapCollection.add(new MapScreenItem("25.204849", "55.270783", R.drawable.map_icon2));
        mapCollection.add(new MapScreenItem("25.209740", "55.274330", R.drawable.map_icon2));
        mapCollection.add(new MapScreenItem("25.218322", "55.309210", R.drawable.red_map_icon));
        mapCollection.add(new MapScreenItem("25.259935", "55.292387", R.drawable.map_icon2));
        mapCollection.add(new MapScreenItem("25.276391", "55.362768", R.drawable.map_icon2));
        mapCollection.add(new MapScreenItem("25.208397", "55.271852", R.drawable.map_icon2));

        addMarker(mapCollection);
    }


    void addMarker(final ArrayList<MapScreenItem> mapCollection) {

        GoogleMapOptions<MapScreenItem> googleMapOptions = new GoogleMapOptions<>(getDockActivity(),
                mMap,
                mapCollection,
                null,
                new MapMarkerItemBinder(getMainActivity(), getDockActivity())
        );

        googleMapOptions.addMarkers();

    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideTitleBar();
    }


    @OnClick(R.id.btn_filter)
    public void onViewClicked() {
        UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.will_be_implemented));
    }

    @Override
    public void onClick(Object entity, int position) {

    }
}
