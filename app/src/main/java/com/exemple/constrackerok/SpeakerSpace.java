package com.exemple.constrackerok;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.exemple.constrackerok.DataSource.UserDataSource;
import com.exemple.constrackerok.Objects.User;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class SpeakerSpace extends AppCompatActivity {

    //replace it by the object
    Bundle bn;
    String USERNAME, password, email;
    Context ctx = this;
    UserDataSource uds = new UserDataSource(this);
    User speakerInSpeakerSpace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker_space);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Here we receive the User email from Intent
        email = getIntent().getStringExtra("passMeUserEmail");
        speakerInSpeakerSpace = uds.getUserByEmail(email);

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }


    public void startProposeTopic(View view) {

        Intent intent = new Intent(SpeakerSpace.this, ProposeTopic.class);
        //we attache the bunddle calss to the activity
        intent.putExtra("passMeUserEmail", email);

        startActivity(intent);
    }


    //android:onClick="deleteMyProfile"
    public void deleteMyProfile(View view) {

        Context context = this.getApplicationContext();
        new AlertDialog.Builder(ctx)
                .setTitle(R.string.DeleteConfirmationString)
                .setMessage(R.string.SurDeleteString)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        // pass the id from the object
                        long id = speakerInSpeakerSpace.getIdUser();
                        uds.deleteUser(id);
                        Toast.makeText(getBaseContext(), R.string.ProfileDeletedString, Toast.LENGTH_LONG).show();
                        //finish the activity

                        Intent intent = new Intent(SpeakerSpace.this, WelcomeActivity.class);

                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();


    }

    public void modifyMyProfile(View view) {

        Intent intent = new Intent(SpeakerSpace.this, ProfilesInformationModify.class);
        intent.putExtra("passMeUserEmail", email);
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

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("SpeakerSpace Page") // TODO: Define a title for the content shown.
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

}
