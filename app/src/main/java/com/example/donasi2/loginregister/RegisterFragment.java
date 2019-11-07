package com.example.donasi2.loginregister;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donasi2.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

//    private static final int PERMISSION_REQUEST_CODE = 100;

    Button buttonregis;
    EditText usernameregis, emailregis, passwordregis, ulangpasswordregis;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        buttonregis = (Button) view.findViewById(R.id.buttonregis);
        usernameregis = (EditText) view.findViewById(R.id.usernameregis);
        emailregis = (EditText) view.findViewById(R.id.emailregis);
        passwordregis = (EditText) view.findViewById(R.id.passwordregis);
        ulangpasswordregis = (EditText) view.findViewById(R.id.ulangpasswordregis);

        passwordregis.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    registerSharedPreferences();
                    return true;
                }
                return false;
            }
        });

        buttonregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerSharedPreferences();
//                registerExternalStorage();
            }
        });

        return view;
    }

    /**
     * Men-check inputan User dan Password dan Memberikan akses ke Main2Activity
     */
    private void registerSharedPreferences() {
        /* Mereset semua Error dan fokus menjadi default */
        usernameregis.setError(null);
        emailregis.setError(null);
        passwordregis.setError(null);
        ulangpasswordregis.setError(null);
        View fokus = null;
        boolean cancel = false;

        /* Mengambil text dari Form Nama Lengkap, Email, Password, Repassword dengan variable baru bertipe String*/
        String repassword = passwordregis.getText().toString();
        String user = emailregis.getText().toString();
        String password = passwordregis.getText().toString();
        String name = usernameregis.getText().toString();

        /* Jika form name kosong, maka Set error di Form-
         * name dengan menset variable fokus dan error di Viewnya juga cancel menjadi true*/
        if (TextUtils.isEmpty(name)) {
            usernameregis.setError("This field is required");
            fokus = usernameregis;
            cancel = true;
        }

        /* Jika form user kosong atau MEMENUHI kriteria di Method cekUser(), maka Set error di Form-
         * User dengan menset variable fokus dan error di Viewnya juga cancel menjadi true*/
        if (TextUtils.isEmpty(user)) {
            emailregis.setError("This field is required");
            fokus = emailregis;
            cancel = true;
        } else if (cekUser(user)) {
            emailregis.setError("This Username is already exist");
            fokus = emailregis;
            cancel = true;
        }

        /* Jika form password kosong dan MEMENUHI kriteria di Method cekPassword, maka
         * Reaksinya sama dengan percabangan User di atas. Bedanya untuk Password dan Repassword*/
        if (TextUtils.isEmpty(password)) {
            passwordregis.setError("This field is required");
            fokus = passwordregis;
            cancel = true;
        } else if (!cekPassword(password, repassword)) {
            passwordregis.setError("This password is incorrect");
            fokus = passwordregis;
            cancel = true;
        }

        /* Jika cancel true, variable fokus mendapatkan fokus. Jika false, maka
         *  muncul Toast dan menghapus isi edittext dan Set Name, User, dan Password untuk data yang terdaftar */
        if (cancel) {
            fokus.requestFocus();
        } else {
            Preferences.setKeyNameTeregister(requireActivity().getBaseContext(), name);
            Preferences.setRegisteredUser(requireActivity().getBaseContext(), user);
            Preferences.setRegisteredPass(requireActivity().getBaseContext(), password);

            usernameregis.getText().clear();
            emailregis.getText().clear();
            passwordregis.getText().clear();
            ulangpasswordregis.getText().clear();

            Toast.makeText(requireActivity(), "Registrasi berhasil!", Toast.LENGTH_LONG).show();
//            requireActivity().finish();
        }
    }

    /**
     * True jika parameter password sama dengan parameter repassword
     */
    private boolean cekPassword(String password, String repassword) {
        return password.equals(repassword);
    }

    /**
     * True jika parameter user sama dengan data user yang terdaftar dari Preferences
     */
    private boolean cekUser(String user) {
        return user.equals(Preferences.getRegisteredUser(requireContext()));
    }

    ;
}