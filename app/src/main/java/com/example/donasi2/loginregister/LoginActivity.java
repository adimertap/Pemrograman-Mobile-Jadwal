package com.example.donasi2.loginregister;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.donasi2.Dashboard2Activity;
import com.example.donasi2.DashboardActivity;
import com.example.donasi2.R;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    ViewPager viewPager;
    EditText usernameregis, emailregis, passwordregis, ulangpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameregis = findViewById(R.id.usernameregis);
        emailregis = findViewById(R.id.emailregis);
        passwordregis = findViewById(R.id.passwordregis);
        ulangpassword = findViewById(R.id.ulangpasswordregis);
        viewPager = findViewById(R.id.viewPager);

        AuthenticationPagerAdapter pagerAdapter = new AuthenticationPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragmet(new LoginFragment());
        pagerAdapter.addFragmet(new RegisterFragment());
        viewPager.setAdapter(pagerAdapter);
    }

    /** ke Main2Activity jika data Status Login dari Data Preferences bernilai true */
    @Override
    protected void onStart() {
        super.onStart();
        if (Preferences.getLoggedInStatus(getBaseContext())){
            startActivity(new Intent(getBaseContext(), DashboardActivity.class));
            finish();
        }
    }


    class AuthenticationPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragmentList = new ArrayList<>();

        public AuthenticationPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        void addFragmet(Fragment fragment) {
            fragmentList.add(fragment);
        }
    }

}








































//package com.example.donasi2.loginregister;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.support.v7.app.AppCompatActivity;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.donasi2.R;
//import com.example.donasi2.SlideActivity;
//
//public class LoginActivity extends AppCompatActivity {
//    LinearLayout atas;
//    Animation uptodown, downtoup;
//    Button buttonlogin;
//    TextView registertext;
//    EditText temail, tpassword, tusername;
//    SharedPreferences pref;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        setTitle("LOGIN");
//
//        atas = (LinearLayout) findViewById(R.id.atas);
//        buttonlogin = (Button) findViewById(R.id.buttonlogin);
//        registertext = (TextView) findViewById(R.id.registertext);
//        temail = (EditText) findViewById(R.id.email);
//        tpassword = (EditText) findViewById(R.id.password);
//        tusername = (EditText) findViewById(R.id.username);
//
//        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
//        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
//        atas.setAnimation(uptodown);
//
//        final SharedPreferences sharedPreferences=getSharedPreferences("USER_CREDENTIALS",MODE_PRIVATE);
//        final String required_email=sharedPreferences.getString("EMAIL","DEFAULT_EMAIL");
//        final String required_password=sharedPreferences.getString("PASSWORD","DEFAULT_PASSWORD");
//
//        buttonlogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email=temail.getText().toString();
//                String password=tpassword.getText().toString();
//                if(email.equals(required_email)&&password.equals(required_password)) {
//                    sharedPreferences.edit().putBoolean("ISLOGGEDIN",false).apply();
//                    Intent main = new Intent(LoginActivity.this, SlideActivity.class);
//                    startActivity(main);
//                }
//                else
//                {
//                    Toast.makeText(LoginActivity.this,"Email address or password is incorrect",Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//        registertext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent register=new Intent(LoginActivity.this, RegistrasiActivity.class);
//                startActivity(register);
//                finish();
//            }
//        });
//
//    }
//}
//
//
//
//
//
//
//
//

//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////        pref=getSharedPreferences("login",MODE_PRIVATE);
////
////        //if SharedPreferences contains username and password then directly redirect to Home activity
////        if(pref.contains("username") && pref.contains("password")){
////            startActivity(new Intent(LoginActivity.this,DashboardActivity.class));
////            finish();   //finish current activity
////        }
////
////        buttonlogin.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                loginCheck();
////            }
////        });
////    }
////
////    void loginCheck(){
////        //check username and password are correct and then add them to SharedPreferences
////        if(temail.getText().toString().equals("programmer") && tpassword.getText().toString().equals("programmer")){
////            SharedPreferences.Editor e=pref.edit();
////            e.putString("username","programmer");
////            e.putString("password","programmer");
////            e.commit();
////
////            Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
////
////            startActivity(new Intent(LoginActivity.this,DashboardActivity.class));
////            finish();
////        }
////        else{
////            Toast.makeText(LoginActivity.this,"Incorrect Login Details",Toast.LENGTH_LONG).show();
////        }
////    }
////}
//
//
//
//
//
//
//
//
////        tvregister.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent i = new Intent(LoginActivity.this, RegistrasiActivity.class);
////            startActivity(i);
////            }
////        });
////
////        buttonlogin.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                String user = temail.getText().toString();
////                String password =tpassword.getText().toString();
////
////                SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
////
////                String userDetails = preferences.getString(user + password + "data", "Username or Password is Incorrect");
////                SharedPreferences.Editor editor = preferences.edit();
////                editor.putString("display", userDetails);
////                editor.commit();
////
////                Intent dashboard = new Intent(LoginActivity.this, WelcomeActivity.class);
////                startActivity(dashboard);
////            }
////        });
////
////
////    }
////}