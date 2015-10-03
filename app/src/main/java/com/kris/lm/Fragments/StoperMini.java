package com.kris.lm.Fragments;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.kris.lm.Common.OnSwipeTouchListener;
import com.kris.lm.DB.DB_Helper;
import com.kris.lm.R;

import static com.kris.lm.DB.DB_Helper.closeDB;


public class StoperMini extends Fragment {

    private Vibrator v;
    private ImageButton imgSound;
    private SharedPreferences dataSettings;
    private final long[] vibrationPattern = {0, 1000, 1000, 2000};
    private TextView textCounter;
    private long odliczaj;
    private final long interval = 1000;
    private CircularProgressView progressView;
    private MyCountDownTimer myCountDownTimer;
    MediaPlayer mediaPlayer;
    private Boolean sound = true;
    stoperStatus mCallback;
    boolean isStoperRunning = false;

    public StoperMini() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_stoper_mini, container, false);
        Context context = rootView.getContext();

        Bundle bundle = getArguments();
        odliczaj = bundle.getLong("time");
        boolean autoStartChrono = bundle.getBoolean("autostart");

        progressView = (CircularProgressView) rootView.findViewById(R.id.progress_view);

        v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        //ustaw prgoresbary na 100 i dostosuj skale do odliczanego czasu
        progressView.setProgress(100);
        progressView.setMaxProgress(odliczaj / 1000);
        textCounter = (TextView) rootView.findViewById(R.id.counter);
        textCounter.setText("Start");
        if (autoStartChrono)
            autoStartChrono();

        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d("Stoper LifeCycle: ", "onStart");
        imgSound = (ImageButton) getView().findViewById(R.id.imgSound);
        imgSound.setImageResource(R.drawable.ic_sound_on);
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.evil_laugh);
        //ustaw dziwek
        imgSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sound) setSoundOn(false);
                else setSoundOn(true);
            }
        });
        textCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ustaw licznik
                if (textCounter.getText() == "Start") {
                    autoStartChrono();
                } else {
                    myCountDownTimer.cancel();
                    textCounter.setText("Start");
                }
            }
        });


        super.onActivityCreated(savedInstanceState);
    }

    void autoStartChrono() {
        isStoperRunning = true;
        textCounter.setText(String.format("%02d", (odliczaj / 1000) / 60)
                + ":" + String.format("%02d", (odliczaj / 1000) % 60));
        myCountDownTimer = new MyCountDownTimer(odliczaj, interval);
        myCountDownTimer.cancel();
        //   odliczaj = 15 * 1000;
        progressView.setProgress(100);
        progressView.setMaxProgress(odliczaj / 1000);
        if (progressView.getProgress() == 100) {
            myCountDownTimer = new MyCountDownTimer(odliczaj, interval);
            myCountDownTimer.start();

        }

    }

    @Override
    public void onStart() {

        // autoStartChrono();
        Log.d("Stoper LifeCycle: ", "onCreateView");
        super.onStart();
    }

    @Override
    public void onPause() {
     if(isStoperRunning)   myCountDownTimer.cancel();
        Log.d("Stoper LifeCycle: ", "onPause");
        super.onPause();
    }

    @Override
    public void onResume() {
        Log.d("Stoper LifeCycle: ", "onResume");
        super.onResume();
    }


    private void setSoundOn(Boolean set) {
        if (set) {

            dataSettings = this.getActivity().getSharedPreferences("MyData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = dataSettings.edit();
            editor.putBoolean("sound", true);
            editor.apply();
            imgSound.setImageResource(R.drawable.ic_sound_on);
            sound = true;
        } else {
            dataSettings = this.getActivity().getSharedPreferences("MyData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = dataSettings.edit();
            editor.putBoolean("sound", false);
            editor.apply();
            sound = dataSettings.getBoolean("sound", false);
            imgSound.setImageResource(R.drawable.ic_sound_off);
            sound = false;
        }
    }

    @Override
    public void onStop() {
        Log.d("Stoper LifeCycle: ", "onStop");
        super.onStop();
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
                    v.vibrate(vibrationPattern, -1);
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
            Log.d("Stoper LifeCycle: ", "onFinish");
            textCounter.setText("Finished");
            isStoperRunning = false;
            mCallback.provideStoperStatus(isStoperRunning);
            progressView.setProgress(0);
          //  if (sound) mediaPlayer.start();

        }
    }

    public interface stoperStatus {
        void provideStoperStatus(boolean s);

    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallback = (stoperStatus) activity;
    }

}
