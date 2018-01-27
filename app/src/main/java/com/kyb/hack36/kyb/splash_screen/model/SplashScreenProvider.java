package com.kyb.hack36.kyb.splash_screen.model;


import com.kyb.hack36.kyb.splash_screen.model.data.SplashScreenData;

import rx.Observable;


public interface SplashScreenProvider {
    Observable<SplashScreenData> insertFcm(String fcm, String access_token);
}
