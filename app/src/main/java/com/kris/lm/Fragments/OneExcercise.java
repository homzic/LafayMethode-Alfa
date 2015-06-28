package com.kris.lm.Fragments;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kris.lm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneExcercise extends Fragment {


    public OneExcercise() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("OneExce LifeCycle: ", "onCreateView");
        return inflater.inflate(R.layout.fragment_one_excercise, container, false);
    }

    @Override
    public void onStart() {
        Log.d("OneExce LifeCycle: ", "onStart");
        super.onStart();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d("OneExce LifeCycle: ", "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onPause() {
        Log.d("OneExce LifeCycle: ", "onPause");
        super.onPause();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("OneExce LifeCycle: ", "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        Log.d("OneExce LifeCycle: ", "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onAttach(Activity activity) {
        Log.d("OneExce LifeCycle: ", "onAttach");
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        Log.d("OneExce LifeCycle: ", "onDetach");
        super.onDetach();
    }

    @Override
    public void onResume() {
        Log.d("OneExce LifeCycle: ", "onResume");
        super.onResume();
    }

    @Override
    public void onStop() {
        Log.d("OneExce LifeCycle: ", "onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d("OneExce LifeCycle: ", "onDestroyView");
        super.onDestroyView();
    }


}
