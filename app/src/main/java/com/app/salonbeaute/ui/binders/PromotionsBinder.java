package com.app.salonbeaute.ui.binders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.app.salonbeaute.R;
import com.app.salonbeaute.activities.DockActivity;
import com.app.salonbeaute.helpers.BasePreferenceHelper;
import com.app.salonbeaute.ui.viewbinders.abstracts.RecyclerViewBinder;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PromotionsBinder extends RecyclerViewBinder<String> {

    private DockActivity dockActivity;
    private ImageLoader imageLoader;
    private BasePreferenceHelper preferenceHelper;

    public PromotionsBinder(DockActivity context, BasePreferenceHelper prefHelper) {
        super(R.layout.row_item_promotion);
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
        imageLoader.displayImage(entity, viewHolder.image);

    }


    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.image)
        ImageView image;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
