package com.exemple.constrackerok.DataSource;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.exemple.constrackerok.NewConferenceDB;
import com.exemple.constrackerok.NewDataBaseHelper;
import com.exemple.constrackerok.Objects.Room;

public class RoomDataSource {
    private SQLiteDatabase db;
    private Context context;

    public RoomDataSource(Context context){
        NewDataBaseHelper sqliteHelper = NewDataBaseHelper.getInstance(context);
        db = sqliteHelper.getWritableDatabase();
        this.context = context;
    }

    /**
     * Insert a new room
     */
    public long createRoom(Room room){
        long id;
        ContentValues values = new ContentValues();
        values.put(NewConferenceDB.TableRoom.ROOM_NAME, room.getNameRoom());
        values.put(NewConferenceDB.TableRoom.ROOM_NBPEOPLE, room.getNbPeople());

        id = this.db.insert(NewConferenceDB.TableRoom.TABLE_NAME_ROOM, null, values);

        return id;
    }



}
