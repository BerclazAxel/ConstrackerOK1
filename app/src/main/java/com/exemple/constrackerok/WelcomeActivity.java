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
        /*
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
        */

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
