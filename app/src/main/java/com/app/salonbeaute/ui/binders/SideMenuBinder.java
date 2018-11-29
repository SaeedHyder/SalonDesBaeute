package com.app.salonbeaute.ui.binders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

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


public class SideMenuBinder extends RecyclerViewBinder<SideMenuEnt> {

    private DockActivity dockActivity;
    private BasePreferenceHelper prefHelper;
    private ImageLoader imageLoader;
    private RecyclerClickListner clickListner;

    public SideMenuBinder(DockActivity dockActivity, BasePreferenceHelper prefHelper, RecyclerClickListner clickListner) {
        super(R.layout.row_item_sidemenu);
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
    public void bindView(SideMenuEnt entity, int position, Object viewHolder, Context context) {

        final ViewHolder holder = (ViewHolder) viewHolder;
        if (position == 0) {
            holder.llItem.setBackground(dockActivity.getResources().getDrawable(R.drawable.active_bg));
        } else {
            holder.llItem.setBackground(dockActivity.getResources().getDrawable(R.color.transparent));
        }

        holder.txtItemName.setText(entity.getTitle());
        holder.icon.setImageResource(entity.getImage());

        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListner.onClick(entity, position);
            }
        });


    }


    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.icon)
        ImageView icon;
        @BindView(R.id.txt_item_name)
        AnyTextView txtItemName;
        @BindView(R.id.ll_item)
        LinearLayout llItem;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
