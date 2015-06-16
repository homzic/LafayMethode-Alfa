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
            "CREATE TABLE " + User_Data.NewUserInfo.TABLE_NAME
                    + " ("
                    + UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + User_Data.NewUserInfo.USER_NAME + " TEXT,"
                    + User_Data.NewUserInfo.USER_MOB + " TEXT,"
                    + User_Data.NewUserInfo.USER_MAIL + " TEXT);";

    private static final String CREATE_TABLE_BODY =
            "CREATE TABLE " + User_Body.newRow.TABLE_NAME
                    + " ("
                    + UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + User_Body.newRow.BODY_NECK + " TEXT,"
                    + User_Body.newRow.BODY_BICEPS + " TEXT,"
                    + User_Body.newRow.BODY_CHEST + " TEXT,"
                    + User_Body.newRow.BODY_HIP + " TEXT,"
                    + User_Body.newRow.BODY_THIGH + " TEXT,"
                    + User_Body.newRow.BODY_CALF + " TEXT,"
                    + User_Body.newRow.KEY_CREATED_AT + " DATETIME" + ")";

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
        contentValues.put(User_Data.NewUserInfo.USER_NAME, name);
        contentValues.put(User_Data.NewUserInfo.USER_MOB, mob);
        contentValues.put(User_Data.NewUserInfo.USER_MAIL, email);
        db.insert(User_Data.NewUserInfo.TABLE_NAME, null, contentValues);
        Log.e("DATABASE OPERATIONS: ", "One row inserted ...");
    }

    public Cursor getContact(String user_name, SQLiteDatabase sqLiteDatabase) {
        String[] projections = {User_Data.NewUserInfo.USER_MOB, User_Data.NewUserInfo.USER_MAIL};
        String selection = User_Data.NewUserInfo.USER_NAME + " LIKE ?";
        String selection_args[] = {user_name};
        return sqLiteDatabase.query(User_Data.NewUserInfo.TABLE_NAME, projections, selection, selection_args, null, null, null);
    }

    public Cursor getBody(SQLiteDatabase sqLiteDatabase) {
        Cursor cursor;
        String[] Selections = {User_Body.newRow.BODY_NECK, User_Body.newRow.BODY_BICEPS, User_Body.newRow.BODY_CHEST, User_Body.newRow.BODY_HIP, User_Body.newRow.BODY_THIGH, User_Body.newRow.BODY_CALF};
        cursor = sqLiteDatabase.query(User_Body.newRow.TABLE_NAME, Selections, null, null, null, null, null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS " + User_Body.newRow.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + User_Data.NewUserInfo.TABLE_NAME);
        onCreate(db);
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
        Log.e("DATABASE OPERATIONS: ", "db.close();");
    }

    /**
     * get datetime
     */
    public static String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        Log.e("DATABASE OPERATIONS: ", "date:" + date);
        return dateFormat.format(date);
    }
}
