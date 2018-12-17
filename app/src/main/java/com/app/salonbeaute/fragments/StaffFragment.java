package com.app.salonbeaute.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.salonbeaute.R;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.interfaces.RecyclerClickListner;
import com.app.salonbeaute.ui.views.CustomRecyclerView;
import com.app.salonbeaute.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StaffFragment extends BaseFragment implements RecyclerClickListner {

    @BindView(R.id.rv_staff)
    CustomRecyclerView rvStaff;
    Unbinder unbinder;
    private ArrayList<String> staffCollection;

    public static StaffFragment newInstance() {
        Bundle args = new Bundle();

        StaffFragment fragment = new StaffFragment();
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
        View view = inflater.inflate(R.layout.fragment_staff, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setStaffData();
    }

    private void setStaffData() {

        staffCollection = new ArrayList<>();
        staffCollection.add("");
        staffCollection.add("");
        staffCollection.add("");
        staffCollection.add("");
        staffCollection.add("");
        staffCollection.add("");
        staffCollection.add("");

       /* rvStaff.BindRecyclerView(new ChangeUserBinder(getDockActivity(), prefHelper, this), staffCollection,
                new LinearLayoutManager(getDockActivity(), LinearLayoutManager.VERTICAL, false)
                , new DefaultItemAnimator());*/


    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getResString(R.string.staff));
    }



    @Override
    public void onClick(Object entity, int position) {

    }
}
