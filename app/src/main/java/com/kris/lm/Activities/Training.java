package com.kris.lm.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.kris.lm.Fragments.OneExcercise;
import com.kris.lm.Fragments.StoperMini;
import com.kris.lm.R;

public class Training extends AppCompatActivity {
    Fragment fragmentTop, fragmentBottom;
    FrameLayout frameLeft, frameRigth;
    RelativeLayout introLayout;
    LinearLayout trainingLayout;
    TextView topExc, txtIntro;
    ButtonRectangle btnGo;

    public static String odliczaj_training="time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        introLayout = (RelativeLayout) findViewById(R.id.introLayout);
        trainingLayout = (LinearLayout) findViewById(R.id.trainingLayout);

        topExc = (TextView) this.findViewById(R.id.txtTopExc);
        btnGo = (ButtonRectangle) findViewById(R.id.btnGo);
        frameLeft = (FrameLayout) findViewById(R.id.framTop);
        frameRigth = (FrameLayout) findViewById(R.id.frameBottom);
        txtIntro = (TextView) findViewById(R.id.textIntro);
        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //--blokada wygaszania ekranu
        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        params.screenBrightness = 100;
        getWindow().setAttributes(params);


        Log.d("Training LifeCycle: ", "onCreate");
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

    }


    public void showBar(View view) {

        ActionBar actionBar = getSupportActionBar();
        View decorView = getWindow().getDecorView();
        if (actionBar.isShowing()) {

            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
            actionBar.hide();
        } else {
            actionBar.show();
            int uiOptions = View.STATUS_BAR_VISIBLE;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    void excerciseInflate(int t) {
        int timer;
        timer=t*1000;
        introLayout.setVisibility(View.GONE);
        trainingLayout.setVisibility(View.VISIBLE);

        FragmentManager fragmentManager = getFragmentManager();

        //pokaz nazwe ciwczenia
        topExc.setVisibility(View.VISIBLE);


        //zaladuj fragment z cwiczeniem
        fragmentTop = new OneExcercise();
        fragmentManager.beginTransaction()
                .replace(R.id.framTop, fragmentTop, "Frag_Left").commit();

         //ustaw parametry stopera
        Bundle bundleTimer = new Bundle();
        bundleTimer.putLong(odliczaj_training, timer);
        fragmentBottom = new StoperMini();
        fragmentBottom.setArguments(bundleTimer);
        fragmentManager.beginTransaction()
                .replace(R.id.frameBottom, fragmentBottom, "Frag_Right").commit();

    }

    void startTraining(){

    }

    public void btnGO(View view) {
        excerciseInflate(15);
        Log.d("Training LifeCycle: ", "btnGO");
    }


    @Override
    protected void onStart() {
        Log.d("Training LifeCycle: ", "onStart");
        super.onStart();
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
