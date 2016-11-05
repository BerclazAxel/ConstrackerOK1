package com.exemple.constrackerok;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME ="speaker.db";
    public static final String TABLE_NAME = "speaker_data";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "TITLE";
    public static final String COL_3 = "EMAIL";
    public static final String COL_4 = "PASSWORD";
    public static final String COL_5 = "FIRSTNAME";
    public static final String COL_6 = "LASTNAME";
    public static final String COL_7 = "PHONENUMBER";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, EMAIL EMAIL, PASSWORD PASSWORD, FIRSTNAME TEXT, LASTNAME TEXT, PHONUNUMBER NUMBER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
}
