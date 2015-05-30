package com.kris.lm.Activities;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kris.lm.DB.UserBody;
import com.kris.lm.DB.UserDbHelper;
import com.kris.lm.R;

import static com.kris.lm.DB.UserBody.addBodyRow;


public class ActivityBody extends Activity {
    EditText etNeck, etBiceps, etChest, etHip, etThigh, etCalf;
    Context context = this;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_body);
        etNeck = (EditText) findViewById(R.id.editNeck);
        etBiceps = (EditText) findViewById(R.id.editBiceps);
        etChest = (EditText) findViewById(R.id.editChest);
        etHip = (EditText) findViewById(R.id.editPas);
        etThigh = (EditText) findViewById(R.id.editUda);
        etCalf = (EditText) findViewById(R.id.editLydki);

        loadDB();

    }


    public void saveBody(View view) {
        String bic = etBiceps.getText().toString();
        String neck = etNeck.getText().toString();
        String chest = etChest.getText().toString();
        String hip = etHip.getText().toString();
        String thigh = etThigh.getText().toString();
        String calf = etCalf.getText().toString();
        userDbHelper = new UserDbHelper(context);
        sqLiteDatabase = userDbHelper.getWritableDatabase();
        addBodyRow(neck, bic, chest, hip, thigh, calf, sqLiteDatabase);
        userDbHelper.close();

        //Komunikat ile wpisów jest w bazie
       Toast toast = Toast.makeText(getBaseContext(), "Data Saved for body "+getToDoCount()+" row!\n\n"+"At date "+userDbHelper.getDateTime()+" !", Toast.LENGTH_LONG);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        if( v != null) v.setGravity(Gravity.CENTER);
        toast.show();


    }

    private void loadDB() {

        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();
        Cursor cursor = userDbHelper.getBody(sqLiteDatabase);
        if (cursor.moveToLast()) {
            String neck = cursor.getString(cursor.getColumnIndex("body_neck"));
            String bic = cursor.getString(cursor.getColumnIndex("body_biceps"));
            String chest = cursor.getString(cursor.getColumnIndex("body_chest"));
            String hip = cursor.getString(cursor.getColumnIndex("body_hip"));
            String thigh = cursor.getString(cursor.getColumnIndex("body_thigh"));
            String calf = cursor.getString(cursor.getColumnIndex("body_calf"));
            etNeck.setText(neck);
            etBiceps.setText(bic);
            etChest.setText(chest);
            etHip.setText(hip);
            etThigh.setText(thigh);
            etCalf.setText(calf);
        }
    }

    /*
 * getting todo count
 */
    public int getToDoCount() {
        String countQuery = "SELECT  * FROM " + UserBody.NewBody.TABLE_NAME;
        SQLiteDatabase db = userDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }
}
