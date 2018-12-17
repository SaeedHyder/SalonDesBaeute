package com.app.salonbeaute.fragments;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.salonbeaute.R;
import com.app.salonbeaute.entities.SideMenuEnt;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.global.AppConstants;
import com.app.salonbeaute.helpers.DialogHelper;
import com.app.salonbeaute.interfaces.RecyclerClickListner;
import com.app.salonbeaute.ui.binders.SideMenuBinder;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.app.salonbeaute.ui.views.CustomRecyclerView;
import com.app.salonbeaute.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class SideMenuFragment extends BaseFragment implements RecyclerClickListner {

    @BindView(R.id.SideMenu)
    CustomRecyclerView SideMenu;
    Unbinder unbinder;
    @BindView(R.id.profileImage)
    CircleImageView profileImage;
    @BindView(R.id.full_name)
    AnyTextView fullName;
    @BindView(R.id.view_profile)
    AnyTextView viewProfile;

    private ArrayList<SideMenuEnt> collection;
    private long mLastClickTime = 0;

    public static SideMenuFragment newInstance() {
        return new SideMenuFragment();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sidemenu, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setData();

    }

    private void setData() {

        collection = new ArrayList<>();
        collection.add(new SideMenuEnt(AppConstants.Home, R.drawable.home_icon));
        collection.add(new SideMenuEnt(AppConstants.AboutSalonDesBeaute, R.drawable.user_icon));
        collection.add(new SideMenuEnt(AppConstants.ContactUs, R.drawable.contact_icon1));
        collection.add(new SideMenuEnt(AppConstants.ParlourServices, R.drawable.parlor_services));
        collection.add(new SideMenuEnt(AppConstants.MyBookings, R.drawable.booking_icon));
        collection.add(new SideMenuEnt(AppConstants.Promotions, R.drawable.promotion_icon));
        collection.add(new SideMenuEnt(AppConstants.NearBy, R.drawable.nearby_icon));
        collection.add(new SideMenuEnt(AppConstants.Reviews, R.drawable.reviews_icon));
        collection.add(new SideMenuEnt(AppConstants.Notifications, R.drawable.notification_icon));
        collection.add(new SideMenuEnt(AppConstants.Settings, R.drawable.setting_icon));
        collection.add(new SideMenuEnt(AppConstants.SignOut, R.drawable.ssignout_icon));


        SideMenu.BindRecyclerView(new SideMenuBinder(getDockActivity(), prefHelper, this), collection,
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

      /*  if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }*/

        SideMenuEnt ent = (SideMenuEnt) entity;

        if (ent.getTitle().equals(AppConstants.Home)) {
            getMainActivity().closeDrawer();

        } else if (ent.getTitle().equals(AppConstants.AboutSalonDesBeaute)) {

            getDockActivity().replaceDockableFragment(AboutUsFragment.newInstance(), "AboutUsFragment");

        } else if (ent.getTitle().equals(AppConstants.ContactUs)) {
            getDockActivity().replaceDockableFragment(ContactUsFragment.newInstance(), "ContactUsFragment");

        }else if (ent.getTitle().equals(AppConstants.ParlourServices)) {
            getDockActivity().replaceDockableFragment(ParlourServicesFragment.newInstance(), "ParlourServicesFragment");

        } else if (ent.getTitle().equals(AppConstants.MyBookings)) {
            getDockActivity().replaceDockableFragment(MyBookingsFragment.newInstance(), "MyBookingsFragment");

        } else if (ent.getTitle().equals(AppConstants.Promotions)) {
            getDockActivity().replaceDockableFragment(PromotionsFragment.newInstance(), "PromotionsFragment");

        } else if (ent.getTitle().equals(AppConstants.NearBy)) {
            getDockActivity().replaceDockableFragment(NearByFragment.newInstance(), "NearByFragment");

        } else if (ent.getTitle().equals(AppConstants.Reviews)) {
            getDockActivity().replaceDockableFragment(ReviewsFragment.newInstance(), "ReviewsFragment");

        }  else if (ent.getTitle().equals(AppConstants.Notifications)) {
            getDockActivity().replaceDockableFragment(NotificationsFragment.newInstance(), "NotificationsFragment");

        } else if (ent.getTitle().equals(AppConstants.Settings)) {
            getDockActivity().replaceDockableFragment(SettingFragment.newInstance(), "SettingFragment");

        } else if (ent.getTitle().equals(AppConstants.SignOut)) {
            DialogHelper dialoge = new DialogHelper(getDockActivity());
            dialoge.initlogout(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getDockActivity().popBackStackTillEntry(0);
                    prefHelper.setLoginStatus(false);
                    getDockActivity().replaceDockableFragment(LoginFragment.newInstance(), "LoginFragment");
                    dialoge.hideDialog();
                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialoge.hideDialog();
                }
            });
            dialoge.showDialog();
        }
    }

    @OnClick(R.id.view_profile)
    public void onViewClicked() {
        getDockActivity().replaceDockableFragment(ProfileFragment.newInstance(), "ProfileFragment");
    }
}
