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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.exemple.constrackerok.DataSource.UserDataSource;
import com.exemple.constrackerok.Objects.User;

public class ProfilesInformationModify extends AppCompatActivity {
    private RadioGroup groupGenderGR;
    private RadioButton radioGenderButton;

    EditText name, surname, tel;

    //declaration of variables

    String new_gender, nameStr, surnameStr, telStr, email, password;
    Context ctx = this;
    UserDataSource dop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles_information_modify);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void addListenerOnButton() {
        groupGenderGR = (RadioGroup) findViewById(R.id.groupGender);
    }

    public void startUpdateProfile(View view) {
        // here we recieve typed data from EditText, Radiobutton
// get selected radio button from radioGroup

        User speaker = new User();
        int selectedId = groupGenderGR.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        radioGenderButton = (RadioButton) findViewById(selectedId);
        new_gender = radioGenderButton.getText().toString();
        speaker.setTitle(new_gender);
        name = (EditText) findViewById(R.id.nametxt);
        nameStr = name.getText().toString();
        speaker.setName(nameStr);
        surname = (EditText) findViewById(R.id.surnametxt);
        surnameStr = surname.getText().toString();

        tel = (EditText) findViewById(R.id.phoneNumbertxt);
        telStr = tel.getText().toString();

        email = getIntent().getStringExtra("passMeUserEmail");
        speaker.setEmail(email);
   


        //check all fields to be filled it
        // if () {}...code
        UserDataSource uds = new UserDataSource(this);
        uds.updateUser(speaker);
        Toast.makeText(getBaseContext(), "Update is completed", Toast.LENGTH_LONG).show();

        //we go to next activity
        Intent intent = new Intent(this, SpeakerSpace.class);
        startActivity(intent);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("WelcomeActivity", "onCreateOptionsMenu");

        //Inflate the manu; this adds to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {

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
