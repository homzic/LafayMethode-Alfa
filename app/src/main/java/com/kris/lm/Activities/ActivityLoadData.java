package com.kris.lm.Activities;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.kris.lm.DB.UserDbHelper;
import com.kris.lm.R;


public class ActivityLoadData extends Activity {

    EditText Search_Name, Display_email, Display_Mobile;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    String search_name;


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
        search_name = Search_Name.getText().toString();
        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();
        Cursor cursor = userDbHelper.getContact(search_name, sqLiteDatabase);
        if (cursor.moveToFirst()) {
            String MOBILE = cursor.getString(0);
            String EMAIL = cursor.getString(1);
            Display_Mobile.setText(MOBILE);
            Display_email.setText(EMAIL);
        }

    }


}
