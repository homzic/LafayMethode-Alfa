package com.kris.lm.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Tabela wymiarów
 */
public class User_Body {
    private static final String BODY_WEIGHT = "body_weight";
    private static final String BODY_HEIGHT = "body_height";
    private static final String BODY_NECK = "body_neck";
    private static final String BODY_BICEPS = "body_biceps";
    private static final String BODY_CHEST = "body_chest";
    private static final String BODY_HIP = "body_hip";
    private static final String BODY_THIGH = "body_thigh";
    private static final String BODY_CALF = "body_calf";
    public static final String TABLE_NAME = "table_body";
    private static final String KEY_CREATED_AT = "created_at";
    private static final String UID = "_id";

    public static final String CREATE_TABLE_BODY =
            "CREATE TABLE " + User_Body.TABLE_NAME
                    + " ("
                    + UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + User_Body.BODY_WEIGHT + " TEXT,"
                    + User_Body.BODY_HEIGHT + " TEXT,"
                    + User_Body.BODY_NECK + " TEXT,"
                    + User_Body.BODY_BICEPS + " TEXT,"
                    + User_Body.BODY_CHEST + " TEXT,"
                    + User_Body.BODY_HIP + " TEXT,"
                    + User_Body.BODY_THIGH + " TEXT,"
                    + User_Body.BODY_CALF + " TEXT,"
                    + User_Body.KEY_CREATED_AT + " DATETIME" + ")";

    public static void addBodyRow(String neck, String biceps, String chest, String hip, String thigh, String calf, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(BODY_NECK, neck);
        values.put(BODY_BICEPS, biceps);
        values.put(BODY_CHEST, chest);
        values.put(BODY_HIP, hip);
        values.put(BODY_THIGH, thigh);
        values.put(BODY_CALF, calf);
        values.put(KEY_CREATED_AT, getDateTime());
        db.insert(TABLE_NAME, null, values);
        Log.e("DATABASE OPERATIONS: ", "One row Body inserted..." + values);
    }

    public static Cursor getBody(SQLiteDatabase sqLiteDatabase) {
        Cursor cursor;
        String[] Selections = {BODY_NECK, BODY_BICEPS, BODY_CHEST, BODY_HIP, BODY_THIGH, BODY_CALF};
        cursor = sqLiteDatabase.query(TABLE_NAME, Selections, null, null, null, null, null);
        return cursor;
    }

    public static String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        Log.e("DATABASE OPERATIONS: ", "date:" + date);
        return dateFormat.format(date);
    }
}
