package com.example.donasi2.phonebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donasi2.R;

public class DetailActivity extends AppCompatActivity {
    PhonebookModel phonebookModel;
    int id;
    private TextView tvnama, tvnohp, tvalamat, tvhubungan, tvubah, tvhapus, tvtambah;
    private DatabaseHelperPhonebook databaseHelperPhonebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        phonebookModel = (PhonebookModel) intent.getSerializableExtra("students");
        databaseHelperPhonebook = new DatabaseHelperPhonebook(this);

        tvnama = (TextView) findViewById(R.id.tv_nama);
        tvnohp = (TextView) findViewById(R.id.tv_nohp);
        tvalamat = (TextView) findViewById(R.id.tv_alamat);
        tvhubungan = (TextView) findViewById(R.id.tv_hubungan);
        tvtambah = findViewById(R.id.tv_tambah);
        tvhapus = findViewById(R.id.tv_hapus);
        tvubah = findViewById(R.id.tv_ubah);

        tvnama.setText(phonebookModel.getNama());
        tvnohp.setText(phonebookModel.getNohp());
        tvalamat.setText(phonebookModel.getAlamat());
        tvhubungan.setText(phonebookModel.getHubungan());

        tvtambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTeachersr = new Intent(DetailActivity.this, AddActivity.class);
                startActivity(addTeachersr);
            }
        });

        tvhapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelperPhonebook.deleteUSer(phonebookModel.getId());
                Toast.makeText(DetailActivity.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetailActivity.this,PhonebookActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        tvubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this,EditActivity.class);
                intent.putExtra("students",phonebookModel);
                startActivity(intent);
            }
        });
    }
}
