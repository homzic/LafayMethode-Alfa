package com.kris.lm.Fragments;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.gc.materialdesign.views.Button;
import com.kris.lm.Activities.MainActivity;
import com.kris.lm.DatePickerFragment;
import com.kris.lm.R;


public class Profile_Fragment extends Fragment {
    public Profile_Fragment() {
    }

    private SharedPreferences dataSettings;
    private static final String DEFAULT = " ";
    private EditText userName, eMail, etWeight;
    public static EditText etBirthday;
    private RadioGroup radioGender, radioActivity;
    Button btnSave;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onStart() {
        dataSettings = this.getActivity().getSharedPreferences("MyData", Context.MODE_PRIVATE);
        userName = (EditText) getView().findViewById(R.id.editName);
        etBirthday = (EditText) getView().findViewById(R.id.editBirthday);
        eMail = (EditText) getView().findViewById(R.id.editEmail);
        etWeight = (EditText) getView().findViewById(R.id.editWeight);

//-przypisz datę urodzin
        etBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBirthday();
            }
        });

        //--zapisz i przejdz do body
        btnSave = (Button) getView().findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save(getView());
            }
        });
// wybierz płeć
        radioGender = (RadioGroup) getView().findViewById(R.id.radioGender);
        radioGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                // Is the button now checked?
                switch (checkedId) {
                    case R.id.radioMale:
                        savePrefs("Male", true);
                        savePrefs("Female", false);
                        break;
                    case R.id.radioFemale:
                        savePrefs("Female", true);
                        savePrefs("Male", false);
                        break;

                }
            }
        });

// wybierz aktywnosc
        radioActivity = (RadioGroup) getView().findViewById(R.id.radioActivity);
        radioActivity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                // Is the button now checked?
                switch (checkedId) {
                    case R.id.radioLow:
                        savePrefs("Low", true);
                        savePrefs("Medium", false);
                        savePrefs("High", false);
                        break;
                    case R.id.radioMedium:
                        savePrefs("Low", false);
                        savePrefs("Medium", true);
                        savePrefs("High", false);
                        break;
                    case R.id.radioHigh:
                        savePrefs("Low", false);
                        savePrefs("Medium", false);
                        savePrefs("High", true);
                        break;
                }
            }
        });
        Load(); // załaduj dane jesli dostepne
        super.onStart();
    }

    public void Load() {
        String Name = dataSettings.getString("name", DEFAULT);
        String Email = dataSettings.getString("email", DEFAULT);
        String Birthday = dataSettings.getString("birthday", DEFAULT);
        String Weight = dataSettings.getString("weight", DEFAULT);

        boolean Male = dataSettings.getBoolean("Male", false);
        boolean Female = dataSettings.getBoolean("Female", false);
        RadioButton radioMale = (RadioButton) getView().findViewById(R.id.radioMale);
        RadioButton radioFemale = (RadioButton) getView().findViewById(R.id.radioFemale);
        if (Male) radioMale.setChecked(true);
        if (Female) radioFemale.setChecked(true);

        boolean Low = dataSettings.getBoolean("Low", false);
        boolean Medium = dataSettings.getBoolean("Medium", false);
        boolean High = dataSettings.getBoolean("High", false);
        RadioButton radioLow = (RadioButton) getView().findViewById(R.id.radioLow);
        RadioButton radioMedium = (RadioButton) getView().findViewById(R.id.radioMedium);
        RadioButton radioHigh = (RadioButton) getView().findViewById(R.id.radioHigh);
        if (Low) radioLow.setChecked(true);
        if (Medium) radioMedium.setChecked(true);
        if (High) radioHigh.setChecked(true);

        userName.setText(Name);
        eMail.setText(Email);
        etBirthday.setText(Birthday);
        etWeight.setText(Weight);
    }


    public void Save(View view) {
        SharedPreferences.Editor editor = dataSettings.edit();
        editor.putString("name", userName.getText().toString());
        editor.putString("email", eMail.getText().toString());
        editor.putString("birthday", etBirthday.getText().toString());
        editor.putString("weight", etWeight.getText().toString());
        editor.apply();
        Toast.makeText(getActivity(), "Saved", Toast.LENGTH_LONG).show();

        Fragment fragment = new BodyFragment();
        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();

        }
    }

    private void savePrefs(String key, boolean value) {
        SharedPreferences.Editor edit = dataSettings.edit();
        edit.putBoolean(key, value);
        edit.apply();
    }


    //do ustawienia daty urodzenia
    public void setBirthday() {
        ((MainActivity) getActivity()).hideKeyboard(getView());
        DialogFragment picker = new DatePickerFragment();
        picker.show(getFragmentManager(), "datePicker");
    }

}
