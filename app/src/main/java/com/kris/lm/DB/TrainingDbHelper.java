package com.kris.lm.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.kris.lm.DB.Table_Exercises.NewExercise;
import static com.kris.lm.DB.Table_Training.NewTraining;

public class TrainingDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "LAFAY_METHODE.DB";
    private static final int DATABASE_VERSION = 3;
    private static final String UID = "_id";

    private static final String CREATE_TABLE_EXERCISES =
            "CREATE TABLE " + NewExercise.TABLE_NAME
                    + " (" +
                    NewExercise.EXC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + NewExercise.EXC_NAME + " TEXT, "
                    + NewExercise.EXC_DESC + " TEXT, "
                    + NewExercise.EXC_SKILL + " TEXT" + "); ";

   /* CREATE TABLE track(
            trackid     INTEGER,
            trackname   TEXT,
            trackartist INTEGER,
            FOREIGN KEY(trackartist) REFERENCES artist(artistid)
            );*/

    private static final String CREATE_TABLE_TRAINING =
            "CREATE TABLE " + NewTraining.TABLE_NAME
                    + " (" +
                    UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + NewTraining.ID_TRAINING + " INT, "
                    + NewTraining.DATE + " DATETIME, "
                    + NewTraining.LEVEL + " TEXT, "
                    + NewTraining.EXC_ID + " VARCHAR, "
                    + NewTraining.REPEATS + " INT, "
                    + NewTraining.SERIA + " INT, " +
                    "FOREIGN KEY(" + NewExercise.EXC_ID + " ) REFERENCES "
                    + NewExercise.TABLE_NAME + " (" + NewTraining.EXC_ID + ") ON DELETE CASCADE" +
                    ");";


    public TrainingDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EXERCISES);
        Log.e("DATABASE OPERATIONS: ", "Table created ..." + CREATE_TABLE_EXERCISES);
        db.execSQL(CREATE_TABLE_TRAINING);
        Log.e("DATABASE OPERATIONS: ", "Table created ..." + CREATE_TABLE_TRAINING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NewExercise.TABLE_NAME);
        Log.e("DATABASE OPERATIONS: ", "Table onUpgrade ..." + NewExercise.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + NewTraining.TABLE_NAME);
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

    //------Dodawanie danych i funkcje


    public void addExercise(String name, String desc, String skill, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NewExercise.EXC_NAME, name);
        contentValues.put(NewExercise.EXC_DESC, desc);
        contentValues.put(NewExercise.EXC_SKILL, skill);
        db.insert(NewExercise.TABLE_NAME, null, contentValues);
        Log.e("DATABASE OPERATIONS: ", "One row inserted ...");
    }





}
