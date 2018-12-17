package com.app.salonbeaute.fragments;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.salonbeaute.R;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.helpers.UIHelper;
import com.app.salonbeaute.interfaces.RecyclerClickListner;
import com.app.salonbeaute.ui.binders.ListViewBinder;
import com.app.salonbeaute.ui.views.AnyEditTextView;
import com.app.salonbeaute.ui.views.CustomRecyclerView;
import com.app.salonbeaute.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ListViewFragment extends BaseFragment implements RecyclerClickListner {
    @BindView(R.id.edtSearchBox)
    AnyEditTextView edtSearchBox;
    @BindView(R.id.btn_filter)
    ImageView btnFilter;
    @BindView(R.id.card_view)
    CardView cardView;
    @BindView(R.id.rl_listview)
    CustomRecyclerView rvListview;
    Unbinder unbinder;

    private ArrayList<String> collection;

    public static ListViewFragment newInstance() {
        Bundle args = new Bundle();

        ListViewFragment fragment = new ListViewFragment();
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
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);
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
        collection.add("");
        collection.add("");
        collection.add("");
        collection.add("");
        collection.add("");
        collection.add("");
        collection.add("");
        collection.add("");
        collection.add("");

        rvListview.BindRecyclerView(new ListViewBinder(getDockActivity(), prefHelper,this), collection,
                new LinearLayoutManager(getDockActivity(), LinearLayoutManager.VERTICAL, false)
                , new DefaultItemAnimator());
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideTitleBar();
    }



    @OnClick({R.id.btn_filter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_filter:
                getDockActivity().replaceDockableFragment(FilterFragment.newInstance(),"FilterFragment");
                break;

        }
    }

    @Override
    public void onClick(Object entity, int position) {
        getDockActivity().replaceDockableFragment(ParlourDetailFragement.newInstance(),"ParlourDetailFragement");
    }
}
