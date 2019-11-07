package com.example.donasi2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.donasi2.loginregister.LoginActivity;

public class WelcomeActivity extends AppCompatActivity {
    LinearLayout atas, bawah;
    Button buttondonasi;
    Animation uptodown, downtoup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        setTitle("WELCOME");

        buttondonasi = (Button) findViewById(R.id.buttondonasi);
        atas = (LinearLayout) findViewById(R.id.atas);
        bawah = (LinearLayout) findViewById(R.id.bawah);

        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        atas.setAnimation(uptodown);
        bawah.setAnimation(downtoup);

        buttondonasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeActivity.this, SlideActivity.class);
                startActivity(i);
            }
        });


    }
}