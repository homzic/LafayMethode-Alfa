package com.kris.lm.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gc.materialdesign.views.ProgressBarDeterminate;
import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.kris.lm.R;

public class TrainingFragment extends Fragment {
    com.gc.materialdesign.views.Button buttonStart;

    ProgressBarDeterminate progressBarDeterminate;
    TextView textCounter;
    long odliczaj = 10000;
    long interval = 1000;
    CircularProgressView progressView;
    MyCountDownTimer myCountDownTimer;
    private long totalTimeCountInMilliseconds; // total count down time in
    // milliseconds
    private long timeBlinkInMilliseconds; // start time of start blinking
    private boolean blink; // controls the blinking .. on and off


    public TrainingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_training, container, false);

        buttonStart = (com.gc.materialdesign.views.Button) rootView.findViewById(R.id.start);
        progressBarDeterminate = (ProgressBarDeterminate) rootView.findViewById(R.id.progressDeterminate);
        progressView = (CircularProgressView) rootView.findViewById(R.id.progress_view);

 //ustaw prgoresbary na 100 i dostosuj do do odliczanego czasu skalê
        progressView.setProgress(100);
        progressView.setMaxProgress(odliczaj / 1000);
        progressBarDeterminate.setMax((int) (odliczaj / 1000));
        textCounter = (TextView) rootView.findViewById(R.id.counter);

        buttonStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

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
            int progress = (int) (millisUntilFinished / 1000);
            progressBarDeterminate.setProgress(progress);
            progressView.setProgress(progress);

            textCounter.setText(String.format("%02d", (millisUntilFinished / 1000) / 60)
                    + ":" + String.format("%02d", (millisUntilFinished / 1000) % 60));
        }

        @Override
        public void onFinish() {
            textCounter.setText("Finished");
            progressBarDeterminate.setProgress(0);
            progressView.setProgress(0);
        }

    }


}