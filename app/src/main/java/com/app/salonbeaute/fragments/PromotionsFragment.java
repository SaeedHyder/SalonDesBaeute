package com.app.salonbeaute.fragments;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.salonbeaute.R;
import com.app.salonbeaute.fragments.abstracts.BaseFragment;
import com.app.salonbeaute.helpers.UIHelper;
import com.app.salonbeaute.ui.adapters.PromotionAdapter;
import com.app.salonbeaute.ui.binders.NearestParlourBinder;
import com.app.salonbeaute.ui.binders.PromotionsBinder;
import com.app.salonbeaute.ui.views.CustomRecyclerView;
import com.app.salonbeaute.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.relex.circleindicator.CircleIndicator;

public class PromotionsFragment extends BaseFragment {
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.btnSearch)
    ImageView btnSearch;
    @BindView(R.id.viewpagerPromotions)
    ViewPager viewPager;
    @BindView(R.id.indicator)
    CircleIndicator indicator;
    Unbinder unbinder;
    @BindView(R.id.rv_promotionsList)
    CustomRecyclerView rvPromotionsList;

    private ArrayList<String> imagesCollection;
    private ArrayList<String> listCollection;

    private PromotionAdapter promotionAdapter;

    public static PromotionsFragment newInstance() {
        Bundle args = new Bundle();

        PromotionsFragment fragment = new PromotionsFragment();
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
        View view = inflater.inflate(R.layout.fragment_promotions, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setViewPager();
        setListData();
    }

    private void setListData() {
       /* if (entity.size() <= 0) {
            txtNoresult.setVisibility(View.VISIBLE);
            lvFitnessClasses.setVisibility(View.GONE);
        } else {
            txtNoresult.setVisibility(View.GONE);
            lvFitnessClasses.setVisibility(View.VISIBLE);
        }*/
        listCollection = new ArrayList<>();
        listCollection.add("drawable://" + R.drawable.hair_image_bg);
        listCollection.add("drawable://" + R.drawable.spa_image_bg);
        listCollection.add("drawable://" + R.drawable.nails_image_bg);
        listCollection.add("drawable://" + R.drawable.barber_image_bg);
        listCollection.add("drawable://" + R.drawable.aesthetics_image_bg);


        rvPromotionsList.BindRecyclerView(new PromotionsBinder(getDockActivity(), prefHelper), listCollection,
                new LinearLayoutManager(getDockActivity(), LinearLayoutManager.VERTICAL, false)
                , new DefaultItemAnimator());
    }

    private void setViewPager() {

        imagesCollection = new ArrayList<>();
        imagesCollection.add("drawable://" + R.drawable.background1);
        imagesCollection.add("drawable://" + R.drawable.background3);
        imagesCollection.add("drawable://" + R.drawable.background2);
        imagesCollection.add("drawable://" + R.drawable.background5);
        imagesCollection.add("drawable://" + R.drawable.background6);
        imagesCollection.add("drawable://" + R.drawable.background3);

        promotionAdapter = new PromotionAdapter(getMainActivity(), imagesCollection);
        viewPager.setAdapter(promotionAdapter);
        indicator.setViewPager(viewPager);
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideTitleBar();
    }

    @OnClick({R.id.btnBack, R.id.btnSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                getDockActivity().popFragment();
                break;
            case R.id.btnSearch:
                UIHelper.showShortToastInDialoge(getDockActivity(), getResString(R.string.will_be_implemented));
                break;
        }
    }

}
