package com.app.salonbeaute.helpers;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.app.salonbeaute.R;
import com.app.salonbeaute.ui.views.AnyEditTextView;
import com.app.salonbeaute.ui.views.AnyTextView;
import com.app.salonbeaute.ui.views.CustomRatingBar;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created on 5/24/2017.
 */

public class DialogHelper {
    private Dialog dialog;
    private Context context;
    private ImageLoader imageLoader;
    private RadioGroup rg;


    public DialogHelper(Context context) {
        this.context = context;
        this.dialog = new Dialog(context);
    }


    public Dialog toastDialoge(String text) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.dialog.setContentView(R.layout.toast_dialoge);
        AnyTextView textView = (AnyTextView) dialog.findViewById(R.id.txt_text);
        textView.setText(text);
        return this.dialog;
    }

    public Dialog forgotPassDialoge(View.OnClickListener listener, String heading, String text, int image) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.dialog.setContentView(R.layout.dialge_forgot_password);
        Button btn = (Button) dialog.findViewById(R.id.bn_back);
        btn.setOnClickListener(listener);
        AnyTextView textView = (AnyTextView) dialog.findViewById(R.id.txt_text);
        AnyTextView txtHeading = (AnyTextView) dialog.findViewById(R.id.txt_heading);
        ImageView imageIcon = (ImageView) dialog.findViewById(R.id.image);
        txtHeading.setText(heading);
        textView.setText(text);
        imageIcon.setImageResource(image);
        return this.dialog;
    }

    public Dialog initlogout( View.OnClickListener yesListner, View.OnClickListener noListner) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.dialog.setContentView(R.layout.dialoge_logout);
        Button btnYes = (Button) dialog.findViewById(R.id.btn_yes);
        btnYes.setOnClickListener(yesListner);
        Button btnNo = (Button) dialog.findViewById(R.id.btn_No);
        btnNo.setOnClickListener(noListner);
        return this.dialog;
    }


    public void showDialog() {

        dialog.show();
    }

    public void setCancelable(boolean isCancelable) {
        dialog.setCancelable(isCancelable);
        dialog.setCanceledOnTouchOutside(isCancelable);
    }

    public void hideDialog() {
        dialog.dismiss();
    }
}
