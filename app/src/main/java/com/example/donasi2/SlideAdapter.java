package com.example.donasi2;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SlideAdapter(Context context){
        this.context = context;
    }

    public int[] slideimages = {
            R.drawable.timer,
            R.drawable.smartphone,
            R.drawable.kalendar,
    };

    public String[] slide_headings = {
            "MANFAAT",
            "PELAYANAN",
            "KEMUDAHAN",
    };

    public String[] slide_desc = {
      "Dengan platform CARIDEVELOPER dapat membantu anda dalam mengerjakan project pengembangan bisnis anda",
      "Pelayanan yang diberikan dapat dilihat dari rating setiap developer pada platform",
      "Kemudahan akses yang di berikan oleh platform memudahkan user menggunakan"

    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == (RelativeLayout) o;
    }

    @Override
    public Object instantiateItem (ViewGroup container, int position){

        layoutInflater  = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slideimages);
        TextView slideHeading = (TextView) view.findViewById(R.id.slideheading);
        TextView slideDescprition = (TextView) view.findViewById(R.id.slidedesc);

        slideImageView.setImageResource(slideimages[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescprition.setText(slide_desc[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem (ViewGroup container,int position,Object object) {

        container.removeView((RelativeLayout) object);

    }



    }





