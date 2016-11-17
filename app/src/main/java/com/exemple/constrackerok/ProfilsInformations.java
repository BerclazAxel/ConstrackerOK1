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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class ProfilsInformations extends AppCompatActivity {
    private RadioGroup groupGenderGR;
    private RadioButton radioGenderButton;
    EditText New_gender, New_name, New_surname, New_telephone;

    //declaration of variables
    String user_name, user_pass;
    String new_gender, new_name, new_surname, new_telephone;
    Context ctx = this;
    OpenHelperUSERdb dop;


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
        int selectedId = groupGenderGR.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        radioGenderButton = (RadioButton) findViewById(selectedId);
        new_gender = radioGenderButton.getText().toString();

        New_name = (EditText) findViewById(R.id.newNametxt);
        new_name = New_name.getText().toString();

        New_surname = (EditText) findViewById(R.id.newSurnametxt);
        new_surname = New_surname.getText().toString();

        New_telephone = (EditText) findViewById(R.id.newPhoneNumbertxt);
        new_telephone = New_telephone.getText().toString();

        dop = new OpenHelperUSERdb(ctx);
        dop.updateUserInfo(dop, user_name, user_pass, new_name);
        Toast.makeText(getBaseContext(), "Modification Success", Toast.LENGTH_LONG).show();
        // to get Bundle values
        // we recive it thanks to the key values
        //Bundle bn = getIntent().getExtras();
        //user_name = bn.getString("user_name");
        //user_pass = bn.getString("user_pass");

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
