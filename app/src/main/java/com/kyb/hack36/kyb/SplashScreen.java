package com.kyb.hack36.kyb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;

import com.kyb.hack36.kyb.Home.view.Home;
import com.kyb.hack36.kyb.helper.MyApplication;
import com.kyb.hack36.kyb.helper.SharedPrefs;
import com.kyb.hack36.kyb.login.view.LoginActivity;

public class SplashScreen extends Activity{

    SharedPrefs sharedPrefs;
    MyApplication myApplication;
    ProgressBar progressBar,splashProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sharedPrefs = new SharedPrefs(this);
        splashProgressBar=(ProgressBar)findViewById(R.id.splash_progress_bar);
        Log.d("Splash sceen",""+MyApplication.fcm_token);


        final Intent in;
        if (sharedPrefs.isLoggedIn())
            in=new Intent(SplashScreen.this, Home.class);
        else in=new Intent(SplashScreen.this, LoginActivity.class);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(in);
                finish();
            }
        },5000);
    }
}


