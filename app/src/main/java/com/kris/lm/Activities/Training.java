package com.kris.lm.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.kris.lm.Fragments.FragmentIntro;
import com.kris.lm.R;

public class Training extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        params.screenBrightness = 100;
        getWindow().setAttributes(params);

        if (savedInstanceState != null) {
            //Restore the fragment's state here
        }else {
            Fragment fragmentIntro = new FragmentIntro();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction
                    ().replace(R.id.frame_left, fragmentIntro).commit();
        }

        Log.d("Training LifeCycle: ", "onCreate");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

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
