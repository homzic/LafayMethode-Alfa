package com.kris.lm.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kris.lm.DB.UserDbHelper;
import com.kris.lm.R;

/**
 * Created by Kris on 2015-04-25.
 */
public class ActivityDB extends Activity {

    EditText ContactName, ContactMobile, ContactEmail;
    Context context = this;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;

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
        userDbHelper = new UserDbHelper(context);
        sqLiteDatabase = userDbHelper.getWritableDatabase();
        userDbHelper.addInformation(name,mob,email,sqLiteDatabase);
        Toast.makeText(getBaseContext(),"Data Saved",Toast.LENGTH_LONG).show();
        userDbHelper.close();
        Intent intent = new Intent(this, ActivityLoadData.class);
        this.startActivity(intent);

    }



}

