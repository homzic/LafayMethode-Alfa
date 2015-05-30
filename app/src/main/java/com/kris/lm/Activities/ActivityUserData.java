package com.kris.lm.Activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;

import com.kris.lm.R;

import static com.kris.lm.R.id.radioMale;
//Zapisz dane do SharedPreferences

public class ActivityUserData extends Activity implements View.OnClickListener {
    public static final String DEFAULT = " ";
    private EditText userName;
    private EditText eMail;
    private EditText etBirthday;
    private EditText etWeight;
    private SharedPreferences dataSettings;


    //do ustawienia daty urodzenia
    private Calendar cal;
    private int day;
    private int month;
    private int year;

    //ZAPIS DO SHARED PREFERENCES
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            etBirthday.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
                    + selectedYear);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        userName = (EditText) findViewById(R.id.editName);
        etBirthday = (EditText) findViewById(R.id.editBirthday);
        eMail = (EditText) findViewById(R.id.editEmail);
        etWeight = (EditText) findViewById(R.id.editWeight);
        setDate(); // Pobranie daty urodzenia z Date Picker

        Load(); // załaduj dane jesli dostepne


    }

    public void Load() {
        dataSettings = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String Name = dataSettings.getString("name", DEFAULT);
        String Email = dataSettings.getString("email", DEFAULT);
        String Birthday = dataSettings.getString("birthday", DEFAULT);
        String Weight = dataSettings.getString("weight", DEFAULT);
        boolean radio0 = dataSettings.getBoolean("Male", false);
        boolean radio1 = dataSettings.getBoolean("Female", false);
        if (Name.equals(DEFAULT)) {
            Toast.makeText(this, "Fill in your profile!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Data loaded successfully", Toast.LENGTH_LONG).show();
            userName.setText(Name);
            eMail.setText(Email);
            etBirthday.setText(Birthday);
            etWeight.setText(Weight);
        }
    }

    public void Save(View view) {
        dataSettings = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = dataSettings.edit();
        editor.putString("name", userName.getText().toString());
        editor.putString("email", eMail.getText().toString());
        editor.putString("birthday", etBirthday.getText().toString());
        editor.putString("weight", etWeight.getText().toString());
        editor.apply();
        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ActivityBody.class);
        this.startActivity(intent);
    }

    private void savePrefs(String key, boolean value) {
        dataSettings = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = dataSettings.edit();
        edit.putBoolean(key, value);
        edit.apply();
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radioFemale:
                if (checked) {
                    savePrefs("Female", true);
                    Toast.makeText(this, "Jesteś babą", Toast.LENGTH_LONG).show();
                    // Are you Female
                    break;
                }


            case radioMale:
                if (checked) {
                    savePrefs("Male", true);
                    Toast.makeText(this, "Masz wacka", Toast.LENGTH_LONG).show();
                    // No I'm Male
                    break;
                }

        }
    }

    void setDate() {
        //do ustawienia daty urodzenia - wywołanie do onCreate
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);

        etBirthday.setOnClickListener(this);
        //------------------------------------------------------------
    }

    //------------------------------------------------------------
    //do ustawienia daty urodzenia<
    @Override
    public void onClick(View view) {
        showDialog(0);

    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, datePickerListener, year, month, day);
    }
    //do ustawienia daty urodzenia />
}
