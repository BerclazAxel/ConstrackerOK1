package com.exemple.constrackerok;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NewDataBaseHelper extends SQLiteOpenHelper{
    private SQLiteDatabase db;

    //Infos about database
    private static final String DATABASE_NAME = "ConsTracker.db";
    private static final int DATABASE_VERSION = 1;
    private static NewDataBaseHelper instance;


    //use a singleton
    //we want always just one instance of the database
    public NewDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }

    public static NewDataBaseHelper getInstance(Context context){
        if(instance == null){
            instance = new NewDataBaseHelper(context.getApplicationContext());
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

        //create new tables/
        onCreate(db);
    }


}
