package com.kris.lm.DB;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gc.materialdesign.views.ButtonRectangle;
import com.kris.lm.R;

public class DataBase_Mgt extends AppCompatActivity {
private TrainingDbHelper trainingDbHelper;
    private SQLiteDatabase sqLiteDatabase;
private ButtonRectangle btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base__mgt);
        btnSave = (ButtonRectangle) findViewById(R.id.btnSaveTestDB);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trainingDbHelper = new TrainingDbHelper(getApplication());
                sqLiteDatabase = trainingDbHelper.getWritableDatabase();
                trainingDbHelper.closeDB();
            }
        });
    }


}
