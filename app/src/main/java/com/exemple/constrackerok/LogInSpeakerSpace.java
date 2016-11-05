package com.exemple.constrackerok;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class LogInSpeakerSpace extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_speaker_space);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void startConnection(View view) {

        // if password is correct
        // to check
        Intent intent = new Intent(this, SpeakerSpace.class);
        startActivity(intent);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("LogInSpeakerSpace Page") // TODO: Define a title for the content shown.
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

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("WelcomeActivity", "onCreateOptionsMenu");

        //Inflate the manu; this adds to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("WelcomeActivity", "onOptionsItemSelected");
        //handle action bar clicks here
//the action bar will automatically handle clicks on the home/Up button, so long
        //as we specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //nonInspection SimlifiableIfStatement
        if (id == R.id.action_information) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void actionResourceClickMenuContacts(MenuItem item) {
        Intent intent = new Intent(this, Contacts.class);
        startActivity(intent);
    }

    public void actionResourceClickMenuInfo(MenuItem item) {
        Intent intent = new Intent(this, AboutUs.class);
        startActivity(intent);
    }

    public void actionResourceClickMenuLanguage(MenuItem item) {
        Intent intent = new Intent(this, LanguageActivity.class);
        startActivity(intent);
    }
}
