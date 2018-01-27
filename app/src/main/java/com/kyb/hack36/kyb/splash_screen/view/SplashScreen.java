package com.kyb.hack36.kyb.splash_screen.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kyb.hack36.kyb.Home.view.Home;
import com.kyb.hack36.kyb.R;
import com.kyb.hack36.kyb.helper.MyApplication;
import com.kyb.hack36.kyb.helper.SharedPrefs;
import com.kyb.hack36.kyb.login.view.LoginActivity;
import com.kyb.hack36.kyb.splash_screen.model.RetrofitSplashScreenProvider;
import com.kyb.hack36.kyb.splash_screen.model.data.SplashScreenData;
import com.kyb.hack36.kyb.splash_screen.presenter.SplashScreenPresenter;
import com.kyb.hack36.kyb.splash_screen.presenter.SplashScreenPresenterImpl;

public class SplashScreen extends Activity implements  SplashScreenView{

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

        if (sharedPrefs.isLoggedIn()) {
            Intent in = new Intent(SplashScreen.this, Home.class);
            startActivity(in);
            finish();
        } else {
            Intent signIn = new Intent(SplashScreen.this, LoginActivity.class);
            startActivity(signIn);
            finish();
        }


        SplashScreenPresenter splashScreenPresenter = new SplashScreenPresenterImpl(this, new RetrofitSplashScreenProvider());
//        splashScreenPresenter.insertFcm(MyApplication.fcm_token,sharedPrefs.getAccessToken());
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(SplashScreen.this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void fcmInsertStatus(final SplashScreenData splashScreenData) {
         if (splashScreenData.isSuccess()) {
            sharedPrefs.setFCM(MyApplication.fcm_token);

            if (sharedPrefs.isLoggedIn()) {
                Intent in = new Intent(SplashScreen.this, Home.class);
                startActivity(in);
                finish();
            } else {
                Intent signIn = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(signIn);
                finish();
            }

        }
    }

    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            splashProgressBar.setVisibility(View.VISIBLE);
        } else {
            splashProgressBar.setVisibility(View.GONE);
        }
    }
}


