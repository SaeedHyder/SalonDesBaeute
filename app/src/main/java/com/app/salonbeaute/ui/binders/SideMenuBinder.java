package com.app.salonbeaute.ui.binders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.salonbeaute.R;
import com.app.salonbeaute.activities.DockActivity;
import com.app.salonbeaute.fragments.LoginFragment;
import com.app.salonbeaute.helpers.BasePreferenceHelper;
import com.app.salonbeaute.ui.viewbinders.abstracts.RecyclerViewBinder;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SideMenuBinder extends RecyclerViewBinder<String> {

    private DockActivity dockActivity;
    private BasePreferenceHelper prefHelper;
    private ImageLoader imageLoader;

    public SideMenuBinder(DockActivity dockActivity, BasePreferenceHelper prefHelper) {
        super(R.layout.row_item_sidemenu);
        this.dockActivity = dockActivity;
        this.prefHelper = prefHelper;
        this.imageLoader = ImageLoader.getInstance();
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bindView(String entity, int position, Object viewHolder, Context context) {

        final ViewHolder holder = (ViewHolder) viewHolder;

       if(position==0){
           holder.llItem.setBackground(dockActivity.getResources().getDrawable(R.drawable.active_bg));
           holder.txtItemName.setText("Home");
       }else{
           holder.llItem.setBackground(dockActivity.getResources().getDrawable(R.color.transparent));
           holder.txtItemName.setText("Home");
       }

       holder.llItem.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View view) {
               dockActivity.popBackStackTillEntry(0);
               dockActivity.replaceDockableFragment(LoginFragment.newInstance(),"LoginFragment");
               return true;
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
