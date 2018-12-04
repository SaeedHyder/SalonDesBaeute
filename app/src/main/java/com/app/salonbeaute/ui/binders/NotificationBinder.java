package com.app.salonbeaute.ui.binders;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.salonbeaute.R;
import com.app.salonbeaute.activities.DockActivity;
import com.app.salonbeaute.helpers.BasePreferenceHelper;
import com.app.salonbeaute.ui.viewbinders.abstracts.RecyclerViewBinder;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationBinder extends RecyclerViewBinder<String> {

    private DockActivity dockActivity;
    private ImageLoader imageLoader;
    private BasePreferenceHelper preferenceHelper;

    public NotificationBinder(DockActivity context, BasePreferenceHelper prefHelper) {
        super(R.layout.fragment_notification_item);
        this.dockActivity = context;
        this.preferenceHelper = prefHelper;
        imageLoader = ImageLoader.getInstance();
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    public void bindView(String entity, int position, Object holder, Context context) {

        ViewHolder viewHolder = (ViewHolder) holder;
        imageLoader.displayImage("drawable://" + R.drawable.img_01, viewHolder.ivImage);

        if (position % 2 == 1) {
            viewHolder.viewColor.setBackgroundColor(context.getResources().getColor(R.color.app_red));
        } else {
            viewHolder.viewColor.setBackgroundColor(context.getResources().getColor(R.color.blue_view));
        }
    }

    static class ViewHolder extends BaseViewHolder{
        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.cv_image)
        CardView cvImage;
        @BindView(R.id.txtTitle)
        AnyTextView txtTitle;
        @BindView(R.id.txtDetail)
        AnyTextView txtDetail;
        @BindView(R.id.placedOn)
        AnyTextView placedOn;
        @BindView(R.id.txtPlacedOn)
        AnyTextView txtPlacedOn;
        @BindView(R.id.date)
        AnyTextView date;
        @BindView(R.id.txtDate)
        AnyTextView txtDate;
        @BindView(R.id.ll_data)
        LinearLayout llData;
        @BindView(R.id.card_view)
        CardView cardView;
        @BindView(R.id.viewColor)
        View viewColor;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
