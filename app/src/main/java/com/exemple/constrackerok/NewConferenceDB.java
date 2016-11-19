package com.exemple.constrackerok;

import android.provider.BaseColumns;


public class NewConferenceDB {


    public static abstract class TableUser implements BaseColumns {

        //Table name
        public static final String TABLE_NAME_USER = "User";

        //TABLE FOR SPEAKERS
        public static final String USER_ID = "idUser";
        public static final String USER_TITLE = "user_title";
        public static final String USER_NAME = "user_name";
        public static final String USER_SURNAME = "user_surname";
        public static final String USER_TEL = "user_tel";
        public static final String USER_EMAIL = "user_email";
        public static final String USER_PASSWORD = "user_pass";


        //Table user (speaker) create statement

        public static final String CREATE_TABLE_USER = "CREATE TABLE "
                + TABLE_NAME_USER + "("
                + TableUser.USER_ID + " ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableUser.USER_TITLE + " TEXT, "
                + TableUser.USER_NAME + " TEXT, "
                + TableUser.USER_SURNAME + " TEXT, "
                + TableUser.USER_TEL + " TEXT, "
                + TableUser.USER_EMAIL + " TEXT, "
                + TableUser.USER_PASSWORD + " TEXT "
                + ");";

    }

    public static abstract class TableRoom implements BaseColumns {
        //Table name
        public static final String TABLE_NAME_ROOM = "Room";

        //TABLE FOR ROOM
        public static final String ROOM_ID = "idRoom";
        public static final String ROOM_NAME = "room_name";
        public static final int ROOM_NBPEOPLE = 0;



        //Table room create statement
        public static final String CREATE_TABLE_ROOM = "CREATE TABLE "
                + TABLE_NAME_ROOM + "("
                + TableRoom.ROOM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableRoom.ROOM_NAME + " TEXT, "
                + TableRoom.ROOM_NAME + " TEXT, " +
                TableRoom.ROOM_NBPEOPLE + " INTEGER "
                + ");";


    }

    public static abstract class TableTopic implements BaseColumns {
        //Table name
        public static final String TABLE_NAME_TOPIC = "Topic";


        //TABLE FOR TOPIC
        public static final String TOPIC_ID = "idTopic";
        public static final String TOPIC_NAME = "topic_name";
        public static final String TOPIC_START_TIME = "start_time";
        public static final String TOPIC_END_TIME = "end_time";
        public static final String TOPIC_DATE = "date";
        public static String TOPIC_ID_SPEAKER = "idUser";
        public static String TOPIC_ID_ROOM = "idRoom";

        //Table topic create statement
        public static final String CREATE_TABLE_TOPIC = "CREATE TABLE "
                + TABLE_NAME_TOPIC + "("
                + TableTopic.TOPIC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableTopic.TOPIC_NAME + " TEXT, "
                + TableTopic.TOPIC_START_TIME + " TEXT, "
                + TableTopic.TOPIC_END_TIME + " TEXT, "
                + TableTopic.TOPIC_DATE + " TEXT, "
                + "FOREIGN KEY (" + TOPIC_ID_SPEAKER+ ") REFERENCES " + TableUser.TABLE_NAME_USER + " (" + TOPIC_ID + "), "
                + "FOREIGN KEY (" + TOPIC_ID_ROOM  + ") REFERENCES " + TableRoom.TABLE_NAME_ROOM + " (" + TOPIC_ID +") "
                + ");";

    }


}
