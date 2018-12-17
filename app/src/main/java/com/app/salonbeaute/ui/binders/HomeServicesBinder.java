package com.app.salonbeaute.ui.binders;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.app.salonbeaute.R;
import com.app.salonbeaute.activities.DockActivity;
import com.app.salonbeaute.entities.ServicesEnt;
import com.app.salonbeaute.helpers.BasePreferenceHelper;
import com.app.salonbeaute.interfaces.RecyclerClickListner;
import com.app.salonbeaute.ui.viewbinders.abstracts.ViewBinder;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeServicesBinder extends ViewBinder<ServicesEnt> {

    private DockActivity dockActivity;
    private BasePreferenceHelper prefHelper;
    private ImageLoader imageLoader;
    private RecyclerClickListner clickListner;


    public HomeServicesBinder(DockActivity dockActivity, BasePreferenceHelper prefHelper, RecyclerClickListner clickListner) {
        super(R.layout.row_item_service);
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
    public void bindView(final ServicesEnt entity, final int position, int grpPosition, View view, Activity activity) {

        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.txtTitle.setText(entity.getTitle() + "");
        viewHolder.txtDetail.setText(entity.getDetail() + "");
        viewHolder.tvSmallRadiusColor.setImageResource(entity.getBorder());
        viewHolder.ivIcon.setImageResource(entity.getImage());

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListner.onClick(entity, position);
            }
        });

    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.tvSmallRadiusColor)
        ImageView tvSmallRadiusColor;
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.txt_title)
        AnyTextView txtTitle;
        @BindView(R.id.txt_detail)
        AnyTextView txtDetail;
        @BindView(R.id.card_view)
        CardView cardView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
