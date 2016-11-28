package com.exemple.constrackerok;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.exemple.constrackerok.DataSource.UserDataSource;
import com.exemple.constrackerok.Objects.User;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

@SuppressLint("Registered")
public class ProfilsInformationsNew extends AppCompatActivity {
    private RadioGroup groupGenderGR;
    private RadioButton radioGenderButton;


    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profils_informations);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        addListenerOnButton();

    }

    public void addListenerOnButton() {
        groupGenderGR = (RadioGroup) findViewById(R.id.groupGender);
    }

    public void startSubmitProfile(View view) {
        // here we recieve typed data from EditText, Radiobutton
        // get selected radio button from radioGroup
        User speaker = new User ();

        int selectedId = groupGenderGR.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        radioGenderButton = (RadioButton) findViewById(selectedId);
        speaker.setTitle(radioGenderButton.getText().toString());

        EditText name = (EditText) findViewById(R.id.nametxt);
        String nameStr = name.getText().toString();
        speaker.setName(nameStr);

        EditText surname = (EditText) findViewById(R.id.surnametxt);
        String surnameStr = name.getText().toString();
        speaker.setName(surnameStr);

        EditText telephone = (EditText) findViewById(R.id.phoneNumbertxt);
        String telephoneStr = telephone.getText().toString();
        speaker.setTel(telephoneStr);

        //to get email + password from Intent - previous activity
        speaker.setEmail(getIntent().getStringExtra("passMeUserEmail"));
        speaker.setPassword(getIntent().getStringExtra("passMeUserPassword"));

        UserDataSource uds = new UserDataSource(this);
        uds.createUser(speaker);

        //check all fields to be filled it
        Toast.makeText(getBaseContext(), R.string.RegistrationSuccessString, Toast.LENGTH_LONG).show();

        //check all fields to be filled it

        Intent intent = new Intent(this, SpeakerSpace.class);
        startActivity(intent);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ProfilsInformations Page") // TODO: Define a title for the content shown.
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
