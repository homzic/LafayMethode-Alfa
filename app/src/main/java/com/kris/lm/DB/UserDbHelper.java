package com.kris.lm.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class UserDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "USERINFO.DB";
    private static final int DATABASE_VERSION = 1;
    private static final String UID = "_id";


    //Tables
    private static final String CREATE_TABLE_USER =
            "CREATE TABLE " + UserContract.NewUserInfo.TABLE_NAME
                    + " ("
                    + UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + UserContract.NewUserInfo.USER_NAME + " TEXT,"
                    + UserContract.NewUserInfo.USER_MOB + " TEXT,"
                    + UserContract.NewUserInfo.USER_MAIL + " TEXT);";

    private static final String CREATE_TABLE_BODY =
            "CREATE TABLE " + UserBody.NewBody.TABLE_NAME
                    + " ("
                    + UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + UserBody.NewBody.BODY_NECK + " TEXT,"
                    + UserBody.NewBody.BODY_BICEPS + " TEXT,"
                    + UserBody.NewBody.BODY_CHEST + " TEXT,"
                    + UserBody.NewBody.BODY_HIP + " TEXT,"
                    + UserBody.NewBody.BODY_THIGH + " TEXT,"
                    + UserBody.NewBody.BODY_CALF + " TEXT,"
                    + UserBody.NewBody.KEY_CREATED_AT + " DATETIME" + ")";

    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS: ", "Database created | opened ...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_BODY);
        Log.e("DATABASE OPERATIONS: ", "Table created ..." + CREATE_TABLE_BODY);
    }


    public void addInformation(String name, String mob, String email, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.NewUserInfo.USER_NAME, name);
        contentValues.put(UserContract.NewUserInfo.USER_MOB, mob);
        contentValues.put(UserContract.NewUserInfo.USER_MAIL, email);
        db.insert(UserContract.NewUserInfo.TABLE_NAME, null, contentValues);
        Log.e("DATABASE OPERATIONS: ", "One row inserted ...");
    }

    public Cursor getContact(String user_name, SQLiteDatabase sqLiteDatabase) {
        String[] projections = {UserContract.NewUserInfo.USER_MOB, UserContract.NewUserInfo.USER_MAIL};
        String selection = UserContract.NewUserInfo.USER_NAME + " LIKE ?";
        String selection_args[] = {user_name};
        Cursor cursor = sqLiteDatabase.query(UserContract.NewUserInfo.TABLE_NAME, projections, selection, selection_args, null, null, null);
        return cursor;
    }

    public Cursor getBody(SQLiteDatabase sqLiteDatabase) {
        Cursor cursor;
        String[] Selections = {UserBody.NewBody.BODY_NECK, UserBody.NewBody.BODY_BICEPS, UserBody.NewBody.BODY_CHEST, UserBody.NewBody.BODY_HIP, UserBody.NewBody.BODY_THIGH, UserBody.NewBody.BODY_CALF};
        cursor = sqLiteDatabase.query(UserBody.NewBody.TABLE_NAME, Selections, null, null, null, null, null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS " + UserBody.NewBody.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + UserContract.NewUserInfo.TABLE_NAME);
        onCreate(db);
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    /**
     * get datetime
     */
    public static String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
