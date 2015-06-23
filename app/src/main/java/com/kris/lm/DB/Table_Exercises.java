package com.kris.lm.DB;


import android.content.Context;
import android.util.Log;

import com.kris.lm.Recycler_Cwiczenia.CwiczenieItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Table_Exercises {

    private static List<CwiczenieItem> cItems;
    public static final String TABLE_NAME = "table_exercises";
    public static final String EXC_ID = "exc_ID";
    public static final String EXC_NAME = "exc_name";
    public static final String EXC_DESC = "exc_desc";
    public static final String EXC_THUMB = "exc_thumb";
    public static final String EXC_SKILL = "exc_skill";
    private static JSONArray cwiczenia = null;
    public static final String CREATE_TABLE_EXERCISES =
            "CREATE TABLE " + TABLE_NAME
                    + " (" +
                    EXC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + EXC_NAME + " TEXT, "
                    + EXC_DESC + " TEXT, "
                    + EXC_THUMB + " INTEGER, "
                    + EXC_SKILL + " INTEGER" + "); ";




    public static JSONObject parseJSONData(Context context) {
        String JSONString = null;
        JSONObject jsonObject = null;
        try {

            //open the inputStream to the file
            InputStream inputStream = context.getAssets().open("Cwiczenia_JSON");

            int sizeOfJSONFile = inputStream.available();

            //array that will store all the data
            byte[] bytes = new byte[sizeOfJSONFile];

            //reading data into the array from the file
            inputStream.read(bytes);

            //close the input stream
            inputStream.close();

            JSONString = new String(bytes, "UTF-8");
            jsonObject = new JSONObject(JSONString);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (JSONException x) {
            x.printStackTrace();
            return null;
        }
        return jsonObject;
    }

    public static List cwiczeniaJSONtoArray(JSONObject jsonObject, Context context) {
        cItems = new ArrayList<>();
        CwiczenieItem cwiczenie = null;
        int thumbID, skillID;
//THis will store all the values inside "Cwiczenia" in a element string
        try {
            if (jsonObject != null) {
                cwiczenia = jsonObject.getJSONArray("cwiczenia");

            for (int i = 0; i < cwiczenia.length(); i++) {
                String exc_Name = cwiczenia.getJSONObject(i).getString("name");
                String exc_Desc = cwiczenia.getJSONObject(i).getString("desc");
                String exc_Thumb = cwiczenia.getJSONObject(i).getString("icon");
                String skill_Icon = cwiczenia.getJSONObject(i).getString("skill");
                cwiczenie = new CwiczenieItem();
                cwiczenie.setName(exc_Name);
                cwiczenie.setDes(exc_Desc);
                thumbID = context.getResources().getIdentifier(exc_Thumb, "drawable",
                        context.getPackageName());
                cwiczenie.setThumbnail(thumbID);
                skillID = context.getResources().getIdentifier(skill_Icon, "drawable", context
                        .getPackageName());
                cwiczenie.setmDifficulty(skillID);
                cItems.add(cwiczenie);

            }}
        } catch (JSONException e) {
            Log.e("JSON: ", "Desc nie znalezione");
        }
        return cItems;
    }


}


