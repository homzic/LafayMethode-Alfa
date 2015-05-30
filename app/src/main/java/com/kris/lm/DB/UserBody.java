package com.kris.lm.DB;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import static com.kris.lm.DB.UserDbHelper.*;

/**
 * Tabela wymiarów
 */
public class UserBody {
    public static abstract class NewBody{
        public static final String BODY_NECK="body_neck";
        public static final String BODY_BICEPS="body_biceps";
        public static final String BODY_CHEST="body_chest";
        public static final String BODY_HIP="body_hip";
        public static final String BODY_THIGH="body_thigh";
        public static final String BODY_CALF="body_calf";
        public static final String TABLE_NAME="table_body";
        public static final String KEY_CREATED_AT = "created_at";

    }

    public static void addBodyRow(String neck, String biceps, String chest, String hip,  String thigh, String calf, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(UserBody.NewBody.BODY_NECK, neck);
        values.put(UserBody.NewBody.BODY_BICEPS, biceps);
        values.put(UserBody.NewBody.BODY_CHEST, chest);
        values.put(UserBody.NewBody.BODY_HIP, hip);
        values.put(UserBody.NewBody.BODY_THIGH, thigh);
        values.put(UserBody.NewBody.BODY_CALF, calf);
        values.put(UserBody.NewBody.KEY_CREATED_AT, getDateTime());
        db.insert(UserBody.NewBody.TABLE_NAME, null, values);
        Log.e("DATABASE OPERATIONS: ", "One row Body inserted..." + values);
    }
}
