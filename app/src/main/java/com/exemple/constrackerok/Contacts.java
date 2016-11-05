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

public class Contacts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
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
