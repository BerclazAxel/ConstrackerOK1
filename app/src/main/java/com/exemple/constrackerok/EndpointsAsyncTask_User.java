package com.exemple.constrackerok;

import android.os.AsyncTask;
import android.util.Log;

import com.example.daria.myapplication.backend.userApi.UserApi;
import com.example.daria.myapplication.backend.userApi.model.User;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Thie on 11.01.2017.
 */

public class EndpointsAsyncTask_User extends AsyncTask<Void,Void,List<User>> {
    private static UserApi userApi = null;
    private static final String TAG = EndpointsAsyncTask_User.class.getName();
    private User user;


    @Override
    protected List<User> doInBackground(Void... params)
    {
        if(userApi == null){
            UserApi.Builder builder = new UserApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(),null)
                    .setRootUrl("https://questionboard-155021.appspot.com/_ah/api/");
            userApi = builder.build();
        }

        try {
            if(user != null){
                userApi.insert(user).execute();
                Log.i(TAG,"insert answer");
            }
            return userApi.list().execute().getItems();
        } catch (IOException e) {
            Log.e(TAG, e.toString());
            return new ArrayList<User>();
        }
    }
    protected void onPostExecute(List<User> result) {
        if(result != null){
            for(User user : result){
                Log.i(TAG, "Content : " + user.getName());
            }
        }
    }
}
