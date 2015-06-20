package com.kris.lm.DB;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class Table_Training {
    private static final String UID = "_id";
    public static final String TABLE_NAME = "table_training";
    private static final String ID_TRAINING = "ID_training";
    private static final String DATE = "date";
    private static final String LEVEL = "level";
    private static final String SERIA = "seria";
    private static final String REPEATS = "repeats";
    private static final String EXC_ID = "exc_ID";
    public static final String EXC_NAME = "exc_name";

    public static final String CREATE_TABLE_TRAINING =
            "CREATE TABLE " + TABLE_NAME
                    + " (" +
                    UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + ID_TRAINING + " INT, "
                    + DATE + " DATETIME, "
                    + LEVEL + " TEXT, "
                    + EXC_ID + " INT, "
                    + EXC_NAME + " TEXT,"
                    + SERIA + " INT, "
                    + REPEATS + " INT, "+
                    "FOREIGN KEY(" + Table_Exercises.EXC_ID + " ) REFERENCES "
                    + Table_Exercises.TABLE_NAME + " (" + EXC_ID + ") ON DELETE CASCADE" +
                    ");";

    public static void addTrainingRow(String level, String exc_name, Integer seria, Integer repeats, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(ID_TRAINING, getLastTrainingId());
        values.put(LEVEL, level);
        values.put(EXC_ID, getExcID());
        values.put(EXC_NAME, exc_name);
        values.put(SERIA, seria);
        values.put(REPEATS, repeats);
        values.put(DATE, getDateTime());
        db.insert(TABLE_NAME, null, values);
        Log.e("DATABASE OPERATIONS: ", "One row Training inserted..." + values);
    }

   public static int getLastTrainingId(){
       return 0;
   };
    public static int getExcID(){
        return 0;

    }

    public static String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        Log.e("DATABASE OPERATIONS: ", "date:" + date);
        return dateFormat.format(date);
    }
}
