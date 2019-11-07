package com.example.donasi2.loginregister;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.donasi2.DashboardActivity;
import com.example.donasi2.R;
import com.example.donasi2.SlideActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    EditText emaillogin, passwordlogin;
    Button buttonlogin;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        buttonlogin = (Button) view.findViewById(R.id.buttonlogin);
        emaillogin = (EditText) view.findViewById(R.id.emaillogin);
        passwordlogin = (EditText) view.findViewById(R.id.passwordlogin);

        passwordlogin.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    loginSharedPreferences();
                    return true;
                }
                return false;
            }
        });

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginSharedPreferences();
            }
        });

        return view;
    }

    private void loginSharedPreferences(){
        /* Mereset semua Error dan fokus menjadi default */
        emaillogin.setError(null);
        passwordlogin.setError(null);
        View fokus = null;
        boolean cancel = false;

        String email = emaillogin.getText().toString();
        String password = passwordlogin.getText().toString();

        /* Jika form email kosong atau TIDAK memenuhi kriteria di Method cekUser(), maka Set error
         *  di Form Email dengan menset variable fokus dan error di Viewnya juga cancel menjadi true*/
        if (TextUtils.isEmpty(email)){
            emaillogin.setError("This field is required");
            fokus = emaillogin;
            cancel = true;
        }else if(!cekUser(email)){
            emaillogin.setError("This Email is not found");
            fokus = emaillogin;
            cancel = true;
        }

        /* Sama syarat percabangannya dengan Email seperti di atas. Bedanya ini untuk Form Password*/
        if (TextUtils.isEmpty(password)){
            passwordlogin.setError("This field is required");
            fokus = passwordlogin;
            cancel = true;
        }else if (!cekPassword(password)){
            passwordlogin.setError("This password is incorrect");
            fokus = passwordlogin;
            cancel = true;
        }

        /* Jika cancel true, variable fokus mendapatkan fokus */
        if (cancel) fokus.requestFocus();
        else masuk();
    }

    /** Menuju ke Main2Activity dan Set User dan Status sedang login, di Preferences */
    private void masuk(){
        Preferences.setLoggedInUser(requireActivity().getBaseContext(),Preferences.getKeyNameTeregister(requireActivity().getBaseContext()));
        Preferences.setLoggedInStatus(requireActivity().getBaseContext(),true);
        startActivity(new Intent(requireActivity().getBaseContext(), DashboardActivity.class));
        requireActivity().finish();
    }

    /** True jika parameter password sama dengan data password yang terdaftar dari Preferences */
    private boolean cekPassword(String password){
        return password.equals(Preferences.getRegisteredPass(requireActivity().getBaseContext()));
    }

    /** True jika parameter user sama dengan data user yang terdaftar dari Preferences */
    private boolean cekUser(String user){
        return user.equals(Preferences.getRegisteredUser(requireActivity().getBaseContext()));
    }

}
