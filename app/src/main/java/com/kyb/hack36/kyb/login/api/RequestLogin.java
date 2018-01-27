package com.kyb.hack36.kyb.login.api;


import com.kyb.hack36.kyb.helper.Urls;
import com.kyb.hack36.kyb.login.model.LoginData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RequestLogin {

    @FormUrlEncoded
    @POST(Urls.REQUEST_LOGIN)
    Call<LoginData> getJSON(@Field("mobile") String mobile, @Field("public_key") String public_key);
}
