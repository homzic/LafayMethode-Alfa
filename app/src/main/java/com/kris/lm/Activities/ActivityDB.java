package com.kris.lm.Activities;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kris.lm.DB.DB_Helper;
import com.kris.lm.DB.User_Data;
import com.kris.lm.R;

public class ActivityDB extends AppCompatActivity {

    private EditText ContactName;
    private EditText ContactMobile;
    private EditText ContactEmail;
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        ContactName = (EditText) findViewById(R.id.editTextName);
        ContactMobile = (EditText) findViewById(R.id.editTextPhone);
        ContactEmail = (EditText) findViewById(R.id.editTextEmail);
    }

    public void addContact(View view) {
        String name = ContactName.getText().toString();
        String mob = ContactMobile.getText().toString();
        String email = ContactEmail.getText().toString();
        DB_Helper userDbHelper = new DB_Helper(context);
        SQLiteDatabase sqLiteDatabase = userDbHelper.getWritableDatabase();
        User_Data.addInformation(name, mob, email, sqLiteDatabase);
        Toast.makeText(getBaseContext(), "Data Saved", Toast.LENGTH_LONG).show();
        userDbHelper.close();
        Intent intent = new Intent(this, ActivityLoadData.class);
        this.startActivity(intent);

    }


    public void loadDB(View view) {
    }
}

