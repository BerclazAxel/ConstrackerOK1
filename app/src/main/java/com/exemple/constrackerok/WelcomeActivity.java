package com.exemple.constrackerok;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.exemple.constrackerok.DataSource.RoomDataSource;
import com.exemple.constrackerok.DataSource.TopicUserRoomDataSource;
import com.exemple.constrackerok.DataSource.UserDataSource;
import com.exemple.constrackerok.Objects.Room;
import com.exemple.constrackerok.Objects.Topic;
import com.exemple.constrackerok.Objects.User;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

public class WelcomeActivity extends AppCompatActivity {
    List<Room> rooms;

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        NewDataBaseHelper db = new NewDataBaseHelper(this);
        RoomDataSource rds = new RoomDataSource(this);
        UserDataSource uds = new UserDataSource(this);
        TopicUserRoomDataSource tds = new TopicUserRoomDataSource(this);
        // Inserting Rooms

        Log.d("Insert: ", "Inserting ..");

        Room r1 = new Room();
        r1.setIdRoom(1);
        r1.setNameRoom("Mont-Blanc");
        r1.setNbPeople(10);

        Room r2 = new Room();
        r2.setIdRoom(2);
        r2.setNameRoom("Mont-Dolent");
        r2.setNbPeople(25);

        Room r3 = new Room();
        r3.setIdRoom(3);
        r3.setNameRoom("Cervin");
        r3.setNbPeople(50);

        Room r4 = new Room();
        r4.setIdRoom(4);
        r4.setNameRoom("Mont-Bonvin");
        r4.setNbPeople(100);

        RoomDataSource rmds = new RoomDataSource(this);
        rmds.createRoom(r1);
        rmds.createRoom(r2);
        rmds.createRoom(r3);
        rmds.createRoom(r4);



		//Cloud
		com.example.daria.myapplication.backend.roomApi.model.Room room0 = new com.example.daria.myapplication.backend.roomApi.model.Room();
		room0.setNameRoom("Mont-Blanc");
		new EndpointsAsyncTask_Room(room0).execute();
		
		com.example.daria.myapplication.backend.roomApi.model.Room room1 = new com.example.daria.myapplication.backend.roomApi.model.Room();
		room1.setNameRoom("Mont-Dolent");
		new EndpointsAsyncTask_Room(room1).execute();
		
		com.example.daria.myapplication.backend.roomApi.model.Room room2 = new com.example.daria.myapplication.backend.roomApi.model.Room();
		room2.setNameRoom("Cervin");
		new EndpointsAsyncTask_Room(room2).execute();
		
		com.example.daria.myapplication.backend.roomApi.model.Room room3 = new com.example.daria.myapplication.backend.roomApi.model.Room();
		room3.setNameRoom("Mont-Bonvin");
		new EndpointsAsyncTask_Room(room3).execute();
		
		

        //RoomDataSource rds = new RoomDataSource(this);
        Log.d("Reading: ", "Reading all rooms..");
        List<Room> rooms = rds.getAllRooms();

        for (Room room : rooms) {
            String logR = "IdRoom: " + room.getIdRoom() + ", Room Name: " + room.getNameRoom() + ", Numbre People: " + room.getNbPeople();

            // Writing users to log
            Log.d("Rooms: ", logR);
        }

        UserDataSource uds1 = new UserDataSource(this);

        Log.d("Reading: ", "Reading all users..");
        List<User> users = uds1.getAllUsers();

        for (User user : users) {
            String logU = "IdUser: " + user.getIdUser() + ", User Title: " + user.getTitle() + ", User Name: " + user.getName()
                    + ", User Surname: " + user.getSurname() + ", Tel: " + user.getTel() + ", Email: " + user.getEmail()
                    + ", Password: " + user.getPassword();

            // Writing users to log
            Log.d("Users: ", logU);
        }

        TopicUserRoomDataSource tds1 = new TopicUserRoomDataSource(this);
        Log.d("Reading: ", "Reading all Topic..");
        List<Topic> topics = tds1.getAllTopics();

        for (Topic topic : topics) {
            String logT = "IdTopic: " + topic.getIdTopic() + ", Subject: " + topic.getNameTopic() + ", Date: " + topic.getDate()
                    + ", Start time: " + topic.getStartTime() + ", End Time: " + topic.getEndTime() + ", Id Speaker: " + topic.getIdSpeaker()
                    + ", Id Room: " + topic.getIdRoom();

            // Writing users to log
            Log.d("Topic: ", logT);
        }
    }



    public void startConferenceSearch(View view) {
        Intent intent = new Intent(this, SearchConference.class);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("WelcomeActivity", "onCreateOptionsMenu");

        //Inflate the menu; this adds to the action bar if it is present
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


    public void startSpeakerSpace(View view) {
        Intent intent = new Intent(this, LogInSpeakerSpace.class);
        startActivity(intent);
    }

    public void startToBeSpeaker(View view) {
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }


    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Welcome Page") // TODO: Define a title for the content shown.
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

}
