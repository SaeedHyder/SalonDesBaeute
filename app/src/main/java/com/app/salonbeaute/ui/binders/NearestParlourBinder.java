package com.app.salonbeaute.ui.binders;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.app.salonbeaute.R;
import com.app.salonbeaute.activities.DockActivity;
import com.app.salonbeaute.helpers.BasePreferenceHelper;
import com.app.salonbeaute.interfaces.RecyclerClickListner;
import com.app.salonbeaute.ui.viewbinders.abstracts.RecyclerViewBinder;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NearestParlourBinder extends RecyclerViewBinder<String> {

    private DockActivity dockActivity;
    private BasePreferenceHelper prefHelper;
    private ImageLoader imageLoader;
    private RecyclerClickListner recyclerClickListner;

    public NearestParlourBinder(DockActivity dockActivity, BasePreferenceHelper prefHelper,RecyclerClickListner recyclerClickListner) {
        super(R.layout.row_item_nearest_parlours);
        this.dockActivity = dockActivity;
        this.prefHelper = prefHelper;
        this.imageLoader = ImageLoader.getInstance();
        this.recyclerClickListner=recyclerClickListner;
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bindView(String entity, int position, Object viewHolder, Context context) {

        final ViewHolder holder = (ViewHolder) viewHolder;

        imageLoader.displayImage(entity,holder.parlourImage);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerClickListner.onClick(entity,position);
            }
        });
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.parlour_image)
        ImageView parlourImage;
        @BindView(R.id.txt_title)
        AnyTextView txtTitle;
        @BindView(R.id.txt_distance)
        AnyTextView txtDistance;
        @BindView(R.id.card_view)
        CardView cardView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
