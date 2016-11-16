package com.exemple.constrackerok;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class LogInSpeakerSpace extends AppCompatActivity {
    Button login;
    EditText USERNAME, USERPASS;
    String username, userpass;
    // create Object of Context class
    Context ctx = this;


    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_speaker_space);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        login = (Button) findViewById(R.id.Connect);
        USERNAME = (EditText) findViewById(R.id.emailTypedtxt);
        USERPASS = (EditText) findViewById(R.id.passwordTypedtxt);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = USERNAME.getText().toString();
                userpass = USERPASS.getText().toString();
                DatabaseOperations dop = new DatabaseOperations(ctx);
                Cursor cr = dop.getInformation(dop);
                // here we receive info of registered data
                cr.moveToFirst();
                boolean login_status = false;
                String NAME = "";
                do {
                    //column index [0], [1]
                    if (username.equals(cr.getString(0)) && (userpass.equals(cr.getString(1)))) {
                        login_status = true;
                        NAME = cr.getString(0);
                    }

                } while (cr.moveToNext()); //move untill there is a raw
                if (login_status) {

                    Toast.makeText(getBaseContext(), "Login Success", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LogInSpeakerSpace.this, SpeakerSpace.class);
                    startActivity(intent);
                    Bundle b = new Bundle();
                    //key string, value
                    b.putString("user_email", NAME);
                    intent.putExtras(b);

                } else {
                    Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

                }


            }
        });
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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

        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


}
