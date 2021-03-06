package com.exemple.constrackerok;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daria.myapplication.backend.roomApi.RoomApi;
import com.exemple.constrackerok.DataSource.RoomDataSource;
import com.exemple.constrackerok.DataSource.TopicUserRoomDataSource;
import com.exemple.constrackerok.DataSource.UserDataSource;
import com.exemple.constrackerok.Objects.Room;
import com.exemple.constrackerok.Objects.Topic;
import com.exemple.constrackerok.Objects.User;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

public class Participate extends AppCompatActivity {
    Context ctx = this;
    TopicUserRoomDataSource tds = new TopicUserRoomDataSource(this);
    UserDataSource uds = new UserDataSource(this);
    RoomDataSource rds = new RoomDataSource(this);
    Button btn;
    Topic topic;
    User user;
    Room room;
    int id;
    String nameTopic, date, startTime, endTime, nameSpeaker, surnameSpeaker, nameRoom, remainingPlaces;
    TextView nameTopicTV, dateTV, startTimeTV, endTimeTV, nameSpeakerTV, nameRoomTV, remainingPlacesTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent mIntent = getIntent();
        int id = mIntent.getIntExtra("passMeId", 0);
        //idStr = getIntent().getStringExtra("passMeId");
        //id = Integer.parseInt(idStr);

        topic = tds.getTopicById(id);

        nameTopic = topic.getNameTopic();
        date = topic.getDate();
        startTime = topic.getStartTime();
        endTime = topic.getEndTime();
        user = uds.getUserById(topic.getIdSpeaker());
        nameSpeaker = user.getName();
        surnameSpeaker = user.getSurname();


        /////////////////////////////////////////////////////////////
        /*
        Cloud

        com.example.daria.myapplication.backend.roomApi.RoomApi.Get.Flags roomById;
                                                                                // K = Key and V = Value
        roomById = new com.example.daria.myapplication.backend.roomApi.RoomApi.Get() ;
        RoomApi roomApi = null;

        RoomApi.Builder builder = new RoomApi.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(),null)
                .setRootUrl("https://questionboard-155021.appspot.com/_ah/api/");
        roomApi = builder.build();

        try {
            roomById=roomApi.get((long) 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new EndpointsAsyncTask_Room().execute();
        /////////////////////////////////////////////////////////////
        */

        room = rds.getRoomById(topic.getIdRoom());
        nameRoom = room.getNameRoom();
        int remainingPlacesInt = room.getNbPeople();
        remainingPlaces = Integer.toString(remainingPlacesInt);

        TextView nameTopicTV = (TextView) findViewById(R.id.topictxt);
        nameTopicTV.setText(nameTopic);

        TextView remainingPlacesTV = (TextView) findViewById(R.id.placestxt);
        remainingPlacesTV.setText(remainingPlaces);

        btn = (Button) findViewById(R.id.participateBtn);
        btn.setVisibility(View.GONE);
        TextView dateTV = (TextView) findViewById(R.id.datetxt);
        dateTV.setText(date);

        TextView startTimeTV = (TextView) findViewById(R.id.srartTimetxt);
        startTimeTV.setText(startTime);

        TextView endTimeTV = (TextView) findViewById(R.id.endTimetxt);
        endTimeTV.setText(endTime);

        TextView nameSpeakerTV = (TextView) findViewById(R.id.speakertxt);
        nameSpeakerTV.setText(nameSpeaker + " " + surnameSpeaker);

        TextView nameRoomTV = (TextView) findViewById(R.id.roomtxt);
        nameRoomTV.setText(nameRoom);

        if (remainingPlaces.equals("0")) {
            btn = (Button) findViewById(R.id.participateBtn);
            Toast.makeText(getBaseContext(), R.string.noMorPlace, Toast.LENGTH_LONG).show();
            btn.setVisibility(View.GONE);
        } else {
            btn.setVisibility(View.VISIBLE);

        }
    }


    public void startSubmitParticipation(View view) {
        Toast.makeText(getBaseContext(), R.string.ConformationOk, Toast.LENGTH_SHORT).show();
        int nb = room.getNbPeople();
        int newNB = nb-1;
        room.setNbPeople(newNB);

        rds.updateRoom1(room);
        Intent intent = new Intent(this, SearchConference.class);
        startActivity(intent);
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

}
