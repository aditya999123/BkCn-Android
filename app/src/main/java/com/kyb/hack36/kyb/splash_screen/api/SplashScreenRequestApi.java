package com.kyb.hack36.kyb.splash_screen.api;


import com.kyb.hack36.kyb.helper.Urls;
import com.kyb.hack36.kyb.splash_screen.model.data.SplashScreenData;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;


public interface SplashScreenRequestApi {
    @FormUrlEncoded
    @POST(Urls.REQUEST_SPLASH_SCREEN)
    Observable<SplashScreenData> insertFcm(@Field("fcm") String fcm, @Field("token") String access_token);

}
