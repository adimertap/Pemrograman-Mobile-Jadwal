package com.example.donasi2.profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donasi2.R;

public class SimpanDataProfileActivity extends AppCompatActivity {

    TextView tnama_depan, temail, tno_hp, talamat, tprodi, ttanggal;
    String get_nama_depan, get_nama_belakang, get_email, get_no_hp, get_alamat, get_prodi, get_tanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpan_data_profle);

        setTitle("FORMULIR BIODATA");

        tnama_depan = (TextView) findViewById(R.id.textHasilNama);
        temail = (TextView) findViewById(R.id.textHasilemail);
        tno_hp = (TextView) findViewById(R.id.textHasilnohp);
        talamat = (TextView) findViewById(R.id.textHasilalamat);
        tprodi = (TextView) findViewById(R.id.textHasilprodi);
        ttanggal = (TextView) findViewById(R.id.textHasiltanggal);

        Intent b = getIntent();

        get_nama_depan = b.getStringExtra("nama");
        get_nama_belakang = b.getStringExtra("nama belakang");
        get_email = b.getStringExtra("email");
        get_no_hp = b.getStringExtra("no_hp");
        get_alamat = b.getStringExtra("alamat");
        get_prodi = b.getStringExtra("prodi");
        get_tanggal = b.getStringExtra("Tanggal Lahir");

        tnama_depan.setText("Nama :" + get_nama_depan);
        temail.setText("Email : " + get_email);
        tno_hp.setText("No. Telp : " + get_no_hp);
        talamat.setText("Program Studi : " + get_alamat);
        talamat.setText("Alamat : " + get_alamat);
        tprodi.setText("Program Studi : " + get_prodi);
        ttanggal.setText("Tanggal Lahir : " +get_tanggal);

        Button buttontugas = (Button) findViewById(R.id.Puas);
        buttontugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SimpanDataProfileActivity.this, ProfileActivity.class);
                intent.putExtra("message", "Pelanggan Puas");
                startActivity(intent);
            }
        });

        Button buttontugas2 = (Button) findViewById(R.id.TidakPuas);
        buttontugas2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("message", "Pelanggan Tidak Puas");
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("ABOUT");
                alert.setMessage("Apakah Kamu Sangat Menikmati Layanan Yang Kami Berikan ?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SimpanDataProfileActivity.this, "Terima Kasih", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SimpanDataProfileActivity.this, "Kami Akan Tingkatkan Pelayanan", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.create().show();
        }
        return super.onOptionsItemSelected(item);
    }
}