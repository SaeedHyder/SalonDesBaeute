package com.app.salonbeaute.ui.binders;

import android.content.Context;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.app.salonbeaute.R;
import com.app.salonbeaute.activities.DockActivity;
import com.app.salonbeaute.helpers.BasePreferenceHelper;
import com.app.salonbeaute.interfaces.RecyclerClickListner;
import com.app.salonbeaute.ui.viewbinders.abstracts.RecyclerViewBinder;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class ChangeUserBinder extends RecyclerViewBinder<String> {

    private DockActivity dockActivity;
    private BasePreferenceHelper prefHelper;
    private ImageLoader imageLoader;
    private RecyclerClickListner clickListner;
    private RadioButton selected=null;

    public ChangeUserBinder(DockActivity dockActivity) {
        super(R.layout.row_item_change_user);
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

        holder.radioButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(selected != null)
                {
                    selected.setChecked(false);
                }

                holder.radioButton.setChecked(true);
                selected = holder.radioButton;
            }
        });
        holder.llMainFrame.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(selected != null)
                {
                    selected.setChecked(false);
                }

                holder.radioButton.setChecked(true);
                selected = holder.radioButton;
            }
        });



    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.image)
        CircleImageView image;
        @BindView(R.id.name)
        AnyTextView name;
        @BindView(R.id.radioButton)
        RadioButton radioButton;
        @BindView(R.id.ll_mainFrame)
        RelativeLayout llMainFrame;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

