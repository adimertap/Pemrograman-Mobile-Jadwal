package com.example.donasi2.loginregister;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
    /** Pendeklarasian key-data berupa String, untuk sebagai wadah penyimpanan data.*/
    static final String KEY_NAME_TEREGISTER ="name", KEY_USER_TEREGISTER ="user", KEY_PASS_TEREGISTER ="pass";
    static final String KEY_USERNAME_SEDANG_LOGIN = "Username_logged_in";
    static final String KEY_STATUS_SEDANG_LOGIN = "Status_logged_in";

    public Preferences(Context applicationContext) {

    }

    /** Pendlakarasian Shared Preferences yang berdasarkan paramater context, mutator */
    private static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setRegisteredUser(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USER_TEREGISTER, username);
        editor.apply();
    }

    public static String getRegisteredUser(Context context){
        return getSharedPreference(context).getString(KEY_USER_TEREGISTER,"");
    }

    public static void setRegisteredPass(Context context, String password){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_PASS_TEREGISTER, password);
        editor.apply();
    }

    public static String getRegisteredPass(Context context){
        return getSharedPreference(context).getString(KEY_PASS_TEREGISTER,"");
    }

    public static void setLoggedInUser(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USERNAME_SEDANG_LOGIN, username);
        editor.apply();
    }

    public static String getLoggedInUser(Context context){
        return getSharedPreference(context).getString(KEY_USERNAME_SEDANG_LOGIN,"");
    }

    public static void setLoggedInStatus(Context context, boolean status){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putBoolean(KEY_STATUS_SEDANG_LOGIN,status);
        editor.apply();
    }

    public static boolean getLoggedInStatus(Context context){
        return getSharedPreference(context).getBoolean(KEY_STATUS_SEDANG_LOGIN,false);
    }

    public static void setKeyNameTeregister(Context context, String name){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_NAME_TEREGISTER, name);
        editor.apply();
    }

    public static String getKeyNameTeregister(Context context){
        return getSharedPreference(context).getString(KEY_NAME_TEREGISTER,"");
    }

    public static void clearLoggedInUser (Context context){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(KEY_NAME_TEREGISTER);
        editor.remove(KEY_USERNAME_SEDANG_LOGIN);
        editor.remove(KEY_STATUS_SEDANG_LOGIN);
        editor.apply();
    }

}


