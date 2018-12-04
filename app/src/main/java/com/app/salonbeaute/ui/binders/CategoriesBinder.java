package com.app.salonbeaute.ui.binders;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;

import com.app.salonbeaute.R;
import com.app.salonbeaute.activities.DockActivity;
import com.app.salonbeaute.entities.ServicesEnt;
import com.app.salonbeaute.helpers.BasePreferenceHelper;
import com.app.salonbeaute.interfaces.RecyclerClickListner;
import com.app.salonbeaute.ui.viewbinders.abstracts.RecyclerViewBinder;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CategoriesBinder extends RecyclerViewBinder<ServicesEnt> {

    private DockActivity dockActivity;
    private BasePreferenceHelper prefHelper;
    private ImageLoader imageLoader;
    private RecyclerClickListner clickListner;
    private int Pos = 0;

    public CategoriesBinder(DockActivity dockActivity, BasePreferenceHelper prefHelper, RecyclerClickListner clickListner, int Pos) {
        super(R.layout.row_item_categories);
        this.dockActivity = dockActivity;
        this.prefHelper = prefHelper;
        this.imageLoader = ImageLoader.getInstance();
        this.clickListner = clickListner;
        this.Pos = Pos;
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bindView(ServicesEnt entity, int position, Object viewHolder, Context context) {

        final ViewHolder holder = (ViewHolder) viewHolder;


        //   if(entity.isSelect()){
        if (position == Pos) {
            holder.txtText.setTextColor(dockActivity.getResources().getColor(R.color.app_red_dark));
            holder.view.setVisibility(View.VISIBLE);
        } else {
            holder.txtText.setTextColor(dockActivity.getResources().getColor(R.color.text_color));
            holder.view.setVisibility(View.GONE);
        }

        holder.txtText.setText(entity.getTitle());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListner.onClick(entity, position);

            }
        });

    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.txt_text)
        AnyTextView txtText;
        @BindView(R.id.view)
        View view;
        @BindView(R.id.card_view)
        CardView cardView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
