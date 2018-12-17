package com.app.salonbeaute.ui.binders;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.app.salonbeaute.R;
import com.app.salonbeaute.activities.DockActivity;
import com.app.salonbeaute.entities.SideMenuEnt;
import com.app.salonbeaute.helpers.BasePreferenceHelper;
import com.app.salonbeaute.interfaces.RecyclerClickListner;
import com.app.salonbeaute.ui.viewbinders.abstracts.RecyclerViewBinder;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyBookingsBinder extends RecyclerViewBinder<String> {

    private DockActivity dockActivity;
    private BasePreferenceHelper prefHelper;
    private ImageLoader imageLoader;
    private RecyclerClickListner clickListner;

    public MyBookingsBinder(DockActivity dockActivity, BasePreferenceHelper prefHelper, RecyclerClickListner clickListner) {
        super(R.layout.row_item_booking);
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
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListner.onClick(entity,position);
            }
        });

    }


    static class ViewHolder extends BaseViewHolder{

        @BindView(R.id.txt_month)
        AnyTextView txtMonth;
        @BindView(R.id.txt_date)
        AnyTextView txtDate;
        @BindView(R.id.txt_time)
        AnyTextView txtTime;
        @BindView(R.id.rl_calendar)
        RelativeLayout rlCalendar;
        @BindView(R.id.title)
        AnyTextView title;
        @BindView(R.id.card_view)
        CardView cardView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
