package com.kris.lm.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kris.lm.R;

public class HomeFragment extends Fragment {
    private String Name;
    private static final String DEFAULT = " ";

    public HomeFragment() {
    }

    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        context = getActivity().getApplicationContext();
        getSharedPref();
        FirstStart();
        return rootView;
    }

    private void getSharedPref() {
        SharedPreferences dataSettings = context.getSharedPreferences("MyData", Context.MODE_PRIVATE);
        Name = dataSettings.getString("name", DEFAULT);
    }

    private void FirstStart() {
        if (!Name.equals(""))
            Toast.makeText(context, "Hello " + Name + "!", Toast.LENGTH_LONG).show();
        else {
            Toast.makeText(context, "Fill in your profile!", Toast.LENGTH_LONG).show();
           // startActivity(new Intent(context, ActivityUserData.class));
        }
    }
}
