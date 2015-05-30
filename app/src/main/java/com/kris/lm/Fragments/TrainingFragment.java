package com.kris.lm.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kris.lm.R;

public class TrainingFragment extends Fragment {
    Button buttonStart;
    ProgressBar progressBar;
    TextView textCounter;
    long odliczaj = 100000;
    long interval = 1000;
    private int mDisplaySeconds, mDisplayMinutes;

    MyCountDownTimer myCountDownTimer;

    public TrainingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_training, container, false);

        buttonStart = (Button) rootView.findViewById(R.id.start);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressbar);
        textCounter = (TextView) rootView.findViewById(R.id.counter);

        buttonStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                progressBar.setProgress(100);
                myCountDownTimer = new MyCountDownTimer(odliczaj, interval);
                myCountDownTimer.start();

            }
        });
        return rootView;
    }


    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mDisplayMinutes = (int) ((millisUntilFinished / 1000) / 60);
            mDisplaySeconds = (int) ((millisUntilFinished / 1000) % 60);
            int progress = (int) (millisUntilFinished / 1000);
            progressBar.setProgress(progress);
            textCounter.setText(String.valueOf(mDisplayMinutes)  +":" + String.valueOf(mDisplaySeconds) + "\nProgres: " + String.valueOf(progress));

        }

        @Override
        public void onFinish() {
            textCounter.setText("Finished");
            progressBar.setProgress(0);
        }

    }

}