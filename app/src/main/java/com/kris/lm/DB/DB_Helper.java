package com.kris.lm.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DB_Helper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "LAFAY_METHODE.DB";
    private static final int DATABASE_VERSION = 3;
    public SQLiteDatabase sqLiteDatabase;

    public DB_Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(User_Data.CREATE_TABLE_USER);
        Log.e("DATABASE OPERATIONS: ", "Table created ..." + User_Data.CREATE_TABLE_USER);
        db.execSQL(User_Body.CREATE_TABLE_BODY);
        Log.e("DATABASE OPERATIONS: ", "Table created ..." + User_Body.CREATE_TABLE_BODY);
        db.execSQL(Table_Exercises.CREATE_TABLE_EXERCISES);
        Log.e("DATABASE OPERATIONS: ", "Table created ..." + Table_Exercises.CREATE_TABLE_EXERCISES);
        db.execSQL(Table_Training.CREATE_TABLE_TRAINING);
        Log.e("DATABASE OPERATIONS: ", "Table created ..." + Table_Training.CREATE_TABLE_TRAINING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + User_Body.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + User_Data.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Table_Exercises.TABLE_NAME);
        Log.e("DATABASE OPERATIONS: ", "Table onUpgrade ..." + Table_Exercises.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Table_Training.TABLE_NAME);
        onCreate(db);

    }

    // closing database
    public static void closeDB(SQLiteDatabase db) {
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

    //------Dodawanie danych i funkcje


}
