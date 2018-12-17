package com.app.salonbeaute.ui.binders;

import android.content.Context;
import android.view.View;
import android.widget.RadioButton;

import com.app.salonbeaute.R;
import com.app.salonbeaute.activities.DockActivity;
import com.app.salonbeaute.helpers.BasePreferenceHelper;
import com.app.salonbeaute.interfaces.RecyclerClickListner;
import com.app.salonbeaute.ui.viewbinders.abstracts.RecyclerViewBinder;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TimingBinder extends RecyclerViewBinder<String> {

    private DockActivity dockActivity;
    private BasePreferenceHelper prefHelper;
    private ImageLoader imageLoader;
    private RecyclerClickListner recyclerClickListner;
    private int pos=0;



    public TimingBinder(DockActivity dockActivity, BasePreferenceHelper prefHelper, RecyclerClickListner recyclerClickListner,int pos) {
        super(R.layout.row_item_timing);
        this.dockActivity = dockActivity;
        this.prefHelper = prefHelper;
        this.imageLoader = ImageLoader.getInstance();
        this.recyclerClickListner = recyclerClickListner;
        this.pos=pos;
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bindView(String entity, int position, Object viewHolder, Context context) {

        final ViewHolder holder = (ViewHolder) viewHolder;

        if (position == pos) {
            holder.txtTime.setText("10:00 am");
            holder.txtTime.setBackground(dockActivity.getResources().getDrawable(R.drawable.rounded_blue_button));
            holder.txtTime.setTextColor(dockActivity.getResources().getColor(R.color.white));
        } else {
            holder.txtTime.setText("11:00 am");
            holder.txtTime.setBackground(dockActivity.getResources().getDrawable(R.drawable.rounded_blue_button_border));
            holder.txtTime.setTextColor(dockActivity.getResources().getColor(R.color.timing_button_color));
        }

        holder.txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos=position;
                recyclerClickListner.onClick(entity,position);
            }
        });

    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.txt_time)
        AnyTextView txtTime;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
