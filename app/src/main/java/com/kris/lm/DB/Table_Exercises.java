package com.kris.lm.DB;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

class Table_Exercises {

        public static final String TABLE_NAME="table_exercises";
        public static final String EXC_ID = "exc_ID";
        public static final String EXC_NAME = "exc_name";
        public static final String EXC_DESC = "exc_desc";
        public static final String EXC_SKILL = "exc_skill";

    public static final String CREATE_TABLE_EXERCISES =
            "CREATE TABLE " + TABLE_NAME
                    + " (" +
                    EXC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + EXC_NAME + " TEXT, "
                    + EXC_DESC + " TEXT, "
                    + EXC_SKILL + " TEXT" + "); ";


    public void addExercise(String name, String desc, String skill, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EXC_NAME, name);
        contentValues.put(EXC_DESC, desc);
        contentValues.put(EXC_SKILL, skill);
        db.insert(TABLE_NAME, null, contentValues);
        Log.e("DATABASE OPERATIONS: ", "One row inserted ...");
    }


}


