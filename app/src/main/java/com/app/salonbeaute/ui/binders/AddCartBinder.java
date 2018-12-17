package com.app.salonbeaute.ui.binders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.app.salonbeaute.R;
import com.app.salonbeaute.activities.DockActivity;
import com.app.salonbeaute.helpers.BasePreferenceHelper;
import com.app.salonbeaute.helpers.UIHelper;
import com.app.salonbeaute.interfaces.RecyclerClickListner;
import com.app.salonbeaute.ui.viewbinders.abstracts.RecyclerViewBinder;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddCartBinder extends RecyclerViewBinder<String> {

    private DockActivity dockActivity;
    private BasePreferenceHelper prefHelper;
    private ImageLoader imageLoader;
    private RecyclerClickListner recyclerClickListner;

    public AddCartBinder(DockActivity dockActivity, BasePreferenceHelper prefHelper, RecyclerClickListner recyclerClickListner) {
        super(R.layout.row_item_add_cart);
        this.dockActivity = dockActivity;
        this.prefHelper = prefHelper;
        this.imageLoader = ImageLoader.getInstance();
        this.recyclerClickListner = recyclerClickListner;
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bindView(String entity, int position, Object viewHolder, Context context) {

        final ViewHolder holder = (ViewHolder) viewHolder;

        if (position == 0) {

            holder.icon.setImageResource(R.drawable.plus_active_icon);

        } else if (position == 2) {

            holder.icon.setImageResource(R.drawable.minus_down_icon);

        } else {

            holder.icon.setImageResource(R.drawable.plus_down_icon);
        }

        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIHelper.showShortToastInDialoge(dockActivity, dockActivity.getResources().getString(R.string.will_be_implemented));
            }
        });

        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIHelper.showShortToastInDialoge(dockActivity, dockActivity.getResources().getString(R.string.will_be_implemented));
            }
        });

    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.txt_salon_title)
        AnyTextView txtSalonTitle;
        @BindView(R.id.txt_salon_detai)
        AnyTextView txtSalonDetai;
        @BindView(R.id.txt_price)
        AnyTextView txtPrice;
        @BindView(R.id.icon)
        ImageView icon;
        @BindView(R.id.info)
        ImageView info;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
