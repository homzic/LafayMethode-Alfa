package com.kris.lm.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kris.lm.Fragments.ExercisesFragment;
import com.kris.lm.Fragments.StoperFragment;
import com.kris.lm.R;

public class Training extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        Fragment fragmentStoper = new StoperFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_stoper, fragmentStoper).commit();


        Fragment fragmentExcersise = new ExercisesFragment();
        fragmentManager.beginTransaction().replace(R.id.fram_excercise, fragmentExcersise)
                .commit();


    }


}
