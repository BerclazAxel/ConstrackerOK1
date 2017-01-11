package com.exemple.constrackerok;

import android.os.AsyncTask;
import android.util.Log;


import com.example.daria.myapplication.backend.topicApi.TopicApi;
import com.example.daria.myapplication.backend.topicApi.model.Topic;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thie on 11.01.2017.
 */

public class EndpointsAsyncTask_Topic extends AsyncTask<Void,Void,List<Topic>> {

    private static TopicApi topicApi = null;
    private static final String TAG = EndpointsAsyncTask_Topic.class.getName();
    private Topic topic;

    @Override
    protected List<Topic> doInBackground(Void... params)
    {
        if(topicApi == null){

            TopicApi.Builder builder = new TopicApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(),null)
                    .setRootUrl("https://questionboard-155021.appspot.com/_ah/api/");
            topicApi = builder.build();
        }

        try {
            if(topic != null){
                topicApi.insert(topic).execute();
                Log.i(TAG,"insert answer");
            }
            return topicApi.list().execute().getItems();
        } catch (IOException e) {
            Log.e(TAG, e.toString());
            return new ArrayList<Topic>();
        }
    }

    protected void onPostExecute(List<Topic> result) {
        if(result != null){
            for(Topic topic : result){
                Log.i(TAG, "Content : " + topic.getNameTopic());
            }
        }
    }
}
