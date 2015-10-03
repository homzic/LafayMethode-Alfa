package com.kris.lm.Fragments;


import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kris.lm.Activities.Training;
import com.kris.lm.Common.OnSwipeTouchListener;
import com.kris.lm.DB.DB_Helper;
import com.kris.lm.DB.Table_Training;
import com.kris.lm.R;

import at.grabner.circleprogress.CircleProgressView;

import static com.kris.lm.DB.DB_Helper.closeDB;


public class ResultsExe extends Fragment {
    Context context;
    Bundle bundle;
    private DB_Helper DBHelper;
    private SQLiteDatabase sqLiteDatabase;
    View rootView;
    CircleProgressView mCircleView;
    Button btnRound, btnRepeats;
    int counter = 0;
    int target;

    public ResultsExe() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_results_exe, container, false);
        Log.d("ResultExe: ", "onCreateView");
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.d("ResultExe: ", "onViewCreated");
        context = getActivity();
        btnRepeats = (Button) rootView.findViewById(R.id.btnTarget);
        btnRound = (Button) rootView.findViewById(R.id.btnRound);

        bundle = getArguments();
        btnRound.setText(bundle.getString(Training.ROUND));
        btnRepeats.setText(bundle.getString(Training.REPEATES));
        target = Integer.parseInt(bundle.getString(Training.REPEATES));

        mCircleView = (CircleProgressView) rootView.findViewById(R.id.circleView);
        //      value setting
        mCircleView.setMaxValue(target);
        mCircleView.setValue(0);
        //        show unit
        mCircleView.setShowUnit(false);
        //        text sizes
        //       mCircleView.setTextSize(20); // text size set, auto text size off
        mCircleView.setUnitSize(15); // if i set the text size i also have to set the unit size
        mCircleView.setAutoTextSize(true); // enable auto text size, previous values are overwritten

//
        String textTarget = "Swipe result";
        mCircleView.setText(textTarget);
        mCircleView.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
            public void onSwipeTop() {
                counter += 3;
                setmCircleView();

            }

            public void onSwipeRight() {
                counter += 1;
                setmCircleView();
            }

            @Override
            public void onSwipeBottom() {
                counter = 0;
                setmCircleView();
            }

            public void onSwipeLeft() {
                if (counter > 0) counter -= 1;
                setmCircleView();
            }

            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });


        super.onViewCreated(view, savedInstanceState);
    }

    void setmCircleView() {
        mCircleView.setTextScale(0.5f);
        mCircleView.setText(String.valueOf(counter));
        mCircleView.spin(); // start spinning
        mCircleView.setValue(counter);
        mCircleView.setValueAnimated(counter); // stops spinning. Spinner spinns until on top. Then fills to set value.
        mCircleView.stopSpinning(); // stops spinning. Spinner gets shorter until it disappears.

    }

    @Override
    public void onStart() {
        Log.d("ResultExe: ", "onCreateView");
        super.onStart();
    }

    @Override
    public void onPause() {
        Log.d("ResultExe: ", "onPause");
        super.onPause();
    }

    @Override
    public void onAttach(Activity activity) {
        Log.d("ResultExe: ", "onAttach");
        super.onAttach(activity);
    }

    void SaveSeriaToDB() {
        DBHelper = new DB_Helper(context);
        sqLiteDatabase = DBHelper.getWritableDatabase();

        String SERIA_t = String.valueOf(btnRound.getText());
        String REPEATS_t = String.valueOf(counter);
        String LEVEL = "1";
        String EXC_NAME = bundle.getString(Training.IMAGE_NAME);
        int SERIA = Integer.parseInt(SERIA_t);
        int REPEATS = Integer.parseInt(REPEATS_t);
        Table_Training.addTrainingRow(LEVEL, EXC_NAME, SERIA, REPEATS, sqLiteDatabase);

        closeDB(sqLiteDatabase);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("ResultExe: ", "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        Log.d("ResultExe: ", "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d("ResultExe: ", "onDetach");
        super.onDetach();
    }


    @Override
    public void onDestroyView() {
        Log.d("ResultExe: ", "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onStop() {
        Log.d("ResultExe: ", "onStop");
        SaveSeriaToDB();
        super.onStop();
    }

    @Override
    public void onResume() {
        Log.d("ResultExe: ", "onResume");
        super.onResume();
    }

}
