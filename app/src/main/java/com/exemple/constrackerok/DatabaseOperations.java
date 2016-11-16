package com.exemple.constrackerok;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseOperations extends SQLiteOpenHelper {
    public static final int database_version = 1;
    // We create here a table
    // using the table name
    // we specify 2 column names and 2 data type (text)
    // operator "," between 2 column names
    // before data type - > add espace
    public String CREATE_QUERY = "CREATE TABLE " + TableData.TableInfo.TABLE_NAME + "(" + TableData.TableInfo.USER_NAME + " TEXT," + TableData.TableInfo.USER_PASSWORD + " TEXT);";

    public DatabaseOperations(Context context) {

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

    public void registerUserToDB(DatabaseOperations dop, String name, String password) {
        SQLiteDatabase sq = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.USER_NAME, name);
        cv.put(TableData.TableInfo.USER_PASSWORD, password);
        long k = sq.insert(TableData.TableInfo.TABLE_NAME, null, cv);

        Log.d("Database operations", "One raw inserted");

    }

    //Object cursor
    public Cursor getInformation(DatabaseOperations dop) {
        SQLiteDatabase sq = dop.getReadableDatabase();
        String[] coloumns = {TableData.TableInfo.USER_NAME, TableData.TableInfo.USER_PASSWORD};
      /* Cursor query (String table, String[] columns, String selection, String[] selectionArgs,
        String groupBy, String having, String orderBy)
        Query the given URL, returning a Cursor over the result set.
        Parameters: distinct
        boolean: true if you want each row to be unique, false otherwise. */
        Cursor cr = sq.query(TableData.TableInfo.TABLE_NAME, coloumns, null, null, null, null, null);

        return cr;
    }

//we remove user-speaker from database
    public void deleteUser(DatabaseOperations dop, String user, String pass) {
String selection = TableData.TableInfo.USER_NAME+ " LIKE ? AND " + TableData.TableInfo.USER_PASSWORD + " LIKE ?";
        String coloumns [] = {TableData.TableInfo.USER_PASSWORD};
        String arg[] = {user, pass};
        SQLiteDatabase sq = dop.getWritableDatabase();
        // delete (table, whereClause, whereArgs)
        sq.delete(TableData.TableInfo.TABLE_NAME, selection, arg);


    }
}
