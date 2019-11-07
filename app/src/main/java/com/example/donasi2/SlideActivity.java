package com.example.donasi2;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.donasi2.loginregister.LoginActivity;

public class SlideActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout dotlayout;
    private SlideAdapter slideAdapter;
    private TextView[] mDots;
    private Button buttonnext;
    private Button buttonback;
    private Button buttonfinish;
    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        setTitle("WELCOME");

        viewPager = (ViewPager) findViewById(R.id.slideview);
        dotlayout = (LinearLayout) findViewById(R.id.dotlayout);
        buttonback = (Button) findViewById(R.id.back);
        buttonnext = (Button) findViewById(R.id.next);
        buttonfinish = (Button) findViewById(R.id.buttonfinish);
        slideAdapter = new SlideAdapter(this);
        viewPager.setAdapter(slideAdapter);
        addDotsIndicator(0);
        viewPager.addOnPageChangeListener(viewListener);

        buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(mCurrentPage + 1);
            }
        });

        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(mCurrentPage - 1);
            }
        });

        buttonfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dash=new Intent(SlideActivity.this, LoginActivity.class);
                startActivity(dash);
                finish();
            }
        });
    }
    public void addDotsIndicator(int position){

        mDots = new TextView[3];
        dotlayout.removeAllViews();

        for (int i=0; i < mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.TransparanWhite));

            dotlayout.addView(mDots[i]);
        }

        if (mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.white));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }
        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);

            mCurrentPage = i;

            if (i == 0) {

                buttonnext.setEnabled(true);
                buttonback.setEnabled(false);
                buttonback.setVisibility(View.INVISIBLE);

                buttonnext.setText("Next");
                buttonback.setText("");
            } else if (i == mDots.length - 1) {
                buttonnext.setEnabled(true);
                buttonback.setEnabled(true);
                buttonback.setVisibility(View.VISIBLE);

                buttonnext.setText("DONASI YUK");
                buttonback.setText("BACK");
            } else {
                buttonnext.setEnabled(true);
                buttonback.setEnabled(true);
                buttonback.setVisibility(View.VISIBLE);

                buttonnext.setText("NEXT");
                buttonback.setText("BACK");
            }
        }
        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}




