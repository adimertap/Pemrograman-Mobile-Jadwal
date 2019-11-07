package com.example.donasi2;

import android.content.Intent;
import android.net.Uri;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailTemanActivity extends AppCompatActivity {

    TextView tvNama,tvAlamat;
    MaterialButton btncall;
    Teman teman;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_teman);

        tvNama = findViewById(R.id.tv_nama);
        tvAlamat = findViewById(R.id.tv_alamat);
        btncall = findViewById(R.id.btn_call);

        final Intent intent = getIntent();
        teman = (Teman) intent.getSerializableExtra("teman");

        tvNama.setText(teman.getName());
        tvAlamat.setText(teman.getAlamat());

        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:" + teman.getTelp()));
                startActivity(call);
            }
        });

    }
}
