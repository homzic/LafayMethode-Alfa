package com.kris.lm.Activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.kris.lm.DB.DB_Helper;
import com.kris.lm.DB.User_Data;
import com.kris.lm.R;


public class ActivityLoadData extends AppCompatActivity {

    private EditText Search_Name;
    private EditText Display_email;
    private EditText Display_Mobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        View saveButton = findViewById(R.id.btnSaveDB);
        View loadButton = findViewById(R.id.btnLoaDB);
        saveButton.setVisibility(View.INVISIBLE);
        loadButton.setVisibility(View.VISIBLE);

        Search_Name = (EditText) findViewById(R.id.editTextName);
        Display_Mobile = (EditText) findViewById(R.id.editTextPhone);
        Display_email = (EditText) findViewById(R.id.editTextEmail);

    }

    public void loadDB(View view) {
        String search_name = Search_Name.getText().toString();
        DB_Helper userDbHelper = new DB_Helper(getApplicationContext());
        SQLiteDatabase sqLiteDatabase = userDbHelper.getReadableDatabase();
        Cursor cursor = User_Data.getContact(search_name, sqLiteDatabase);
        if (cursor.moveToFirst()) {
            String MOBILE = cursor.getString(0);
            String EMAIL = cursor.getString(1);
            Display_Mobile.setText(MOBILE);
            Display_email.setText(EMAIL);
        }

    }


    public void addContact(View view) {
    }
}
