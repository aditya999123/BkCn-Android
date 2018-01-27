package com.kyb.hack36.kyb.splash_screen.view;


import com.kyb.hack36.kyb.splash_screen.model.data.SplashScreenData;

public interface SplashScreenView {

    void showMessage(String message);

    void fcmInsertStatus(SplashScreenData splashScreenData);

    void showProgressBar(boolean show);
}
