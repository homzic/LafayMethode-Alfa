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
import android.widget.Toast;

import com.kris.lm.Activities.MainActivity;
import com.kris.lm.DatePickerFragment;
import com.kris.lm.R;


public class Profile_Fragment extends Fragment implements View.OnClickListener {
    public Profile_Fragment() {
    }

    SharedPreferences dataSettings;
    private static final String DEFAULT = " ";
    private EditText userName;
    private EditText eMail;
    public static EditText etBirthday;
    private EditText etWeight;


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

        etBirthday.setOnClickListener(this);

        Load(); // załaduj dane jesli dostepne
        super.onStart();

    }

    public void Load() {
        String Name = dataSettings.getString("name", DEFAULT);
        String Email = dataSettings.getString("email", DEFAULT);
        String Birthday = dataSettings.getString("birthday", DEFAULT);
        String Weight = dataSettings.getString("weight", DEFAULT);
        boolean radio0 = dataSettings.getBoolean("Male", false);
        boolean radio1 = dataSettings.getBoolean("Female", false);
        if (Name.equals(DEFAULT)) {
            Toast.makeText(getActivity(), "Fill in your profile!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), "Data loaded successfully", Toast.LENGTH_LONG).show();
            userName.setText(Name);
            eMail.setText(Email);
            etBirthday.setText(Birthday);
            etWeight.setText(Weight);
        }
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

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radioFemale:
                if (checked) {
                    savePrefs("Female", true);
                    Toast.makeText(getActivity(), "Jesteś babą", Toast.LENGTH_LONG).show();
                    // Are you Female
                    break;
                }


            case R.id.radioMale:
                if (checked) {
                    savePrefs("Male", true);
                    Toast.makeText(getActivity(), "Masz wacka", Toast.LENGTH_LONG).show();
                    // No I'm Male
                    break;
                }
        }
    }

    //do ustawienia daty urodzenia<
    @Override
    public void onClick(View view) {
        ((MainActivity) getActivity()).hideKeyboard(getView());
        DialogFragment picker = new DatePickerFragment();
        picker.show(getFragmentManager(), "datePicker");
    }

}
