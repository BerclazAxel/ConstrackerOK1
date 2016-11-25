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
import android.widget.TextView;
import android.widget.Toast;

import com.exemple.constrackerok.DataSource.UserDataSource;
import com.exemple.constrackerok.Objects.User;

import java.util.List;

public class ProfilesInformationModify extends AppCompatActivity {
    private RadioGroup groupGenderGR;
    private RadioButton radioGenderButton;
    private EditText name, surname, tel;
    private String new_gender, nameStr, surnameStr, telStr, email, password, genderUser, nameUser, surnameUser, telephoneUser;
    Context ctx = this;
    UserDataSource uds = new UserDataSource(this);
    User speaker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles_information_modify);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        email = getIntent().getStringExtra("passMeUserEmail");
        speaker = uds.getUserByEmail(email);

        addListenerOnButton();

        genderUser = speaker.getTitle();
        nameUser = speaker.getName();
        surnameUser = speaker.getSurname();
        telephoneUser = speaker.getTel();


        int selectedId = groupGenderGR.getCheckedRadioButtonId();
        radioGenderButton = (RadioButton) findViewById(selectedId);


        RadioButton rbu1 = (RadioButton) findViewById(R.id.maleBtn);
        RadioButton rbu2 = (RadioButton) findViewById(R.id.femaleBtn);


        if (genderUser.equalsIgnoreCase("Male") || genderUser.equalsIgnoreCase("Homme")) {
            rbu1.setChecked(true);
        } else if (genderUser.equalsIgnoreCase("Female") || genderUser.equalsIgnoreCase("Femme")) {

            rbu2.setChecked(true);
        } else {
            rbu1.setChecked(true);
        }


        name = (EditText) findViewById(R.id.nametxt);
        name.setText(nameUser, TextView.BufferType.EDITABLE);

        surname = (EditText) findViewById(R.id.surnametxt);
        surname.setText(surnameUser, TextView.BufferType.EDITABLE);

        tel = (EditText) findViewById(R.id.phoneNumbertxt);
        tel.setText(telephoneUser, TextView.BufferType.EDITABLE);


        // new_gender = radioGenderButton.getText().toString();
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


    }

    public void addListenerOnButton() {
        groupGenderGR = (RadioGroup) findViewById(R.id.groupGender);
    }



    public void startUpdateProfile(View view) {
        // here we recieve typed data from EditText, Radiobutton
// get selected radio button from radioGroup


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
        speaker.setSurname(surnameStr);

        tel = (EditText) findViewById(R.id.phoneNumbertxt);
        telStr = tel.getText().toString();
        speaker.setTel(telStr);

        email = getIntent().getStringExtra("passMeUserEmail");
        speaker.setEmail(email);


        //check all fields to be filled it
        // if () {}...code
        UserDataSource uds = new UserDataSource(this);
        uds.updateUser(speaker);
        //Toast.makeText(getBaseContext(), R.string.UpdateCompleteString, Toast.LENGTH_LONG).show();

        //we go to next activity
        email = getIntent().getStringExtra("passMeUserEmail");

        Intent intent = new Intent(this, SpeakerSpace.class);
        intent.putExtra("passMeUserEmail", email);
        startActivity(intent);

        // Reading all users
        Log.d("Reading: ", "Reading all users..");
        List<User> users = uds.getAllUsers();

        for (User user : users) {
            String logU = "IdUser: " + user.getIdUser() + " , Title: " + user.getTitle() + " , Name: " + user.getName()
                    + " , Surname: " + user.getSurname() + " , Tel: " + user.getTel() + " , Email: " + user.getEmail()
                    + " , Password: " + user.getPassword();
            // Writing users to log
            Log.d("Users: ", logU);
        }
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
