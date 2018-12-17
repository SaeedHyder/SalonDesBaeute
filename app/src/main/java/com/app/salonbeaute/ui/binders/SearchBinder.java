package com.app.salonbeaute.ui.binders;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.app.salonbeaute.R;
import com.app.salonbeaute.activities.DockActivity;
import com.app.salonbeaute.helpers.BasePreferenceHelper;
import com.app.salonbeaute.interfaces.RecyclerClickListner;
import com.app.salonbeaute.ui.adapters.PromotionAdapter;
import com.app.salonbeaute.ui.viewbinders.abstracts.RecyclerViewBinder;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.app.salonbeaute.ui.views.CustomRatingBar;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;


public class SearchBinder extends RecyclerViewBinder<String> {

    private DockActivity dockActivity;
    private BasePreferenceHelper prefHelper;
    private ImageLoader imageLoader;
    private RecyclerClickListner clickListner;

    private ArrayList<String> imagesCollection;
    private PromotionAdapter promotionAdapter;

    public SearchBinder(DockActivity dockActivity, BasePreferenceHelper prefHelper, RecyclerClickListner clickListner) {
        super(R.layout.row_item_search);
        this.dockActivity = dockActivity;
        this.prefHelper = prefHelper;
        this.imageLoader = ImageLoader.getInstance();
        this.clickListner = clickListner;
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bindView(String entity, int position, Object viewHolder, Context context) {

        final ViewHolder holder = (ViewHolder) viewHolder;

        setViewPager(holder,entity,position);
    }

    private void setViewPager(ViewHolder holder, String entity, int position) {

        imagesCollection = new ArrayList<>();
        imagesCollection.add("drawable://" + R.drawable.salon_slider_bg);
        imagesCollection.add("drawable://" + R.drawable.background3);
        imagesCollection.add("drawable://" + R.drawable.background2);
        imagesCollection.add("drawable://" + R.drawable.background5);
        imagesCollection.add("drawable://" + R.drawable.background6);
        imagesCollection.add("drawable://" + R.drawable.background3);

        promotionAdapter = new PromotionAdapter(dockActivity, imagesCollection);
        holder.viewPager.setAdapter(promotionAdapter);
        holder.indicator.setViewPager(holder.viewPager);

        holder.llMainFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListner.onClick(entity,position);
            }
        });
    }


    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.viewPager)
        ViewPager viewPager;
        @BindView(R.id.indicator)
        CircleIndicator indicator;
        @BindView(R.id.containerImages)
        RelativeLayout containerImages;
        @BindView(R.id.title)
        AnyTextView title;
        @BindView(R.id.txtDistance)
        AnyTextView txtDistance;
        @BindView(R.id.txt_address)
        AnyTextView txtAddress;
        @BindView(R.id.ll_data)
        LinearLayout llData;
        @BindView(R.id.txtServiceType)
        AnyTextView txtServiceType;
        @BindView(R.id.rbParlourRating)
        CustomRatingBar rbParlourRating;
        @BindView(R.id.ll_rating)
        LinearLayout llRating;
        @BindView(R.id.ll_mainFrame)
        LinearLayout llMainFrame;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
