package com.kris.lm.DB;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.kris.lm.Recycler_Cwiczenia.CwiczenieItem;

import java.util.List;

class Table_Exercises {
    private List<CwiczenieItem> cItems;
    public static final String TABLE_NAME = "table_exercises";
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
/*
    private void ListaCwiczen() {
        cItems = new ArrayList<>();
        CwiczenieItem cwiczenie = new CwiczenieItem();
        cwiczenie.setName(getString(R.string.cwiczenie) + " A");
        cwiczenie.setDes(getString(R.string.cw_A_opis));
        cwiczenie.setThumbnail(R.drawable.cw_a);
        cwiczenie.setmDifficulty(R.drawable.skill1);
        cItems.add(cwiczenie);

        cwiczenie = new CwiczenieItem();
        cwiczenie.setName(getString(R.string.cwiczenie) + " A1");
        cwiczenie.setDes(getString(R.string.cw_A1_opis));
        cwiczenie.setThumbnail(R.drawable.cw_a1);
        cwiczenie.setmDifficulty(R.drawable.skill1);
        cItems.add(cwiczenie);

        cwiczenie = new CwiczenieItem();
        cwiczenie.setName(getString(R.string.cwiczenie) + " B");
        cwiczenie.setDes(getString(R.string.cw_B_opis));
        cwiczenie.setThumbnail(R.drawable.cw_b);
        cwiczenie.setmDifficulty(R.drawable.skill2);
        cItems.add(cwiczenie);

        cwiczenie = new CwiczenieItem();
        cwiczenie.setName(getString(R.string.cwiczenie) + " C");
        cwiczenie.setDes(getString(R.string.cw_C_opis));
        cwiczenie.setThumbnail(R.drawable.cw_c);
        cwiczenie.setmDifficulty(R.drawable.skill2);
        cItems.add(cwiczenie);

        cwiczenie = new CwiczenieItem();
        cwiczenie.setName(getString(R.string.cwiczenie) + " C1");
        cwiczenie.setDes(getString(R.string.cw_C1_opis));
        cwiczenie.setThumbnail(R.drawable.cw_c1);
        cwiczenie.setmDifficulty(R.drawable.skill1);
        cItems.add(cwiczenie);

        cwiczenie = new CwiczenieItem();
        cwiczenie.setName(getString(R.string.cwiczenie) + " D");
        cwiczenie.setDes(getString(R.string.cw_D_opis));
        cwiczenie.setThumbnail(R.drawable.cw_d);
        cwiczenie.setmDifficulty(R.drawable.skill1);
        cItems.add(cwiczenie);

        cwiczenie = new CwiczenieItem();
        cwiczenie.setName(getString(R.string.cwiczenie) + " E");
        cwiczenie.setDes(getString(R.string.cw_E_opis));
        cwiczenie.setThumbnail(R.drawable.cw_e);
        cwiczenie.setmDifficulty(R.drawable.skill1);
        cItems.add(cwiczenie);

        cwiczenie = new CwiczenieItem();
        cwiczenie.setName(getString(R.string.cwiczenie) + " F");
        cwiczenie.setDes(getString(R.string.cw_F_opis));
        cwiczenie.setThumbnail(R.drawable.cw_f);
        cwiczenie.setmDifficulty(R.drawable.skill1);
        cItems.add(cwiczenie);

        cwiczenie = new CwiczenieItem();
        cwiczenie.setName(getString(R.string.cwiczenie) + " G");
        cwiczenie.setDes(getString(R.string.cw_G_opis));
        cwiczenie.setThumbnail(R.drawable.cw_g);
        cwiczenie.setmDifficulty(R.drawable.skill1);
        cItems.add(cwiczenie);

        cwiczenie = new CwiczenieItem();
        cwiczenie.setName(getString(R.string.cwiczenie) + " K2");
        cwiczenie.setDes(getString(R.string.cw_K2_opis));
        cwiczenie.setThumbnail(R.drawable.cw_k2);
        cwiczenie.setmDifficulty(R.drawable.skill1);
        cItems.add(cwiczenie);

    }
*/
}


