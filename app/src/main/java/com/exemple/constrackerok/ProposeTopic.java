package com.exemple.constrackerok;

import android.accessibilityservice.AccessibilityService;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;
import android.app.DialogFragment;
import android.widget.TimePicker;
import android.widget.Toast;


import com.exemple.constrackerok.DataSource.RoomDataSource;
import com.exemple.constrackerok.DataSource.TopicUserRoomDataSource;
import com.exemple.constrackerok.DataSource.UserDataSource;
import com.exemple.constrackerok.Objects.Room;
import com.exemple.constrackerok.Objects.Topic;
import com.exemple.constrackerok.Objects.User;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.exemple.constrackerok.R.id.room;

public class ProposeTopic extends AppCompatActivity {
    private String email;
    List<Room> rooms;
    Context ctx = this;
    RoomDataSource rds = new RoomDataSource(this);
    Spinner spinner;
    UserDataSource uds = new UserDataSource(this);
    User speaker;
    Room r;
    String[] spinnerLists;
    int[] spinnerListSave;

    //ImageView Iv = (ImageView) findViewById(R.id.roomList);
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propose_topic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        email = getIntent().getStringExtra("passMeUserEmail");
        speaker = uds.getUserByEmail(email);

        rooms = rds.getAllRooms();

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        spinner = (Spinner) findViewById(R.id.spinnerRoom);

        spinnerLists = new String[rooms.size()];
        spinnerListSave = new int[rooms.size()];

        for (int i = 0; i < rooms.size(); i++) {
            spinnerLists[i] = rooms.get(i).getNameRoom() + " - " + rooms.get(i).getNbPeople() + getString(R.string.LabelPersons);
            spinnerListSave[i] = rooms.get(i).getIdRoom();
        }
        if (spinnerLists.length > 0) {

            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, spinnerLists);
            spinner.setAdapter(adapter);
        }

    }
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.content_propose_topic, parent, false);

        return rowView;
    }

    public void startSendProposedTopic(View view) throws ParseException {
        String nameTopic, dateTopic, startTimeTopic, endTimeTopic;
        EditText name = (EditText) findViewById(R.id.editTextName);
        nameTopic = name.getText().toString();
        EditText date = (EditText) findViewById(R.id.editTextDate);
        dateTopic = date.getText().toString();
        EditText startTime = (EditText) findViewById(R.id.editTextStartTime);
        startTimeTopic = startTime.getText().toString();
        EditText endTime = (EditText) findViewById(R.id.editEndTime);
        endTimeTopic = endTime.getText().toString();
        EditText id = (EditText) findViewById(R.id.editTextDate);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date startTiime = null;
        Date endTiime = null;
        Date FormatDate = null;

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        DateFormat formatter = new SimpleDateFormat("HH:MM");


        if (!(dateTopic.isEmpty())) {
                FormatDate = sdf.parse(dateTopic);
        }

        if (nameTopic.isEmpty() || dateTopic.isEmpty() || startTimeTopic.isEmpty() || endTimeTopic.isEmpty() || dateTopic.isEmpty()) {

            Toast.makeText(getBaseContext(), R.string.Completeregistr, Toast.LENGTH_SHORT).show();

        } else
            startTiime = formatter.parse(startTimeTopic);
            endTiime = formatter.parse(endTimeTopic);

            if (endTiime.before(startTiime)) {
            Toast.makeText(getBaseContext(), R.string.TimeWarning, Toast.LENGTH_SHORT).show();

        } else if (FormatDate.before(tomorrow)) {
            Toast.makeText(getBaseContext(), R.string.Presentationdate, Toast.LENGTH_SHORT).show();
        } else {
            Topic t = new Topic();
            t.setNameTopic(nameTopic);
            t.setDate((dateTopic));
            t.setStartTime((startTimeTopic));
            t.setEndTime((endTimeTopic));
            t.setDate((dateTopic));

            int idSpeaker = speaker.getIdUser();
            t.setIdSpeaker(idSpeaker);

            int no = spinner.getSelectedItemPosition();
                System.out.println(no);

            t.setIdRoom(spinnerListSave[no]);

            TopicUserRoomDataSource turds = new TopicUserRoomDataSource(this);
            turds.AddTopic(t);
                Toast.makeText(getBaseContext(), R.string.TopicCreate, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);


        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ProposeTopic Page") // TODO: Define a title for the content shown.
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