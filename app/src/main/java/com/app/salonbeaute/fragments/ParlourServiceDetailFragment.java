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
import com.app.salonbeaute.ui.binders.ServiceDetailBinder;
import com.app.salonbeaute.ui.views.AnyEditTextView;
import com.app.salonbeaute.ui.views.CustomRecyclerView;
import com.app.salonbeaute.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ParlourServiceDetailFragment extends BaseFragment implements RecyclerClickListner {
    @BindView(R.id.edtFilterBox)
    AnyEditTextView edtFilterBox;
    @BindView(R.id.btn_filter)
    ImageView btnFilter;
    @BindView(R.id.card_view)
    CardView cardView;
    @BindView(R.id.rl_service_detail)
    CustomRecyclerView rlServiceDetail;
    Unbinder unbinder;

    private ArrayList<String> collection;

    public static ParlourServiceDetailFragment newInstance() {
        Bundle args = new Bundle();

        ParlourServiceDetailFragment fragment = new ParlourServiceDetailFragment();
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
        View view = inflater.inflate(R.layout.fragment_parlour_service_detail, container, false);
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

        rlServiceDetail.BindRecyclerView(new ServiceDetailBinder(getDockActivity(), prefHelper,this), collection,
                new LinearLayoutManager(getDockActivity(), LinearLayoutManager.VERTICAL, false)
                , new DefaultItemAnimator());
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading("Hair");
        titleBar.showSearchButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.will_be_implemented));
            }
        });
    }


    @OnClick(R.id.btn_filter)
    public void onViewClicked() {
        UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.will_be_implemented));
    }

    @Override
    public void onClick(Object entity, int position) {

    }
}
