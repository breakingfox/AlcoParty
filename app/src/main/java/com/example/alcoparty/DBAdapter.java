package com.example.alcoparty;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBAdapter extends SQLiteOpenHelper {
    private static final String LOG_TAG = "LOGS";

    public DBAdapter(@Nullable Context context) {
        super(context, "Database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, "--- onCreate database ---");
        // создаем таблицу с полями
        db.execSQL("create table main ("
                + "id integer primary key autoincrement,"
                + "date text,"
                + "ml integer" + ");");
        db.execSQL("create table pictures ("
                + "id integer, "
                + "picture integer, "
                + "foreign key(id) references main(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
