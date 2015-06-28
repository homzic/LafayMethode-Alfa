package com.kris.lm.Fragments;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gc.materialdesign.views.ButtonRectangle;
import com.kris.lm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentIntro extends Fragment {


    public FragmentIntro() {
        // Required empty public constructor
    }

    ButtonRectangle btnGo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.intro, container, false);
        Log.d("Intro LifeCycle: ", "onCreateView");
        return rootView;
    }

    @Override
    public void onStart() {
        Log.d("Intro LifeCycle: ", "onStart");

        btnGo = (ButtonRectangle) getActivity().findViewById(R.id.btnGo);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment_left, fragment_right;
                fragment_left = new OneExcercise();
                fragment_right = new StoperFragment();

                if (fragment_left != null) {
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.frame_left, fragment_left, "Frag_Left").commit();
                    fragmentManager.beginTransaction()
                            .replace(R.id.frame_right, fragment_right, "Frag_Right").commit();

                }
            }
        });
        super.onStart();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d("Intro LifeCycle: ", "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onPause() {
        Log.d("Intro LifeCycle: ", "onPause");
        super.onPause();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("Intro LifeCycle: ", "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        Log.d("Intro LifeCycle: ", "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onAttach(Activity activity) {
        Log.d("Intro LifeCycle: ", "onAttach");
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        Log.d("Intro LifeCycle: ", "onDetach");
        super.onDetach();
    }

    @Override
    public void onResume() {
        Log.d("Intro LifeCycle: ", "onResume");
        super.onResume();
    }

    @Override
    public void onStop() {
        Log.d("Intro LifeCycle: ", "onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d("Intro LifeCycle: ", "onDestroyView");
        super.onDestroyView();
    }

}
