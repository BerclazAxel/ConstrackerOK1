package com.exemple.constrackerok.DataSource;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.exemple.constrackerok.NewConferenceDB;
import com.exemple.constrackerok.NewDataBaseHelper;
import com.exemple.constrackerok.Objects.Room;
import com.exemple.constrackerok.Objects.User;

import java.util.ArrayList;
import java.util.List;

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

    public List<Room> getAllRooms(){
        List<Room> rooms = new ArrayList<Room>();
        String sql = "SELECT * FROM " + NewConferenceDB.TableRoom.TABLE_NAME_ROOM + " ORDER BY " + NewConferenceDB.TableRoom.ROOM_NAME;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                Room room = new Room();
                room.setIdRoom(cursor.getInt(cursor.getColumnIndex(NewConferenceDB.TableRoom.ROOM_ID)));
                room.setNameRoom(cursor.getString(cursor.getColumnIndex(NewConferenceDB.TableRoom.ROOM_NAME)));
                room.setNbPeople(cursor.getInt(cursor.getColumnIndex(NewConferenceDB.TableRoom.ROOM_NBPEOPLE)));

                rooms.add(room);
            } while(cursor.moveToNext());
        }

        return rooms;
    }

}
