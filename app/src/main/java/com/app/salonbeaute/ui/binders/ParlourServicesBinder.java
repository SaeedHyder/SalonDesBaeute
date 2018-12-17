package com.app.salonbeaute.ui.binders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.app.salonbeaute.R;
import com.app.salonbeaute.activities.DockActivity;
import com.app.salonbeaute.helpers.BasePreferenceHelper;
import com.app.salonbeaute.ui.viewbinders.abstracts.RecyclerViewBinder;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ParlourServicesBinder extends RecyclerViewBinder<String> {

    private DockActivity dockActivity;
    private ImageLoader imageLoader;
    private BasePreferenceHelper preferenceHelper;
    private int dotDrawable;

    public ParlourServicesBinder(DockActivity context,int dotDrawable) {
        super(R.layout.row_item_parlour_services);
        this.dockActivity = context;
        this.dotDrawable=dotDrawable;
        imageLoader = ImageLoader.getInstance();
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    public void bindView(String entity, int position, Object holder, Context context) {

        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.txtService.setText(entity);
        viewHolder.dot.setImageResource(dotDrawable);

    }

    static class ViewHolder extends BaseViewHolder{
        @BindView(R.id.txt_service)
        AnyTextView txtService;
        @BindView(R.id.dot)
        ImageView dot;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
