package com.exemple.constrackerok.DataSource;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.exemple.constrackerok.NewConferenceDB;
import com.exemple.constrackerok.NewSQLiteHelper;
import com.exemple.constrackerok.Objects.Topic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TopicUserRoomDataSource {
    private SQLiteDatabase db;


    public TopicUserRoomDataSource(Context context){
        NewSQLiteHelper sqliteHelper = NewSQLiteHelper.getInstance(context);
        db = sqliteHelper.getWritableDatabase();
    }


    /**
     * inserts a new topic
     */
    public long AddTopicByUser(Topic topic){

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();

        long id;
        ContentValues values = new ContentValues();
        values.put(NewConferenceDB.TableTopic.TOPIC_NAME, topic.getNameTopic());
        values.put(NewConferenceDB.TableTopic.TOPIC_START_TIME, topic.getStartTime());
        values.put(NewConferenceDB.TableTopic.TOPIC_END_TIME, topic.getEndTime());
        values.put(NewConferenceDB.TableTopic.TOPIC_DATE, topic.getDate());
        values.put(NewConferenceDB.TableTopic.TOPIC_ID_SPEAKER, topic.getIdSpeaker());
        values.put(NewConferenceDB.TableTopic.TOPIC_ID_ROOM, topic.getIdRoom());

        return this.db.insert(NewConferenceDB.TableTopic.TABLE_NAME_TOPIC, null, values);
    }


    /**
     * show all topics
     */
    public List<Topic> getAllTopics(){
        List<Topic> topics = new ArrayList<Topic>();
        String sql = "SELECT * FROM " + NewConferenceDB.TableTopic.TABLE_NAME_TOPIC + " ORDER BY " + NewConferenceDB.TableTopic.TOPIC_DATE;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                Topic topic = new Topic();
                topic.setIdTopic(cursor.getInt(cursor.getColumnIndex(NewConferenceDB.TableTopic.TOPIC_ID)));
                topic.setNameTopic(cursor.getString(cursor.getColumnIndex(NewConferenceDB.TableTopic.TOPIC_NAME)));
                topic.setStartTime(cursor.getString(cursor.getColumnIndex(NewConferenceDB.TableTopic.TOPIC_START_TIME)));
                topic.setEndTime(cursor.getString(cursor.getColumnIndex(NewConferenceDB.TableTopic.TOPIC_END_TIME)));
                topic.setIdSpeaker(cursor.getInt(cursor.getColumnIndex(NewConferenceDB.TableTopic.TOPIC_ID_SPEAKER)));
                topic.setIdRoom(cursor.getInt(cursor.getColumnIndex(NewConferenceDB.TableTopic.TOPIC_ID_ROOM)));

                topics.add(topic);
            } while(cursor.moveToNext());
        }

        return topics;
    }
    /**
     * show topics by user id
     */
    public Topic getTopicById(long id){
        String sql = "SELECT * FROM " + NewConferenceDB.TableTopic.TABLE_NAME_TOPIC +
                " WHERE " + NewConferenceDB.TableTopic.TOPIC_ID+ " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        Topic topic = new Topic();
        topic.setIdTopic(cursor.getInt(cursor.getColumnIndex(NewConferenceDB.TableTopic.TOPIC_ID)));
        topic.setNameTopic(cursor.getString(cursor.getColumnIndex(NewConferenceDB.TableTopic.TOPIC_NAME)));
        topic.setStartTime(cursor.getString(cursor.getColumnIndex(NewConferenceDB.TableTopic.TOPIC_START_TIME)));
        topic.setEndTime(cursor.getString(cursor.getColumnIndex(NewConferenceDB.TableTopic.TOPIC_END_TIME)));
        topic.setIdSpeaker(cursor.getInt(cursor.getColumnIndex(NewConferenceDB.TableTopic.TOPIC_ID_SPEAKER)));
        topic.setIdRoom(cursor.getInt(cursor.getColumnIndex(NewConferenceDB.TableTopic.TOPIC_ID_ROOM)));

        return topic;
    }

}
