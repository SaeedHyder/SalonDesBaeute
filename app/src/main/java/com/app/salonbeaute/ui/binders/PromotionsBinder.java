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


public class PromotionsBinder extends RecyclerViewBinder<String> {

    private DockActivity dockActivity;
    private ImageLoader imageLoader;
    private BasePreferenceHelper preferenceHelper;
    private RecyclerClickListner clickListner;

    public PromotionsBinder(DockActivity context, BasePreferenceHelper prefHelper, RecyclerClickListner clickListner) {
        super(R.layout.row_item_promotion);
        this.dockActivity = context;
        this.preferenceHelper = prefHelper;
        imageLoader = ImageLoader.getInstance();
        this.clickListner = clickListner;
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    public void bindView(String entity, int position, Object holder, Context context) {

        ViewHolder viewHolder = (ViewHolder) holder;
        imageLoader.displayImage(entity, viewHolder.image);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListner.onClick(entity, position);
            }
        });
    }


    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.txt_text)
        AnyTextView txtText;
        @BindView(R.id.card_view)
        CardView cardView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
