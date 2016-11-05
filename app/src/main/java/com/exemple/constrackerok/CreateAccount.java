package com.exemple.constrackerok;

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
import android.widget.Button;
import android.widget.EditText;

public class CreateAccount extends AppCompatActivity {


    //Name of the user (email), Password, Confirmed password.
    EditText USER_NAME, USER_PASS, CON_PASS;
    String user_name, user_pass, con_pass;

    //we create an object for the button
   Button REG;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //DATABASE part
        USER_NAME = (EditText) findViewById(R.id.EMailtxt);
        USER_PASS =(EditText) findViewById(R.id.Passwordtxt);
        CON_PASS = (EditText) findViewById(R.id.RepeatPasswordtxt);
REG = (Button) findViewById(R.id.NextBtn);
    }


    public void startConnection(View view) {

        Intent intent = new Intent(this, ProfilsInformations.class);
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

}
