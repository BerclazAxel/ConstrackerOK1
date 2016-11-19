package com.exemple.constrackerok;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class OpenHelperUSERdb extends SQLiteOpenHelper {
    public static final int database_version = 1;
    // We create here a table
    // using the table name
    // we specify 2 column names and 2 data type (text)
    // operator "," between 2 column names
    // before data type - > add espace
    public String CREATE_QUERY = "CREATE TABLE " + TableData.TableInfo.TABLE_NAME_USER + "(" +
            TableData.TableInfo.USER_ID + " ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TableData.TableInfo.USER_TITLE + " TEXT, " +
            TableData.TableInfo.USER_NAME_USER + " TEXT, " +
            TableData.TableInfo.USER_SURNAME + " TEXT, " +
            TableData.TableInfo.USER_TEL + " TEXT, " +
            TableData.TableInfo.USER_EMAIL + " TEXT, " +
            TableData.TableInfo.USER_PASSWORD + " TEXT);";

    public OpenHelperUSERdb(Context context) {
        super(context, TableData.TableInfo.DATABASE_USER, null, database_version);
        Log.d("Database operations", "DataDase  created");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.d("Database operations", "Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void registerUserToDB(OpenHelperUSERdb dop, String name, String password) {
        SQLiteDatabase sq = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.USER_NAME_USER, name);
        cv.put(TableData.TableInfo.USER_PASSWORD, password);
        long k = sq.insert(TableData.TableInfo.TABLE_NAME_USER, null, cv);

        Log.d("Database operations", "One raw inserted");

    }

    //Object cursor
    public Cursor getInformation(OpenHelperUSERdb dop) {
        SQLiteDatabase sq = dop.getReadableDatabase();
        String[] coloumns = {TableData.TableInfo.USER_NAME_USER, TableData.TableInfo.USER_PASSWORD};
      /* Cursor query (String table, String[] columns, String selection, String[] selectionArgs,
        String groupBy, String having, String orderBy)
        Query the given URL, returning a Cursor over the result set.
        Parameters: distinct
        boolean: true if you want each row to be unique, false otherwise. */
        Cursor cr = sq.query(TableData.TableInfo.TABLE_NAME_USER, coloumns, null, null, null, null, null);

        return cr;
    }

    //we remove user-speaker from database
    public void deleteUser(OpenHelperUSERdb dop, String user, String pass) {
        String selection = TableData.TableInfo.USER_NAME_USER + " LIKE ? AND " + TableData.TableInfo.USER_PASSWORD + " LIKE ?";
        String coloumns[] = {TableData.TableInfo.USER_PASSWORD};
        String arg[] = {user, pass};
        SQLiteDatabase sq = dop.getWritableDatabase();
        // delete (table, whereClause, whereArgs)
        sq.delete(TableData.TableInfo.TABLE_NAME_USER, selection, arg);

    }

    //send object instead of string
    public void updateUserInfo(OpenHelperUSERdb dop, String user_name, String user_pass, String newuser_name) {
        SQLiteDatabase sq = dop.getWritableDatabase();
        String selection = TableData.TableInfo.USER_NAME_USER + " LIKE ? AND " + TableData.TableInfo.USER_PASSWORD + " LIKE ? ";
        String args[] = {user_name, user_pass};
        ContentValues values = new ContentValues();
        values.put(TableData.TableInfo.USER_NAME_USER, newuser_name);
        sq.update(TableData.TableInfo.TABLE_NAME_USER, values, selection, args);

    }


}//END
