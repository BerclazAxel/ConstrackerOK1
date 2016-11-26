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
import android.widget.TextView;

import com.exemple.constrackerok.DataSource.RoomDataSource;
import com.exemple.constrackerok.DataSource.TopicUserRoomDataSource;
import com.exemple.constrackerok.DataSource.UserDataSource;
import com.exemple.constrackerok.Objects.Room;
import com.exemple.constrackerok.Objects.Topic;
import com.exemple.constrackerok.Objects.User;

public class Participate extends AppCompatActivity {
    Context ctx = this;
    TopicUserRoomDataSource tds = new TopicUserRoomDataSource(this);
    UserDataSource uds = new UserDataSource(this);
    RoomDataSource rds = new RoomDataSource(this);
    Topic topic;
    User user;
    Room room;
    String idStr;
    int id, idRoom, idSpeaker;
    String nameTopic, date, startTime, endTime, nameSpeaker, surnameSpeaker, nameRoom, remainingPlaces;
    TextView nameTopicTV, dateTV, startTimeTV, endTimeTV, nameSpeakerTV, nameRoomTV, remainingPlacesTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        idStr = getIntent().getStringExtra("passMeId");
        id = Integer.parseInt(idStr);
        topic = tds.getTopicById(id);
        idRoom = topic.getIdRoom();
        idSpeaker = topic.getIdSpeaker();


        nameTopic = topic.getNameTopic();
        date = topic.getDate();
        startTime = topic.getStartTime();
        endTime = topic.getEndTime();
        user = uds.getUserById(idSpeaker);
        nameSpeaker = user.getName();
        surnameSpeaker = user.getSurname();

        room = rds.getRoomById(idRoom);
        nameRoom = room.getNameRoom();
        int remainingPlacesInt = room.getNbPeople();
        remainingPlaces = Integer.toString(remainingPlacesInt);

        TextView nameTopicTV = (TextView) findViewById(R.id.topictxt);
        nameTopicTV.setText(nameTopic);

        TextView remainingPlacesTV = (TextView) findViewById(R.id.placestxt);
        remainingPlacesTV.setText(remainingPlaces);

        TextView dateTV = (TextView) findViewById(R.id.datetxt);
        dateTV.setText(date);

        TextView startTimeTV = (TextView) findViewById(R.id.srartTimetxt);
        startTimeTV.setText(startTime);

        TextView endTimeTV = (TextView) findViewById(R.id.endTimetxt);
        endTimeTV.setText(endTime);

        TextView nameSpeakerTV = (TextView) findViewById(R.id.speakertxt);
        nameSpeakerTV.setText(nameSpeaker + " " + surnameSpeaker.toUpperCase());

        TextView nameRoomTV = (TextView) findViewById(R.id.roomtxt);
        nameRoomTV.setText(nameRoom);
    }


    public void startSubmitParticipation(View view) {
        rds.updateRoom(room);

        Intent intent = new Intent(this, SearchConference.class);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("WelcomeActivity", "onCreateOptionsMenu");

        //Inflate the manu; this adds to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("WelcomeActivity", "onOptionsItemSelected");
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
