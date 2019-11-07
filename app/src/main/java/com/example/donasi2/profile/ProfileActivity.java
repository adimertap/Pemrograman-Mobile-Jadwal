package com.example.donasi2.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.donasi2.DashboardActivity;
import com.example.donasi2.R;
import com.example.donasi2.loginregister.LoginActivity;
import com.example.donasi2.loginregister.Preferences;

public class ProfileActivity extends AppCompatActivity {
    Button lengkapidata, lihatdata, buttonkeluar;
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        preferences = new Preferences(getApplicationContext());


        setTitle("PROFILE");

        lengkapidata = (Button) findViewById(R.id.lengkapidata);
        lihatdata = (Button) findViewById(R.id.lihatdata);
        buttonkeluar = (Button) findViewById(R.id.buttonkeluar);
        TextView welcometext = (TextView) findViewById(R.id.Halo3);
        TextView welcometext2 = (TextView) findViewById(R.id.namadata);
        TextView welcometext3 = (TextView) findViewById(R.id.emaildata);


        final SharedPreferences sharedPreferences = getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE);
        final String name = sharedPreferences.getString("NAME", "DEFAULT_NAME");
        final String email = sharedPreferences.getString("EMAIL", "DEFAULT_EMAIL");

        welcometext.setText("Hello, " + name);
        welcometext2.setText("Nama : " + name);
        welcometext3.setText("Email : " + email);

        buttonkeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Preferences.clearLoggedInUser(getBaseContext());
                startActivity(new Intent(ProfileActivity.this,LoginActivity.class));
                finish();
            }
        });




        lengkapidata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, ProfileLengkapActivity.class);
                startActivity(i);
            }
        });

        lihatdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, SimpanDataProfileActivity.class);
                startActivity(i);
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
            case R.id.homepage:
                Intent intent = new Intent(ProfileActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}




