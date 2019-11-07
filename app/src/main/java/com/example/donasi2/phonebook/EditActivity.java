package com.example.donasi2.phonebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donasi2.R;

public class EditActivity extends AppCompatActivity {

    PhonebookModel phonebookModel;
    private EditText tvnama, tvnohp, tvalamat, tvhubungan;
    TextView tvupdate, tvbatal;
    private DatabaseHelperPhonebook databaseHelperPhonebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();
        phonebookModel = (PhonebookModel) intent.getSerializableExtra("students");
        databaseHelperPhonebook = new DatabaseHelperPhonebook(this);

        tvnama =  findViewById(R.id.et_nama_edit);
        tvnohp =  findViewById(R.id.et_nohp_edit);
        tvalamat =  findViewById(R.id.et_alamat_edit);
        tvhubungan =  findViewById(R.id.et_hubungan_edit);
        tvupdate = findViewById(R.id.tv_update);
        tvbatal = findViewById(R.id.tv_batal);

        tvnama.setText(phonebookModel.getNama());
        tvnohp.setText(phonebookModel.getNohp());
        tvalamat.setText(phonebookModel.getAlamat());
        tvhubungan.setText(phonebookModel.getHubungan());


        tvbatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelperPhonebook.updatePhonebook(phonebookModel.getId(),tvnama.getText().toString(),tvnohp.getText().toString(),tvalamat.getText().toString(), tvhubungan.getText().toString());
                Toast.makeText(EditActivity.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditActivity.this,PhonebookActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
