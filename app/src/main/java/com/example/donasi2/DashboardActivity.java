package com.example.donasi2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.donasi2.loginregister.LoginActivity;
import com.example.donasi2.phonebook.PhonebookActivity;
import com.example.donasi2.profile.ProfileActivity;
import com.example.donasi2.profile.ProfileFragment;


public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {
    Intent login;
    private CardView carduser, carddonasi, carddaftar;
    private SharedPreferences profile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        setTitle("DASHBOARD");

        final SharedPreferences sharedPreferences = getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE);
        final String name = sharedPreferences.getString("NAME", "DEFAULT_NAME");
        carduser = (CardView) findViewById(R.id.carduser);
        carddonasi = (CardView) findViewById(R.id.carddonasi);
        carddaftar = (CardView) findViewById(R.id.carddaftar);
        carduser.setOnClickListener(this);
        carddonasi.setOnClickListener(this);
        carddaftar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

//        startActivity(new Intent(getBaseContext(), Dashboard2Activity.class));
//        finish();
//
        switch (v.getId()) {
            case R.id.carduser:
                i = new Intent(this, ProfileActivity.class);
                startActivity(i);
                break;
            case R.id.carddonasi:
                i = new Intent(this, PhonebookActivity.class);
                startActivity(i);
                break;
            case R.id.carddaftar:
                i = new Intent(this, DaftarPembimbingActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout:
                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.about:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("PENILAIAN");
                alert.setMessage("Apakah Kamu Sangat Menikmati Layanan Yang Kami Berikan ?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DashboardActivity.this, "Terima Kasih", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DashboardActivity.this, "Kami Akan Tingkatkan Pelayanan", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.create().show();
            default:
                return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }
}



