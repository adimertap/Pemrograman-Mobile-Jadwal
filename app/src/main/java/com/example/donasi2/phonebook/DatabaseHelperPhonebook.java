package com.example.donasi2.phonebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelperPhonebook extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "phonebook";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_USER = "User";
    public static final String KEY_ID = "id";
    public static final String KEY_NAMA = "nama";
    public static final String KEY_NOHP = "nohp";
    public static final String KEY_ALAMAT = "alamat";
    public static final String KEY_HUBUNGAN = "hubungan";

    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_NAMA + " TEXT NOT NULL, "+
            KEY_NOHP + " TEXT NOT NULL, "+
            KEY_ALAMAT + " TEXT NOT NULL, "+
            KEY_HUBUNGAN + " TEXT NOT NULL " +
            "); ";

    public DatabaseHelperPhonebook(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("table", CREATE_TABLE_USER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_USER + "'");
        onCreate(db);
    }

    public long addPhonebookDetail(String nama, String nohp, String alamat, String hubungan) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, nama);
        values.put(KEY_NOHP, nohp);
        values.put(KEY_ALAMAT, alamat);
        values.put(KEY_HUBUNGAN, hubungan);
        //insert row in table
        long insert = db.insert(TABLE_USER, null, values);
        return insert;
    }

    public ArrayList<PhonebookModel> getAllPhonebook() {
        ArrayList<PhonebookModel> phonebookModelArrayList = new ArrayList<PhonebookModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                PhonebookModel phonebookModel = new PhonebookModel();
                phonebookModel.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                phonebookModel.setNama(c.getString(c.getColumnIndex(KEY_NAMA)));
                phonebookModel.setNohp(c.getString(c.getColumnIndex(KEY_NOHP)));
                phonebookModel.setAlamat(c.getString(c.getColumnIndex(KEY_ALAMAT)));
                phonebookModel.setHubungan(c.getString(c.getColumnIndex(KEY_HUBUNGAN)));
                // adding to list
                phonebookModelArrayList.add(phonebookModel);
            } while (c.moveToNext());
        }
        return phonebookModelArrayList;
    }

    public int updatePhonebook(int id, String nama, String nohp, String alamat, String hubungan) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, nama);
        values.put(KEY_NOHP, nohp);
        values.put(KEY_ALAMAT, alamat);
        values.put(KEY_HUBUNGAN, hubungan);
        // update row in table base on students.is value
        return db.update(TABLE_USER, values, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void deleteUSer(int id) {

        // delete row in table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }


}
