package com.example.donasi2.profile;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donasi2.R;

import java.util.Calendar;

public class ProfileLengkapActivity extends AppCompatActivity {

    EditText nama_depan, nama_belakang, email, no_hp, alamat, Textdate;
    Spinner spProdi;
    private TextView textView;
    public static final int message_request = 01;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_lengkap);

        setTitle("PROFILE");
    }

        public void Simpan(View view) {

            Intent intent = new Intent(ProfileLengkapActivity.this, SimpanDataProfileActivity.class);

            nama_depan = (EditText) findViewById(R.id.nama_depan);
            nama_belakang = (EditText) findViewById(R.id.nama_belakang);
            email = (EditText) findViewById(R.id.email);
            no_hp = (EditText) findViewById(R.id.no_hp);
            alamat = (EditText) findViewById(R.id.alamat);
            spProdi = (Spinner) findViewById(R.id.pilihan);
            Textdate = (EditText) findViewById(R.id.Textdate);

            String namaDepan = nama_depan.getText().toString();
            String namaBelaknag = nama_belakang.getText().toString();
            String emailku = email.getText().toString();
            String nohpku = no_hp.getText().toString();
            String namanya = namaDepan + " " + namaBelaknag;
            String prodiku = spProdi.getSelectedItem().toString();
            String alamatku = alamat.getText().toString();
            String Date = Textdate.getText().toString();

            boolean anyError = false;
            if (namaDepan.equals("")) {
                nama_depan.setError("nama depan kosong");
                anyError = true;
            }
            if (emailku.equals("")) {
                email.setError("email kosong");
                anyError = true;
            }
            if (nohpku.equals("")) {
                no_hp.setError("no hp kosong");
                anyError = true;
            }
            if (prodiku.equals("Program Studi")) {
                Toast.makeText(getApplicationContext(), "Isi Field", Toast.LENGTH_SHORT).show();
                anyError = true;
            }
            if (alamatku.equals("")) {
                alamat.setError("alamat kosong");
                anyError = true;
            }
            if (Date.equals("")){
                Textdate.setError("Tanggal Kosong");
                anyError = true;
            }
            if (!anyError) {
                intent.putExtra("nama", namanya);
                intent.putExtra("email", emailku);
                intent.putExtra("no_hp", nohpku);
                intent.putExtra("prodi", prodiku);
                intent.putExtra("alamat",  alamatku);
                intent.putExtra("Tanggal Lahir", Date);

                Textdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        final int year = calendar.get(Calendar.YEAR);
                        final int month = calendar.get(Calendar.MONTH);
                        final int day = calendar.get(Calendar.DAY_OF_MONTH);
                        datePickerDialog=new DatePickerDialog(ProfileLengkapActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int mDay, int mMonth, int myear) {
                                Textdate.setText(mDay+ "/" + (mMonth+1) + "/" +myear );
                            }
                        },day, month, year);
                        datePickerDialog.show();
                    }
                });
                startActivityForResult(intent, message_request);
            }
        }

        @Override
        protected void onActivityResult(int requestcode, int resultcode, Intent data) {
            super.onActivityResult(requestcode, resultcode, data);

            textView = (TextView) findViewById(R.id.txt);
            if (message_request == requestcode) {
                if (resultcode == RESULT_OK) {
                    String message = data.getStringExtra("message");
                    textView.setText(message);
                }
            }
        }

    }