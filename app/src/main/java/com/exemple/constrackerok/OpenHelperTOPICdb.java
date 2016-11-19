package com.exemple.constrackerok;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;



public class OpenHelperTOPICdb {
    public static final int database_version = 1;
    // We create here a table
    // using the table name
    // we specify 2 column names and 2 data type (text)
    // operator "," between 2 column names
    // before data type - > add espace
    public String CREATE_QUERY = "CREATE TABLE " + TableData.TableInfo.TABLE_NAME_TOPIC + "(" +
            TableData.TableInfo.TOPIC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TableData.TableInfo.TOPIC_NAME+ " TEXT, " +
            TableData.TableInfo.TOPIC_START_TIME+ " TEXT, " +
            TableData.TableInfo.TOPIC_END_TIME + " TEXT, " +
            TableData.TableInfo.TOPIC_DATE + " TEXT, " +
            TableData.TableInfo.TOPIC_ID_SPEAKER + " INTEGER, " +
            TableData.TableInfo.TOPIC_ID_ROOM + " INTEGER);";

    // + TASK_CAT + " integer,"
   // + " FOREIGN KEY ("+TASK_CAT+") REFERENCES "+CAT_TABLE+"("+CAT_ID+"));";

    /*public OpenHelperTOPICdb(Context context) {
        super(context, TableData.TableInfo.DATABASE_TOPIC, null, database_version);
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
        cv.put(TableData.TableInfo.USER_NAME, name);
        cv.put(TableData.TableInfo.USER_PASSWORD, password);
        long k = sq.insert(TableData.TableInfo.TABLE_NAME_USER, null, cv);

        Log.d("Database operations", "One raw inserted");

    }

    //Object cursor
    public Cursor getInformation(OpenHelperUSERdb dop) {
        SQLiteDatabase sq = dop.getReadableDatabase();
        String[] coloumns = {TableData.TableInfo.USER_NAME, TableData.TableInfo.USER_PASSWORD};
      /* Cursor query (String table, String[] columns, String selection, String[] selectionArgs,
        String groupBy, String having, String orderBy)
        Query the given URL, returning a Cursor over the result set.
        Parameters: distinct
        boolean: true if you want each row to be unique, false otherwise. */
       /* Cursor cr = sq.query(TableData.TableInfo.TABLE_NAME_USER, coloumns, null, null, null, null, null);

        return cr;
    }

    //we remove user-speaker from database
    public void deleteUser(OpenHelperUSERdb dop, String user, String pass) {
        String selection = TableData.TableInfo.USER_NAME + " LIKE ? AND " + TableData.TableInfo.USER_PASSWORD + " LIKE ?";
        String coloumns[] = {TableData.TableInfo.USER_PASSWORD};
        String arg[] = {user, pass};
        SQLiteDatabase sq = dop.getWritableDatabase();
        // delete (table, whereClause, whereArgs)
        sq.delete(TableData.TableInfo.TABLE_NAME_USER, selection, arg);

    }

    //send object instead of string
    public void updateUserInfo(OpenHelperUSERdb dop, String user_name, String user_pass, String newuser_name) {
        SQLiteDatabase sq = dop.getWritableDatabase();
        String selection = TableData.TableInfo.USER_NAME + " LIKE ? AND " + TableData.TableInfo.USER_PASSWORD + " LIKE ? ";
        String args[] = {user_name, user_pass};
        ContentValues values = new ContentValues();
        values.put(TableData.TableInfo.USER_NAME, newuser_name);
        sq.update(TableData.TableInfo.TABLE_NAME_USER, values, selection, args);

    }*/


}//END


