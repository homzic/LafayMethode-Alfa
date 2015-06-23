package com.kris.lm.DB;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gc.materialdesign.views.ButtonRectangle;
import com.kris.lm.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import static com.kris.lm.DB.DB_Helper.closeDB;

public class DataBase_Mgt extends AppCompatActivity {
    private DB_Helper DBHelper;
    private MaterialEditText etLevel, etExc_Name, etSeria, etRepeats;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base__mgt);
        ButtonRectangle btnSave = (ButtonRectangle) findViewById(R.id.btnSaveTestDB);
        etLevel = (MaterialEditText) findViewById(R.id.editLevel);
        etExc_Name = (MaterialEditText) findViewById(R.id.editEXC_Name);
        etSeria = (MaterialEditText) findViewById(R.id.editSeria);
        etRepeats = (MaterialEditText) findViewById(R.id.editRepeats);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper = new DB_Helper(getApplication());
                sqLiteDatabase = DBHelper.getWritableDatabase();
                Dane();
                closeDB(sqLiteDatabase);
                clearEditText();
            }
        });
    }

    void Dane() {
        String SERIA_t = etSeria.getText().toString();
        String REPEATS_t = etRepeats.getText().toString();
        String LEVEL = etLevel.getText().toString();
        String EXC_NAME = etExc_Name.getText().toString();
        int SERIA = Integer.parseInt(SERIA_t);
        int REPEATS = Integer.parseInt(REPEATS_t);
        Table_Training.addTrainingRow(LEVEL, EXC_NAME, SERIA, REPEATS, sqLiteDatabase);

    }

    void clearEditText() {
        etLevel.getText().clear();
        etExc_Name.getText().clear();
        etSeria.getText().clear();
        etRepeats.getText().clear();
    }
}


