package com.exemple.constrackerok;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.KeyCharacterMap.KeyData;

public class SQLiteHelper extends SQLiteOpenHelper{
    private SQLiteDatabase db;

    //Infos about database
    private static final String DATABASE_NAME = "survey.db";
    private static final int DATABASE_VERSION = 1;
    private static SQLiteHelper instance;


    //use a singleton
    //we want always just one instance of the database
    private SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }

    public static SQLiteHelper getInstance(Context context){
        if(instance == null){
            instance = new SQLiteHelper(context.getApplicationContext());
            //Enable foreign key support
            instance.db.execSQL("PRAGMA foreign_keys = ON;");
        }

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PersonEntry.CREATE_TABLE_PERSON);
        db.execSQL(RecordEntry.CREATE_TABLE_RECORD);
        db.execSQL(PersonRecordEntry.CREATE_TABLE_PERSON_RECORD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop old tables
        db.execSQL("DROP TABLE IF EXISTS " + PersonEntry.TABLE_PERSON);
        db.execSQL("DROP TABLE IF EXISTS " + RecordEntry.TABLE_RECORD);
        db.execSQL("DROP TABLE IF EXISTS " + PersonRecordEntry.TABLE_PERSON_RECORD);

        //create new tables
        onCreate(db);
    }



//
//	/**
//	 * get datetime as a string
//	 * */
//	private String getDateTime() {
//		SimpleDateFormat dateFormat = new SimpleDateFormat(
//				"yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//		Date date = new Date();
//		return dateFormat.format(date);
//	}

}
