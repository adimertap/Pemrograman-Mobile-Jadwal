package com.example.donasi2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.donasi2.loginregister.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class DaftarPembimbingActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private Button btnLogout;
    private SharedPreferences profile;
    List<Teman> daftarTeman;
    private RecyclerView rvDaftarTeman;
    private TemanAdapter temanAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_pembimbing);

        setTitle("DAFTAR PEMBIMBING");

        rvDaftarTeman = findViewById(R.id.rv_daftar_teman);
        generateTeman();
        temanAdapter = new TemanAdapter(this.daftarTeman);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rvDaftarTeman.setNestedScrollingEnabled(false);
        rvDaftarTeman.setLayoutManager(linearLayoutManager);
        rvDaftarTeman.setAdapter(temanAdapter);

    }

    public void generateTeman(){
        daftarTeman = new ArrayList<>();

        for(int i = 0;i <10; i++){
            Teman teman = new Teman("Pembimbing " + (i+1), "Alamat Teman" + (i+1),"083117270179" +(i+1));
            daftarTeman.add(teman);
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
                SharedPreferences.Editor profileEditor = profile.edit();
                profileEditor.clear().apply();
                Intent intent = new Intent(DaftarPembimbingActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }
}