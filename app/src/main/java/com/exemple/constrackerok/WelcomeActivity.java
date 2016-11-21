package com.exemple.constrackerok;

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

public class WelcomeActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        NewDataBaseHelper db = new NewDataBaseHelper(this);
        // Inserting Rooms
        Log.d("Insert: ", "Inserting ..");
        Room r1 = new Room(); //id gets automatically incremented
        Room r2 = new Room();
        Room r3 = new Room();
        User speaker1 = new User();
        User speaker2 = new User();
        Topic top1 = new Topic();
        Topic top2 = new Topic();

        RoomDataSource rds = new RoomDataSource(this);
        UserDataSource uds = new UserDataSource(this);
        TopicUserRoomDataSource tds = new TopicUserRoomDataSource(this);

        speaker1.setTitle("Mr.");
        speaker1.setName("Bob");
        speaker1.setSurname("Marley");
        speaker1.setTel("+41 78 800 11 12");
        speaker1.setEmail("bm@gmail.com");
        speaker1.setPassword("test");

        speaker2.setTitle("Mrs.");
        speaker2.setName("Sarah");
        speaker2.setSurname("Gellar");
        speaker2.setTel("+41 78 500 10 10");
        speaker2.setEmail("sg@gmail.com");
        speaker2.setPassword("test");

        r1.setNameRoom("Moon");
        r1.setNbPeople(10);
        r2.setNameRoom("Round");
        r2.setNbPeople(5);
        r3.setNameRoom("Polo");
        r3.setNbPeople(25);

        top1.setNameTopic("Life on the Moon");
        top1.setDate("08.01.2017");
        top1.setStartTime("10:00");
        top1.setEndTime("12:00");
        top1.setIdRoom(1);
        top1.setIdSpeaker(1);

        top2.setNameTopic("Philosophy in JAVA");
        top2.setDate("03.03.2017");
        top2.setStartTime("18:00");
        top2.setEndTime("20:00");
        top2.setIdRoom(3);
        top2.setIdSpeaker(2);

        speaker1.setIdUser((int) uds.createUser(speaker1));
        speaker2.setIdUser((int) uds.createUser(speaker2));

        r1.setIdRoom((int) rds.createRoom(r1));
        r2.setIdRoom((int) rds.createRoom(r2));
        r3.setIdRoom((int) rds.createRoom(r3));

        top1.setIdTopic((int) tds.AddTopicByUser(top1));
        top2.setIdTopic((int) tds.AddTopicByUser(top2));

        //close db instance
        NewDataBaseHelper sqlHelper = NewDataBaseHelper.getInstance(this);
        sqlHelper.getWritableDatabase().close();

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

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
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
