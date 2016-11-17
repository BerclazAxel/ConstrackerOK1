package com.exemple.constrackerok;


import android.provider.BaseColumns;

import java.util.Date;

public class TableData {

    public TableData() {
    }

    public static abstract class TableInfo implements BaseColumns {

        public static final String DATABASE_USER = "user_table";
        public static final String DATABASE_TOPIC = "topic_table";
        public static final String DATABASE_ROOM= "room_table";

        //TABLE FOR SPEAKERS
        public static final int USER_ID = 0;
        public static final String USER_TITLE = "user_title";
        public static final String USER_NAME_USER = "user_name";
        public static final String USER_SURNAME = "user_surname";
        public static final String USER_TEL = "user_tel";
        public static final String USER_EMAIL = "user_email";
        public static final String USER_PASSWORD = "user_pass";

        public static final String TABLE_NAME_USER = "reg_info";


        //TABLE FOR ROOM
        public static final int ROOM_ID = 0;
        public static final String ROOM_NAME = "room_name";
        public static final int ROOM_NBPEOPLE = 0;

        public static final String TABLE_NAME_ROOM = "room_info";


        //TABLE FOR TOPIC
        public static final int TOPIC_ID = 0;
        public static final String TOPIC_NAME = "topic_name";
        public static final String TOPIC_START_TIME = "start_time";
        public static final String TOPIC_END_TIME = "end_time";
        public static Date TOPIC_DATE;
        public static int TOPIC_ID_SPEAKER;
        public static int TOPIC_ID_ROOM;

        public static final String TABLE_NAME_TOPIC = "topic_info";

    }


}
