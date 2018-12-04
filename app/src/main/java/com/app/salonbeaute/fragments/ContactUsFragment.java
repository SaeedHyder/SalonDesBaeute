package com.app.salonbeaute.fragments;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Patterns;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.salonbeaute.R;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.global.AppConstants;
import com.app.salonbeaute.helpers.UIHelper;
import com.app.salonbeaute.ui.views.AnyEditTextView;
import com.app.salonbeaute.ui.views.TitleBar;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ContactUsFragment extends BaseFragment implements OnMapReadyCallback {
    @BindView(R.id.ViewMapButton)
    Button ViewMapButton;
    @BindView(R.id.card_view)
    CardView cardView;
    @BindView(R.id.edt_name)
    AnyEditTextView edtName;
    @BindView(R.id.edt_email)
    AnyEditTextView edtEmail;
    @BindView(R.id.edt_comment)
    AnyEditTextView edtComment;
    Unbinder unbinder;
    @BindView(R.id.submitBtn)
    Button submitBtn;

    private GoogleMap mMap;
    private View viewParent;
    private SupportMapFragment mapFragment;

    public static ContactUsFragment newInstance() {
        Bundle args = new Bundle();

        ContactUsFragment fragment = new ContactUsFragment();
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
            viewParent = inflater.inflate(R.layout.fragment_contact_us, container, false);

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
        listner();
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
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(25.2048,55.2708), AppConstants.zoomIn));
        mMap.getUiSettings().setAllGesturesEnabled(false);
    }


    private void listner() {
        edtComment.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                v.getParent().requestDisallowInterceptTouchEvent(true);
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        // TODO Auto-generated method stub
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getResString(R.string.contact_us));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ViewMapButton, R.id.submitBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ViewMapButton:
                UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.will_be_implemented));
                break;
            case R.id.submitBtn:
                if (isvalidated()) {
                    getDockActivity().popFragment();
                }
                break;
        }
    }

    private boolean isvalidated() {
        if (edtName.getText().toString().isEmpty()) {
            edtName.setError(getString(R.string.enter_name));
            if (edtName.requestFocus()) {
                setEditTextFocus(edtName);
            }
            return false;
        } else if (edtEmail.getText() == null || edtEmail.getText().toString().isEmpty() ||
                !Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText().toString()).matches()) {
            edtEmail.setError(getString(R.string.enter_valid_email));
            if (edtEmail.requestFocus()) {
                setEditTextFocus(edtEmail);
            }
            return false;
        } else if (edtComment.getText().toString().isEmpty()) {
            edtComment.setError(getString(R.string.enter_comment));
            if (edtComment.requestFocus()) {
                setEditTextFocus(edtComment);
            }
            return false;
        } else
            return true;

    }
}
