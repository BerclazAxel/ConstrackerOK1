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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.exemple.constrackerok.DataSource.UserDataSource;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class CreateAccount extends AppCompatActivity {


    //Name of the user (email), Password, Confirmed password.
    EditText USER_EMAIL, USER_PASS, CON_PASS;
    String user_email, user_pass, con_pass;
    //we create an object for the button
    Button REG;
    Context ctx = this;
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //DATABASE part
        USER_EMAIL = (EditText) findViewById(R.id.EMailtxt);
        USER_PASS = (EditText) findViewById(R.id.Passwordtxt);
        CON_PASS = (EditText) findViewById(R.id.RepeatPasswordtxt);
        REG = (Button) findViewById(R.id.NextBtn);


        REG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_email = USER_EMAIL.getText().toString();
                user_pass = USER_PASS.getText().toString();
                con_pass = CON_PASS.getText().toString();

                if (!(user_pass.equals(con_pass))) {
                    Toast.makeText(getBaseContext(), "Passwords are not matching", Toast.LENGTH_LONG).show();
                    //and we reset all fields
                    USER_EMAIL.setText("");
                    USER_PASS.setText("");
                    CON_PASS.setText("");

                } else {


                    Toast.makeText(getBaseContext(), "Click the button Continue", Toast.LENGTH_SHORT).show();
                    //here we pass data to another activity
                    Intent intent = new Intent(CreateAccount.this, ProfilsInformations.class);
                    intent.putExtra("passMeUserEmail", user_email);
                    intent.putExtra("passMeUserPassword", user_pass);
                    startActivity(intent);
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
                .setName("CreateAccount Page") // TODO: Define a title for the content shown.
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
