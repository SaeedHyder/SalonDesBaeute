package com.app.salonbeaute.fragments;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.app.salonbeaute.R;
import com.app.salonbeaute.entities.MapScreenItem;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.global.AppConstants;
import com.app.salonbeaute.helpers.UIHelper;
import com.app.salonbeaute.map.abstracts.GoogleMapOptions;
import com.app.salonbeaute.map.abstracts.MapMarkerItemBinder;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.app.salonbeaute.ui.views.CustomRatingBar;
import com.app.salonbeaute.ui.views.TitleBar;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BookingDetailFragment extends BaseFragment implements OnMapReadyCallback {
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.cv_image)
    CardView cvImage;
    @BindView(R.id.txtTitle)
    AnyTextView txtTitle;
    @BindView(R.id.txtDetail)
    AnyTextView txtDetail;
    @BindView(R.id.rbRating)
    CustomRatingBar rbRating;
    @BindView(R.id.txt_rating)
    AnyTextView txtRating;
    @BindView(R.id.txt_starting_rate)
    AnyTextView txtStartingRate;
    @BindView(R.id.ll_data)
    LinearLayout llData;
    @BindView(R.id.rl_item)
    RelativeLayout rlItem;
    Unbinder unbinder;
    @BindView(R.id.txt_date)
    AnyTextView txtDate;
    @BindView(R.id.callBtn)
    ImageView callBtn;
    @BindView(R.id.txt_price)
    AnyTextView txtPrice;
    @BindView(R.id.txt_Stylist)
    AnyTextView txtStylist;
    @BindView(R.id.txt_time)
    AnyTextView txtTime;
    @BindView(R.id.changeButton)
    Button changeButton;
    @BindView(R.id.cancelButton)
    Button cancelButton;

    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private View viewParent;
    private ArrayList<MapScreenItem> mapCollection;

    public static BookingDetailFragment newInstance() {
        Bundle args = new Bundle();

        BookingDetailFragment fragment = new BookingDetailFragment();
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
        View view = inflater.inflate(R.layout.fragment_booking_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mapFragment == null) {
            initMap();
        }
    }

    private void initMap() {
        mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(25.218322, 55.309210), AppConstants.zoomIn));

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
        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading("ID 1234");
    }


    @OnClick({R.id.callBtn, R.id.changeButton, R.id.cancelButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.callBtn:
                UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.will_be_implemented));
                break;
            case R.id.changeButton:
                UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.will_be_implemented));
                break;
            case R.id.cancelButton:
                UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.will_be_implemented));
                break;
        }
    }
}
