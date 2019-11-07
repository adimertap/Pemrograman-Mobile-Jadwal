package com.example.donasi2.profile;


import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donasi2.R;
import com.example.donasi2.loginregister.LoginActivity;
import com.example.donasi2.loginregister.Preferences;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private static final int PERMISSION_REQUEST_CODE = 100;

    TextView tvNama, tvAlamat;
    Button buttonkeluar;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        buttonkeluar = (Button) view.findViewById(R.id.buttonkeluar);
        tvNama = (TextView) view.findViewById(R.id.tv_account_name);

        tvNama.setText(Preferences.getLoggedInUser(requireActivity().getBaseContext()));

        buttonkeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Preferences.clearLoggedInUser(requireContext());
                startActivity(new Intent(requireContext(), LoginActivity.class));
                requireActivity().finish();
            }
        });

//        getExternalStorageText();

        return view;
    }

    private void getExternalStorageText(){
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (checkPermission()) {
                    File sdcard = Environment.getExternalStorageDirectory();
                    File dir = new File(sdcard.getAbsolutePath() + "/text/");
                    if(dir.exists()) {
                        File file = new File(dir, "sample.txt");

                        FileOutputStream os = null;
                        StringBuilder text = new StringBuilder();

                        try {
                            BufferedReader br = new BufferedReader(new FileReader(file));
                            String line;

                            while ((line = br.readLine()) != null) {
                                text.append(line);
                                text.append('\n');
                            }
                            br.close();
                        } catch (IOException e) {
                            //You'll need to add proper error handling here
                        }
                        tvNama.setText(text);
                    }
                } else {
                    requestPermission(); // Code for permission
                }
            } else {

                File sdcard = Environment.getExternalStorageDirectory();
                File dir = new File(sdcard.getAbsolutePath() + "/text/");
                if(dir.exists()) {
                    File file = new File(dir, "sample.txt");

                    FileOutputStream os = null;
                    StringBuilder text = new StringBuilder();

                    try {
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String line;

                        while ((line = br.readLine()) != null) {
                            text.append(line);
                            text.append('\n');
                        }
                        br.close();
                    } catch (IOException e) {
                        //You'll need to add proper error handling here
                    }
                    tvNama.setText(text);
                }
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(getContext(), "Write External Storage permission allows us to read files. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }

}