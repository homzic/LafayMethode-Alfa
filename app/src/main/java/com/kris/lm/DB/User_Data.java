package com.kris.lm.DB;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class User_Data {

    private static final String USER_NAME = "user_name";
    private static final String USER_MOB = "user_mob";
    private static final String USER_MAIL = "user_mail";
    public static final String TABLE_NAME = "table_user";
    private static final String UID = "_id";

    public static final String CREATE_TABLE_USER =
            "CREATE TABLE " + TABLE_NAME
                    + " ("
                    + UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + USER_NAME + " TEXT,"
                    + USER_MOB + " TEXT,"
                    + USER_MAIL + " TEXT);";

    public static void addInformation(String name, String mob, String email, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, name);
        contentValues.put(USER_MOB, mob);
        contentValues.put(USER_MAIL, email);
        db.insert(TABLE_NAME, null, contentValues);
        Log.e("DATABASE OPERATIONS: ", "One row inserted ...");
    }

    public static Cursor getContact(String user_name, SQLiteDatabase sqLiteDatabase) {
        String[] projections = {USER_MOB, USER_MAIL};
        String selection = USER_NAME + " LIKE ?";
        String selection_args[] = {user_name};
        return sqLiteDatabase.query(TABLE_NAME, projections, selection, selection_args, null, null, null);
    }


}
