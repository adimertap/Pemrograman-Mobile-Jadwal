package com.example.donasi2.phonebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donasi2.R;

public class AddActivity extends AppCompatActivity {

    private TextView tvsimpan, tvbatal;
    private EditText etnama, etnohp, etalamat, ethubungan;
    private DatabaseHelperPhonebook databaseHelperPhonebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        databaseHelperPhonebook = new DatabaseHelperPhonebook(this);

        setTitle("TAMBAH TEMAN");

        tvbatal = findViewById(R.id.tv_batal);
        tvsimpan = findViewById(R.id.tv_simpan1);
        etnama = (EditText) findViewById(R.id.et_nama_add);
        etnohp = (EditText) findViewById(R.id.et_nohp_add);
        etalamat = (EditText) findViewById(R.id.et_alamat_add);
        ethubungan = (EditText) findViewById(R.id.et_hubungan_add);

        tvbatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etnama.getText().toString();
                if (TextUtils.isEmpty(name)){
                    etnama.setError("Enter Name");
                    etnama.requestFocus();
                    return;
                }

                databaseHelperPhonebook.addPhonebookDetail(
                        etnama.getText().toString(),
                        etnohp.getText().toString(),
                        etalamat.getText().toString(),
                        ethubungan.getText().toString());
//
                etalamat.setText("");
                ethubungan.setText("");

                Toast.makeText(AddActivity.this, "Stored Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddActivity.this,PhonebookActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }
}