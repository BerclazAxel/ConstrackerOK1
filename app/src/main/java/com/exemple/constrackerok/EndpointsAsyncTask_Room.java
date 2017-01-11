package com.exemple.constrackerok;

import android.os.AsyncTask;
import android.util.Log;

import com.example.daria.myapplication.backend.roomApi.RoomApi;
import com.example.daria.myapplication.backend.roomApi.model.Room;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.util.List;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Thie on 11.01.2017.
 */

public class EndpointsAsyncTask_Room extends AsyncTask<Void,Void,List<Room>> {

    private static RoomApi roomApi = null;
    private static final String TAG = EndpointsAsyncTask_Room.class.getName();
    private Room room;

    EndpointsAsyncTask_Room(){}
    EndpointsAsyncTask_Room(Room answer){ this.room = room; }

    @Override
    protected List<Room> doInBackground(Void... params)
    {
        if(roomApi == null){
            RoomApi.Builder builder = new RoomApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(),null)
                    .setRootUrl("https://questionboard-155021.appspot.com/_ah/api/");
            roomApi = builder.build();
        }

        try {
            if(room != null){
                roomApi.insert(room).execute();
                Log.i(TAG,"insert answer");
            }
            return roomApi.list().execute().getItems();
        } catch (IOException e) {
            Log.e(TAG, e.toString());
            return new ArrayList<Room>();
        }
    }
    protected void onPostExecute(List<Room> result) {
        if(result != null){
            for(Room answer : result){
                Log.i(TAG, "Content : " + answer.getNameRoom());
            }
        }
    }



}
