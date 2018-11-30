package com.app.salonbeaute.ui.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.app.salonbeaute.R;
import com.app.salonbeaute.activities.MainActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;


public class TutorialAdapter extends PagerAdapter {
    MainActivity context;
    ArrayList<String> images;
    LayoutInflater layoutInflater;
    ImageLoader imageLoader;


    public TutorialAdapter(MainActivity context, ArrayList<String> images) {
        this.context = context;
        this.images = images;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = ImageLoader.getInstance();
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.tutorail_row_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        imageLoader.displayImage(images.get(position), imageView);
        container.addView(itemView);

       /* imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "you clicked image " + (position + 1), Toast.LENGTH_LONG).show();
            }
        });*/

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
