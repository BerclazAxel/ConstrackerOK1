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
        db.execSQL(NewConferenceDB.TableUser.CREATE_TABLE_USER);
        db.execSQL(NewConferenceDB.TableRoom.CREATE_TABLE_ROOM);
        db.execSQL(NewConferenceDB.TableTopic.CREATE_TABLE_TOPIC);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop old tables
        db.execSQL("DROP TABLE IF EXISTS " + NewConferenceDB.TableUser.CREATE_TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + NewConferenceDB.TableRoom.CREATE_TABLE_ROOM);
        db.execSQL("DROP TABLE IF EXISTS " + NewConferenceDB.TableTopic.CREATE_TABLE_TOPIC);

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
