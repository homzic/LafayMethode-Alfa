package com.kris.lm.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.kris.lm.Common.OnSwipeTouchListener;
import com.kris.lm.Fragments.OneExcercise;
import com.kris.lm.Fragments.ResultsExe;
import com.kris.lm.Fragments.StoperMini;
import com.kris.lm.R;
import com.kris.lm.Training.StageStretching;
import com.kris.lm.Training.StageWarmUp;
import com.kris.lm.Training.Workout;


public class Training extends AppCompatActivity implements StoperMini.stoperStatus {
    Fragment fragmentTop, fragmentBottom;
    FrameLayout frameTOP, frameBOTTOM;
    RelativeLayout introLayout;
    LinearLayout trainingLayout;
    TextView topExc, txtIntro;
    int exeNumber = 0;
    int stageTraining = 0;
    ButtonRectangle btnGo;
    public static String ODLICZAJ_TRAINING = "time";
    public static String IMAGE_NAME = "image";
    public static String ROUND = "round";
    public static String REPEATES = "repeats";
    public static String EXE_NAME = "exc_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        introLayout = (RelativeLayout) findViewById(R.id.introLayout);
        trainingLayout = (LinearLayout) findViewById(R.id.trainingLayout);

        topExc = (TextView) this.findViewById(R.id.txtTopExc);
        btnGo = (ButtonRectangle) findViewById(R.id.btnGo);
        frameTOP = (FrameLayout) findViewById(R.id.frameTop);
        frameBOTTOM = (FrameLayout) findViewById(R.id.frameBottom);
        txtIntro = (TextView) findViewById(R.id.textIntro);

        hideStatusBar();


        //--blokada wygaszania ekranu
        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        params.screenBrightness = 100;
        getWindow().setAttributes(params);

        trainingLayout.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeLeft() {
                provideStoperStatus(false);
            }

            public void onSwipeTop() {
                hideStatusBar();
            }

            public void onSwipeBottom() {
                showBar();
            }

            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });


        Log.d("Training LifeCycle: ", "onCreate");
    }

    public void provideStoperStatus(boolean s) {
        int timer;
        String topText, imgName, repeats, round;
        boolean chronoAutoStart;
        switch (stageTraining) {
            case 0:
                if (!s && exeNumber < StageWarmUp.timeUP.length) {
                    timer = StageWarmUp.timeUP[exeNumber];
                    topText = StageWarmUp.exeTopDescription[exeNumber];
                    imgName = StageWarmUp.exeNameImage[exeNumber];
                    chronoAutoStart = StageWarmUp.chronoAutoStart[exeNumber];
                    repeats = StageWarmUp.REPEATS[exeNumber];
                    round = StageWarmUp.ROUND[exeNumber];
                    excerciseInflate(timer, topText, imgName, chronoAutoStart, repeats, round);
                    exeNumber++;
                } else {
                    exeNumber = 0;
                    stageTraining++;
                    pageIntroInflate();

                }
                break;
            case 1:
                if (!s && exeNumber < Workout.TestBody.TIME_UP.length) {
                    timer = Workout.TestBody.TIME_UP[exeNumber];
                    topText = Workout.TestBody.TOP_DESCRIPTION[exeNumber];
                    imgName = Workout.TestBody.NAME_IMAGE[exeNumber];
                    chronoAutoStart = Workout.TestBody.STOPER_AUTO_START[exeNumber];
                    repeats = Workout.TestBody.REPEATS[exeNumber];
                    round = Workout.TestBody.ROUND[exeNumber];
                    excerciseInflate(timer, topText, imgName, chronoAutoStart, repeats, round);
                    exeNumber++;
                } else {
                    exeNumber = 0;
                    stageTraining++;
                    pageIntroInflate();

                }
                break;
            case 2:
                if (!s && exeNumber < StageStretching.timeUP.length) {
                    timer = StageStretching.timeUP[exeNumber];
                    topText = StageStretching.exeTopDescription[exeNumber];
                    imgName = StageStretching.exeNameImage[exeNumber];
                    chronoAutoStart = StageStretching.chronoAutoStart[exeNumber];
                    repeats = StageStretching.REPEATS[exeNumber];
                    round = StageStretching.ROUND[exeNumber];
                    excerciseInflate(timer, topText, imgName, chronoAutoStart, repeats, round);
                    exeNumber++;
                } else {
                    exeNumber = 0;
                    stageTraining++;
                    pageIntroInflate();

                }
                break;
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

    }


    void excerciseInflate(int timer, String exeTopName, String imgName, Boolean chronoAS, String reapeats, String round) {
        timer = timer * 1000;
        Bundle bundle = new Bundle();
        introLayout.setVisibility(View.GONE);
        trainingLayout.setVisibility(View.VISIBLE);

        //ustaw parametry cwiczenia
        bundle.putString(REPEATES, reapeats);
        bundle.putString(ROUND, round);
        bundle.putString(EXE_NAME, imgName);


                FragmentManager fragmentManager = getFragmentManager();

        //pokaz nazwe ciwczenia
        topExc.setVisibility(View.VISIBLE);
        topExc.setText(exeTopName);

        //zaladuj fragment z cwiczeniem

        bundle.putString(IMAGE_NAME, imgName);
        fragmentTop = new OneExcercise();
        fragmentTop.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.frameTop, fragmentTop, "Frag_Top").commit();


        //ustaw parametry stopera

        bundle.putLong(ODLICZAJ_TRAINING, timer);
        bundle.putBoolean("autostart", chronoAS);
        if (!chronoAS)
            fragmentBottom = new ResultsExe();
        else
            fragmentBottom = new StoperMini();
        fragmentBottom.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.frameBottom, fragmentBottom, "Frag_Bottom").commit();

    }

    void pageIntroInflate() {
        topExc.setVisibility(View.GONE);
        trainingLayout.setVisibility(View.GONE);
        introLayout.setVisibility(View.VISIBLE);
        switch (stageTraining) {
            case 0:
                txtIntro.setText("Warm Up");
                break;
            case 1:
                txtIntro.setText("Workout");
                break;
            case 2:
                txtIntro.setText("Stretching");
                break;
            case 3:
                txtIntro.setText("Training done \n\n Congratulation!");

        }

    }

    public void btnGO(View view) {
        provideStoperStatus(false);

        Log.d("Training LifeCycle: ", "btnGO");
    }


    @Override
    protected void onStart() {
        Log.d("Training LifeCycle: ", "onStart");
        super.onStart();
    }

    public void showBar() {

        ActionBar actionBar = getSupportActionBar();
        View decorView = getWindow().getDecorView();
        if (actionBar != null) {
            actionBar.show();
        }
        int uiOptions = View.STATUS_BAR_VISIBLE;
        decorView.setSystemUiVisibility(uiOptions);
    }

    void hideStatusBar() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Hide the status bar.
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public void onResume() {
        Log.d("Training LifeCycle: ", "onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d("Training LifeCycle: ", "onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("Training LifeCycle: ", "onStop");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.d("Training LifeCycle: ", "onDestroy");
        super.onDestroy();
    }


}
