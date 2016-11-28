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
                + TableUser.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
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
        public static final String ROOM_NBPEOPLE = "number_people";


        //Table room create statement
        public static final String CREATE_TABLE_ROOM = "CREATE TABLE "
                + TABLE_NAME_ROOM + "("
                + TableRoom.ROOM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + TableRoom.ROOM_NAME + " TEXT, "
                + TableRoom.ROOM_NBPEOPLE + " INTEGER "
                + ");";


    }

    public static abstract class TableTopic implements BaseColumns {
        //Table name
        public static final String TABLE_NAME_TOPIC = "Topic";


        //TABLE FOR TOPIC
        public static final String TOPIC_ID = "IdTopic";
        public static final String TOPIC_NAME = "Topic_name";
        public static final String TOPIC_START_TIME = "Start_time";
        public static final String TOPIC_DATE = "Date";
        public static final String TOPIC_END_TIME = "End_time";

        public static final String TOPIC_ID_SPEAKER = "IdSpealker";
        public static final String TOPIC_ID_ROOM = "IdRoom";

        //Table topic create statement
        public static final String CREATE_TABLE_TOPIC = "CREATE TABLE "
                + TABLE_NAME_TOPIC + "("
                + TableTopic.TOPIC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + TableTopic.TOPIC_NAME + " TEXT, "
                + TableTopic.TOPIC_DATE + " TEXT, "
                + TableTopic.TOPIC_START_TIME + " TEXT, "
                + TableTopic.TOPIC_END_TIME + " TEXT, "
                + TableTopic.TOPIC_ID_SPEAKER + " INTEGER, "
                + TableTopic.TOPIC_ID_ROOM + " INTEGER "
                + " );";


        /*
                        + TABLE_NAME_TOPIC + "("
                + TableTopic.TOPIC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + TableTopic.TOPIC_NAME + " TEXT, "
                + TableTopic.TOPIC_DATE + " TEXT, "
                + TableTopic.TOPIC_START_TIME + " TEXT, "
                + TableTopic.TOPIC_END_TIME + " TEXT, "
                + TableTopic.TOPIC_ID_SPEAKER + " INTEGER, "
                + TableTopic.TOPIC_ID_ROOM + " INTEGER, "
                + "FOREIGN KEY (" + TOPIC_ID_SPEAKER + ") REFERENCES " + TableUser.TABLE_NAME_USER + " (" + TableUser.USER_ID + "), "
                + "FOREIGN KEY (" + TOPIC_ID_ROOM  + ") REFERENCES " + TableRoom.TABLE_NAME_ROOM + " (" + TableRoom.ROOM_ID + ") "
                + ");";

         */
    }


}