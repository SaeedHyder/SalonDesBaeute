package com.app.salonbeaute.ui.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.app.salonbeaute.R;
import com.app.salonbeaute.activities.MainActivity;
import com.app.salonbeaute.entities.ParlourServicesEnt;
import com.app.salonbeaute.fragments.PaymentDetailFragment;
import com.app.salonbeaute.helpers.UIHelper;
import com.app.salonbeaute.ui.binders.NearestParlourBinder;
import com.app.salonbeaute.ui.binders.ParlourServicesBinder;
import com.app.salonbeaute.ui.views.CustomRecyclerView;
import com.google.android.gms.vision.text.Line;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;


public class ParlourPromotionsAdapter extends PagerAdapter {
    MainActivity context;
    ArrayList<String> titles;
    private ArrayList<ParlourServicesEnt> pages;
    LayoutInflater layoutInflater;
    ImageLoader imageLoader;


    public ParlourPromotionsAdapter(MainActivity context, ArrayList<String> titles, ArrayList<ParlourServicesEnt> pages) {
        this.context = context;
        this.titles = titles;
        this.pages = pages;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = ImageLoader.getInstance();
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((NestedScrollView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.row_parlour_promotion, container, false);
        container.addView(itemView);

        CustomRecyclerView rv_services = (CustomRecyclerView) itemView.findViewById(R.id.rv_parlour_services);
        LinearLayout header = (LinearLayout) itemView.findViewById(R.id.header_layout);
        RelativeLayout rlCart = (RelativeLayout) itemView.findViewById(R.id.rl_cart);

        header.setBackground(context.getResources().getDrawable(pages.get(position).getBorder()));

        rlCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.addDockableFragment(PaymentDetailFragment.newInstance(),"PaymentDetailFragment");
            }
        });

        rv_services.BindRecyclerView(new ParlourServicesBinder(context, pages.get(position).getDot()), titles,
                new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                , new DefaultItemAnimator());

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((NestedScrollView) object);
    }
}

