package com.kris.lm.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.Button;
import com.kris.lm.Activities.MainActivity;
import com.kris.lm.DB.DB_Helper;
import com.kris.lm.DB.User_Body;
import com.kris.lm.R;
import com.rengwuxian.materialedittext.MaterialEditText;


public class BodyFragment extends Fragment {
    public BodyFragment() {
    }

    private MaterialEditText etNeck, etBiceps, etChest, etHip, etThigh, etCalf;
    com.gc.materialdesign.views.Button btnSave;
    private Context context;
    private DB_Helper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_body, container, false);
        context = rootView.getContext();
        return rootView;
    }

    @Override
    public void onStart() {
        etNeck = (MaterialEditText) getView().findViewById(R.id.editNeck);
        etBiceps = (MaterialEditText) getView().findViewById(R.id.editBiceps);
        etChest = (MaterialEditText) getView().findViewById(R.id.editChest);
        etHip = (MaterialEditText) getView().findViewById(R.id.editPas);
        etThigh = (MaterialEditText) getView().findViewById(R.id.editUda);
        etCalf = (MaterialEditText) getView().findViewById(R.id.editLydki);

        dbHelper = new DB_Helper(context);
        btnSave = (Button) getView().findViewById(R.id.btnSaveBody);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBody();
            }
        });
        loadDB();
        super.onStart();
    }

    public void saveBody() {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        String bic = etBiceps.getText().toString();
        String neck = etNeck.getText().toString();
        String chest = etChest.getText().toString();
        String hip = etHip.getText().toString();
        String thigh = etThigh.getText().toString();
        String calf = etCalf.getText().toString();
        User_Body.addBodyRow(neck, bic, chest, hip, thigh, calf, sqLiteDatabase);
        DB_Helper.closeDB(sqLiteDatabase);

        //---Ukryj klawiature
        ((MainActivity) getActivity()).hideKeyboard(getView());

        //Komunikat ile wpis�w jest w bazie
        Toast toast = Toast.makeText(context, "Data Saved for body " + getToDoCount() + " row!\n\n" + "At date " + DB_Helper.getDateTime() + " !", Toast.LENGTH_LONG);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        if (v != null) v.setGravity(Gravity.CENTER);
        toast.show();
        Fragment fragment = new HomeFragment();
        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();
        }
    }

    private void loadDB() {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = User_Body.getBody(sqLiteDatabase);
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
    private int getToDoCount() {
        String countQuery = "SELECT  * FROM " + User_Body.TABLE_NAME;
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }
}