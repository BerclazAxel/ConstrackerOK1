package com.exemple.constrackerok;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseOperations extends SQLiteOpenHelper {
public static final int database_version = 1;
    // We create here a table
    // using the table name
    // we specify 2 column names and 2 data type (text)
    // operator "," between 2 column names
public String CREATE_QUERY =  "CREATE TABLE" + TableData.TableInfo.TABLE_NAME + "(" + TableData.TableInfo.USER_NAME + "TEXT, " + TableData.TableInfo.USER_PASSWORD + "TEXT );";

    public DatabaseOperations(Context context){

        super(context, TableData.TableInfo.DATABASE_NAME, null, database_version);
   Log.d("Database operations", "Datase created");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL(CREATE_QUERY);
        Log.d("Database operations", "Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void putInformation(DatabaseOperations dop, String name, String password) {
        SQLiteDatabase sq = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.USER_NAME, name);
        cv.put(TableData.TableInfo.USER_PASSWORD, password);
        long k = sq.insert(TableData.TableInfo.TABLE_NAME, null, cv);

        Log.d("Database operations", "One raw inserted");


    }
}
