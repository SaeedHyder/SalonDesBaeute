package com.app.salonbeaute.ui.binders;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.app.salonbeaute.R;
import com.app.salonbeaute.activities.DockActivity;
import com.app.salonbeaute.helpers.BasePreferenceHelper;
import com.app.salonbeaute.interfaces.RecyclerClickListner;
import com.app.salonbeaute.ui.viewbinders.abstracts.RecyclerViewBinder;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.app.salonbeaute.ui.views.CustomRatingBar;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewsDetailBinder extends RecyclerViewBinder<String> {

    private DockActivity dockActivity;
    private BasePreferenceHelper prefHelper;
    private ImageLoader imageLoader;
    private RecyclerClickListner clickListner;

    public ReviewsDetailBinder(DockActivity dockActivity, BasePreferenceHelper prefHelper, RecyclerClickListner clickListner) {
        super(R.layout.row_item_reviews_detail);
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
                clickListner.onClick(entity, position);
            }
        });

        if (position % 2 == 1) {
            holder.llImages.setVisibility(View.VISIBLE);
        } else {
            holder.llImages.setVisibility(View.GONE);
        }


    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.cv_image)
        CircleImageView cvImage;
        @BindView(R.id.txtName)
        AnyTextView txtName;
        @BindView(R.id.rbRating)
        CustomRatingBar rbRating;
        @BindView(R.id.view)
        View view;
        @BindView(R.id.txt_date)
        AnyTextView txtDate;
        @BindView(R.id.txtDetail)
        AnyTextView txtDetail;
        @BindView(R.id.ll_images)
        LinearLayout llImages;
        @BindView(R.id.ll_data)
        LinearLayout llData;
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
