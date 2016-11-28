package com.exemple.constrackerok.DataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.exemple.constrackerok.NewConferenceDB;
import com.exemple.constrackerok.NewDataBaseHelper;
import com.exemple.constrackerok.Objects.Topic;
import com.exemple.constrackerok.Objects.User;

import java.util.ArrayList;
import java.util.List;


public class UserDataSource {

    private SQLiteDatabase db;
    private Context context;

    public UserDataSource(Context context){
        NewDataBaseHelper sqliteHelper = NewDataBaseHelper.getInstance(context);
        db = sqliteHelper.getWritableDatabase();
        this.context = context;

    }

    /**
     * Insert a new user
     */
    public long createUser(User user){
        long id;
        ContentValues values = new ContentValues();
        values.put(NewConferenceDB.TableUser.USER_TITLE, user.getTitle());
        values.put(NewConferenceDB.TableUser.USER_NAME, user.getName());
        values.put(NewConferenceDB.TableUser.USER_SURNAME, user.getSurname());
        values.put(NewConferenceDB.TableUser.USER_TEL, user.getTel());
        values.put(NewConferenceDB.TableUser.USER_EMAIL, user.getEmail());
        values.put(NewConferenceDB.TableUser.USER_PASSWORD, user.getPassword());

        id = this.db.insert(NewConferenceDB.TableUser.TABLE_NAME_USER, null, values);

        return id;
    }

    /**
     * Find one User by Id
     */
    public User getUserById(int id){
        String sql = "SELECT * FROM " + NewConferenceDB.TableUser.TABLE_NAME_USER +
                " WHERE " + NewConferenceDB.TableUser.USER_ID + " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        User user = new User();
        user.setIdUser(cursor.getInt(cursor.getColumnIndex(NewConferenceDB.TableUser.USER_ID)));
        user.setTitle(cursor.getString(cursor.getColumnIndex(NewConferenceDB.TableUser.USER_TITLE)));
        user.setName(cursor.getString(cursor.getColumnIndex(NewConferenceDB.TableUser.USER_NAME)));
        user.setSurname(cursor.getString(cursor.getColumnIndex(NewConferenceDB.TableUser.USER_SURNAME)));
        user.setTel(cursor.getString(cursor.getColumnIndex(NewConferenceDB.TableUser.USER_TEL)));
        user.setEmail(cursor.getString(cursor.getColumnIndex(NewConferenceDB.TableUser.USER_EMAIL)));
        user.setPassword(cursor.getString(cursor.getColumnIndex(NewConferenceDB.TableUser.USER_PASSWORD)));

        return user;
    }

    /**
     * Find one User by Email
     */
    public User getUserByEmail(String email){
        String sql = "SELECT * FROM " + NewConferenceDB.TableUser.TABLE_NAME_USER +
                " WHERE " + NewConferenceDB.TableUser.USER_EMAIL + " = '" + email + "'";

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        User user = new User();
        user.setIdUser(cursor.getInt(cursor.getColumnIndex(NewConferenceDB.TableUser.USER_ID)));
        user.setTitle(cursor.getString(cursor.getColumnIndex(NewConferenceDB.TableUser.USER_TITLE)));
        user.setName(cursor.getString(cursor.getColumnIndex(NewConferenceDB.TableUser.USER_NAME)));
        user.setSurname(cursor.getString(cursor.getColumnIndex(NewConferenceDB.TableUser.USER_SURNAME)));
        user.setTel(cursor.getString(cursor.getColumnIndex(NewConferenceDB.TableUser.USER_TEL)));
        user.setEmail(cursor.getString(cursor.getColumnIndex(NewConferenceDB.TableUser.USER_EMAIL)));
        user.setPassword(cursor.getString(cursor.getColumnIndex(NewConferenceDB.TableUser.USER_PASSWORD)));

        return user;
    }

    /**
     * Get all Users
     */
    public List<User> getAllUsers(){
        List<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM " + NewConferenceDB.TableUser.TABLE_NAME_USER + " ORDER BY " + NewConferenceDB.TableUser.USER_SURNAME;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                User user = new User();
                user.setIdUser(cursor.getInt(cursor.getColumnIndex(NewConferenceDB.TableUser.USER_ID)));
                user.setTitle(cursor.getString(cursor.getColumnIndex(NewConferenceDB.TableUser.USER_TITLE)));
                user.setName(cursor.getString(cursor.getColumnIndex(NewConferenceDB.TableUser.USER_NAME)));
                user.setSurname(cursor.getString(cursor.getColumnIndex(NewConferenceDB.TableUser.USER_SURNAME)));
                user.setTel(cursor.getString(cursor.getColumnIndex(NewConferenceDB.TableUser.USER_TEL)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(NewConferenceDB.TableUser.USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(NewConferenceDB.TableUser.USER_PASSWORD)));

                users.add(user);
            } while(cursor.moveToNext());
        }

        return users;
    }

    /**
     *  Update a User
     */
    public int updateUser(User user){
        ContentValues values = new ContentValues();
        values.put(NewConferenceDB.TableUser.USER_TITLE, user.getTitle());
        values.put(NewConferenceDB.TableUser.USER_NAME, user.getName());
        values.put(NewConferenceDB.TableUser.USER_SURNAME, user.getSurname());
        values.put(NewConferenceDB.TableUser.USER_TEL, user.getTel());
        values.put(NewConferenceDB.TableUser.USER_EMAIL, user.getEmail());
        //values.put(NewConferenceDB.TableUser.USER_PASSWORD, user.getPassword());

        return this.db.update(NewConferenceDB.TableUser.TABLE_NAME_USER, values, NewConferenceDB.TableUser.USER_ID + " = ?",
                new String[] { String.valueOf(user.getIdUser()) });
    }


     public String searchPass (String email) {
              String sql = "SELECT " + NewConferenceDB.TableUser.USER_EMAIL + ", "
       + NewConferenceDB.TableUser.USER_PASSWORD + " FROM " + NewConferenceDB.TableUser.TABLE_NAME_USER;
               Cursor cursor = db.rawQuery(sql, null);
       String a, b;
       b = "not found";

       if (cursor.moveToFirst()) {
           do {
               a = cursor.getString(0);

               if (a.equals(email)) {
                   b = cursor.getString(1);
                   break;
               }
           }
           while (cursor.moveToNext());
       }
       return b ;
   }



    /**
     * Delete a User - this will also delete all records
     * for the user
     */

    public void deleteUser(long id) {

            TopicUserRoomDataSource pra = new TopicUserRoomDataSource(context);
            //get all records of the user
            List<Topic> topics = pra.getAllTopicsByUser(id);

            for (Topic topic : topics) {
                pra.deleteTopic(topic.getIdTopic());
            }

            //delete the user
            this.db.delete(NewConferenceDB.TableUser.TABLE_NAME_USER, NewConferenceDB.TableUser.USER_ID + " = ?",
                    new String[]{String.valueOf(id)});

        }


    }



