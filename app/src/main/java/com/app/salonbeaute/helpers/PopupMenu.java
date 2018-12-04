package com.app.salonbeaute.helpers;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.app.salonbeaute.R;
import com.app.salonbeaute.activities.DockActivity;
import com.app.salonbeaute.activities.MainActivity;
import com.app.salonbeaute.ui.views.AnyTextView;

public class PopupMenu {

    private Context context;

    public void initMenu(Context context, View v) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.popup_window_dialoge, null);
        final DialogHelper dialogHelper = new DialogHelper(context);

        AnyTextView editBtn = (AnyTextView) customView.findViewById(R.id.btn_edit);
        AnyTextView deleteBtn = (AnyTextView) customView.findViewById(R.id.btn_delete);

        final PopupWindow popupWindow = new PopupWindow(customView, (int) context.getResources().getDimension(R.dimen.x130), LinearLayout.LayoutParams.WRAP_CONTENT);
        //  popupWindow.showAtLocation(viewHolder.llMainframe, Gravity.CENTER, 0, 0);

        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);

        popupWindow.showAsDropDown(v);


        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIHelper.showShortToastInDialoge(context, context.getResources().getString(R.string.will_be_implemented));
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIHelper.showShortToastInDialoge(context, context.getResources().getString(R.string.will_be_implemented));
            }
        });


    }


}
