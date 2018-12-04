package com.app.salonbeaute.ui.binders;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.app.salonbeaute.R;
import com.app.salonbeaute.activities.DockActivity;
import com.app.salonbeaute.helpers.BasePreferenceHelper;
import com.app.salonbeaute.helpers.PopupMenu;
import com.app.salonbeaute.interfaces.RecyclerClickListner;
import com.app.salonbeaute.ui.viewbinders.abstracts.RecyclerViewBinder;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.app.salonbeaute.ui.views.CustomRatingBar;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ServiceDetailBinder extends RecyclerViewBinder<String> {

    private DockActivity dockActivity;
    private BasePreferenceHelper prefHelper;
    private ImageLoader imageLoader;
    private RecyclerClickListner clickListner;

    public ServiceDetailBinder(DockActivity dockActivity, BasePreferenceHelper prefHelper, RecyclerClickListner clickListner) {
        super(R.layout.row_item_service_detail);
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


    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.cv_image)
        CardView cvImage;
        @BindView(R.id.txtTitle)
        AnyTextView txtTitle;
        @BindView(R.id.txtDetail)
        AnyTextView txtDetail;
        @BindView(R.id.txtPrice)
        AnyTextView txtPrice;
        @BindView(R.id.ll_data)
        LinearLayout llData;
        @BindView(R.id.txtAddress)
        AnyTextView txtAddress;
        @BindView(R.id.rbRating)
        CustomRatingBar rbRating;
        @BindView(R.id.txt_rating)
        AnyTextView txtRating;
        @BindView(R.id.view)
        View view;
        @BindView(R.id.txt_distance)
        AnyTextView txtDistance;
        @BindView(R.id.rl_item)
        RelativeLayout rlItem;
        @BindView(R.id.card_view)
        CardView cardView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
