package com.kris.lm.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gc.materialdesign.views.Slider;
import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.kris.lm.R;

public class StoperFragment extends Fragment {
    private com.gc.materialdesign.views.Button buttonStart;
    private com.gc.materialdesign.views.Button buttonStop;
    private Vibrator v;
    private final long[] pattern = {0, 1000, 1000, 2000};
    private TextView textCounter;
    private long odliczaj = 10000;
    private final long interval = 1000;
    private CircularProgressView progressView;
    private MyCountDownTimer myCountDownTimer;
    private Slider slider;

    public StoperFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_stoper, container, false);
        Context context = rootView.getContext();
        buttonStart = (com.gc.materialdesign.views.Button) rootView.findViewById(R.id.start);
        buttonStop = (com.gc.materialdesign.views.Button) rootView.findViewById(R.id.stop);

        progressView = (CircularProgressView) rootView.findViewById(R.id.progress_view);
        slider = (Slider) rootView.findViewById(R.id.slider);
        slider.setValue(10);
        slider.setShowNumberIndicator(true);
        slider.setMax(200);
        v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        //ustaw prgoresbary na 100 i dostosuj do do odliczanego czasu skal
        progressView.setProgress(100);
        progressView.setMaxProgress(odliczaj / 1000);
        textCounter = (TextView) rootView.findViewById(R.id.counter);

        //ustaw licznik
        textCounter.setText(String.format("%02d", (odliczaj / 1000) / 60)
                + ":" + String.format("%02d", (odliczaj / 1000) % 60));
        myCountDownTimer = new MyCountDownTimer(odliczaj, interval);

        //zmien licznik kiedy przesuwa slider
        slider.setOnValueChangedListener(new Slider.OnValueChangedListener() {
            @Override
            public void onValueChanged(int i) {
                odliczaj = slider.getValue() * 1000;
                textCounter.setText(String.format("%02d", (odliczaj / 1000) / 60)
                        + ":" + String.format("%02d", (odliczaj / 1000) % 60));
                myCountDownTimer.cancel();

                buttonStop.setVisibility(View.GONE);
                buttonStart.setVisibility(View.VISIBLE);
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCountDownTimer.cancel();

                buttonStop.setVisibility(View.GONE);
                buttonStart.setVisibility(View.VISIBLE);
                v.playSoundEffect(SoundEffectConstants.CLICK);
            }
        });
        buttonStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                myCountDownTimer.cancel();
                odliczaj = slider.getValue() * 1000;
                progressView.setProgress(100);
                progressView.setMaxProgress(odliczaj / 1000);
                if (progressView.getProgress() == 100) {
                    myCountDownTimer = new MyCountDownTimer(odliczaj, interval);
                    myCountDownTimer.start();

                }
                buttonStart.setVisibility(View.GONE);
                buttonStop.setVisibility(View.VISIBLE);

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
            progressView.setProgress(progress);

            if (progress < 3) {
                progressView.setColor(getResources().getColor(R.color.redItem));
                progressView.playSoundEffect(SoundEffectConstants.CLICK);
                boolean set = true;
                if (set) {
                    v.vibrate(pattern, -1);
                    set = false;
                }

            } else {
                progressView.setColor(getResources().getColor(R.color.colorAccent));

            }

            textCounter.setText(String.format("%02d", (millisUntilFinished / 1000) / 60)
                    + ":" + String.format("%02d", (millisUntilFinished / 1000) % 60));
        }

        @Override
        public void onFinish() {

            textCounter.setText("Finished");
            progressView.setProgress(0);
            buttonStop.setVisibility(View.GONE);
            buttonStart.setVisibility(View.VISIBLE);


        }
    }
}