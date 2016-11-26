package com.exemple.constrackerok;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.exemple.constrackerok.DataSource.TopicUserRoomDataSource;
import com.exemple.constrackerok.Objects.Topic;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

public class SearchConference extends AppCompatActivity {
    private TopicUserRoomDataSource tds;
    private GoogleApiClient client;
    private List<Topic> listTopic;
    Topic topic = new Topic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_conference);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showListTopics();

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public void showListTopics() {

        tds = new TopicUserRoomDataSource(this);
        listTopic = tds.getAllTopics();


        final String[] topicList = new String[listTopic.size()];
        for (int i = 0; i < listTopic.size(); i++) {
            topicList[i] = listTopic.get(i).getNameTopic() + ", " + listTopic.get(i).getDate();
        }
        //final String [] languageList = getResources().getStringArray(R.array.language_list);
        if (topicList.length > 0) {
            ListView list;

            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.content_search_conference, topicList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    final int pos = position;
                    View view;
                    if (convertView == null) {
                        LayoutInflater inflater = getLayoutInflater();
                        view = inflater.inflate(R.layout.content_search_conference, parent, false);
                    } else {
                        view = convertView;
                    }
                    TextView textView1 = (TextView) view.findViewById(R.id.template_list_topic_name);
                    textView1.setHeight(100);
                    textView1.setText(topicList[position]);

                    return view;
                }
            };
            list = (ListView) findViewById(R.id.LVTopic);
            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(SearchConference.this, Participate.class);
                    intent.putExtra("passMeId", listTopic.get(position).getIdTopic());
                    startActivity(intent);
                }
            });
        }
    }





    public void startSearch(View view) {
        Intent intent = new Intent(this, ConferenceData.class);
        startActivity(intent);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("SearchConference Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the manu; this adds to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
// handle presses on the action bar item
        switch (item.getItemId()) {

            case R.id.action_information:
                startActivity(new Intent(this, AboutUs.class));
                return true;

            case R.id.action_contacts:
                startActivity(new Intent(this, Contacts.class));
                return true;

            case R.id.action_language:
                startActivity(new Intent(this, LanguageActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void startDateFrom(View view) {
    }
    public void startDateTo(View view) {
    }
}
