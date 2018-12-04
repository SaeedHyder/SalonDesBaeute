package com.app.salonbeaute.fragments;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.app.salonbeaute.R;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.helpers.CameraHelper;
import com.app.salonbeaute.helpers.UIHelper;
import com.app.salonbeaute.interfaces.ImageSetter;
import com.app.salonbeaute.ui.views.AnyEditTextView;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.app.salonbeaute.ui.views.TitleBar;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.hbb20.CountryCodePicker;
import com.iceteck.silicompressorr.SiliCompressor;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileFragment extends BaseFragment implements ImageSetter {
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.btnDone)
    ImageView btnDone;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.iv_camera)
    ImageView ivCamera;
    @BindView(R.id.btnUploadImage)
    RelativeLayout btnUploadImage;
    @BindView(R.id.txt_name)
    AnyTextView txtName;
    @BindView(R.id.txt_country)
    AnyTextView txtCountry;
    @BindView(R.id.edt_name)
    AnyEditTextView edtName;
    @BindView(R.id.edt_email)
    AnyEditTextView edtEmail;
    @BindView(R.id.Countrypicker)
    CountryCodePicker Countrypicker;
    @BindView(R.id.edtPhone)
    AnyEditTextView edtPhone;
    @BindView(R.id.edt_address)
    AnyEditTextView edtAddress;
    @BindView(R.id.edt_shippaddress)
    AnyEditTextView edtShippaddress;
    Unbinder unbinder;
    @BindView(R.id.iv_image)
    CircleImageView ivImage;
    @BindView(R.id.spn_city)
    Spinner spnCity;
    @BindView(R.id.spn_province)
    Spinner spnProvince;
    @BindView(R.id.spn_gender)
    Spinner spnGender;

    private File profilePic;
    private Bitmap profilePicBitmap;
    private String profilePath;
    private ImageLoader imageLoader;
    private PhoneNumberUtil phoneUtil;

    public static EditProfileFragment newInstance() {
        Bundle args = new Bundle();

        EditProfileFragment fragment = new EditProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageLoader = ImageLoader.getInstance();
        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getMainActivity().setImageSetter(this);
        phoneUtil = PhoneNumberUtil.getInstance();
        setCitySpinner();
        setProvinceSpinner();
        setGenderSpinner();
        listner();
    }

    private void listner() {
        edtAddress.setOnTouchListener(new View.OnTouchListener() {

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

        edtShippaddress.setOnTouchListener(new View.OnTouchListener() {

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
        super.setTitleBar(titleBar);
        titleBar.hideTitleBar();
    }


    @OnClick({R.id.btnBack, R.id.btnDone, R.id.btnUploadImage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                getDockActivity().popFragment();
                break;
            case R.id.btnDone:
                if (isvalidated()) {
                    getDockActivity().popFragment();
                }
                break;
            case R.id.btnUploadImage:
                requestCameraPermission();
                break;
        }
    }


    private void setGenderSpinner() {

        ArrayList<String> genderData = new ArrayList<>();
        genderData.add("Select Gender");
        genderData.add("Male");
        genderData.add("Female");

        ArrayAdapter<String> gendarAdapter = new ArrayAdapter<String>(getDockActivity(), android.R.layout.simple_spinner_item, genderData) {
            @Override
            public boolean isEnabled(int position) {
                return !(position == 0);
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTextColor(position == 0 ? Color.GRAY : Color.BLACK);
                return view;
            }
        };
        gendarAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spnGender.setAdapter(gendarAdapter);

        spnGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setCitySpinner() {

        ArrayList<String> cityCollection = new ArrayList<>();
        cityCollection.add("Select City");
        cityCollection.add("Karachi");
        cityCollection.add("Hyderabd");

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(getDockActivity(), android.R.layout.simple_spinner_item, cityCollection) {
            @Override
            public boolean isEnabled(int position) {
                return !(position == 0);
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTextColor(position == 0 ? Color.GRAY : Color.BLACK);
                return view;
            }
        };
        cityAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spnCity.setAdapter(cityAdapter);

        spnCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setProvinceSpinner() {

        ArrayList<String> provinceCollection = new ArrayList<>();
        provinceCollection.add("Select Province");
        provinceCollection.add("Sindh");
        provinceCollection.add("Punjab");
        provinceCollection.add("KPK");

        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(getDockActivity(), android.R.layout.simple_spinner_item, provinceCollection) {
            @Override
            public boolean isEnabled(int position) {
                return !(position == 0);
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTextColor(position == 0 ? Color.GRAY : Color.BLACK);
                return view;
            }
        };

        provinceAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spnProvince.setAdapter(provinceAdapter);

        spnProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
        } else if (!isPhoneNumberValid()) {
            edtPhone.setError(getDockActivity().getResources().getString(R.string.enter_valid_number_error));
            if (edtPhone.requestFocus()) {
                setEditTextFocus(edtPhone);
            }
            return false;
        }else if (edtAddress.getText().toString().isEmpty()) {
            edtAddress.setError(getString(R.string.enter_your_address));
            if (edtAddress.requestFocus()) {
                setEditTextFocus(edtAddress);
            }
            return false;
        } else if (spnCity.getSelectedItemPosition() == 0) {
            UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.select_city));
            return false;
        } else if (spnProvince.getSelectedItemPosition() == 0) {
            UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.select_province));
            return false;
        } else if (spnGender.getSelectedItemPosition() == 0) {
            UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.select_gender));
            return false;
        } else if (edtShippaddress.getText().toString().isEmpty()) {
            edtShippaddress.setError(getString(R.string.enter_shipping_address));
            if (edtShippaddress.requestFocus()) {
                setEditTextFocus(edtShippaddress);
            }
            return false;
        } else if (profilePicBitmap == null) {
            UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.select_profile_pic));
            return false;
        } else
            return true;

    }

    private void requestCameraPermission() {
        Dexter.withActivity(getDockActivity())
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {

                        if (report.areAllPermissionsGranted()) {
                            CameraHelper.uploadPhotoDialog(getMainActivity());
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            requestCameraPermission();

                        } else if (report.getDeniedPermissionResponses().size() > 0) {
                            requestCameraPermission();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        UIHelper.showShortToastInCenter(getDockActivity(), "Grant Camera And Storage Permission to processed");
                        openSettings();
                    }
                })

                .onSameThread()
                .check();


    }


    private void openSettings() {

        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        Uri uri = Uri.fromParts("package", getDockActivity().getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

    @Override
    public void setImage(String imagePath) {
        if (imagePath != null) {
            imageLoader.displayImage("file:///" + imagePath, ivImage);
            try {
                profilePicBitmap = SiliCompressor.with(getDockActivity()).getCompressBitmap(imagePath);
            } catch (Exception e) {
                e.printStackTrace();
                UIHelper.showShortToastInCenter(getDockActivity(), getDockActivity().getResources().getString(R.string.internet_issue));
            }
        }
    }
    private boolean isPhoneNumberValid() {

        try {
            Phonenumber.PhoneNumber number = phoneUtil.parse(edtPhone.getText().toString(), Countrypicker.getSelectedCountryNameCode());
            if (phoneUtil.isValidNumber(number)) {
                return true;
            } else {

                return false;
            }
        } catch (NumberParseException e) {
            System.err.println("NumberParseException was thrown: " + e.toString());
            edtPhone.setError(getDockActivity().getResources().getString(R.string.enter_valid_number_error));
            return false;

        }
    }
}
