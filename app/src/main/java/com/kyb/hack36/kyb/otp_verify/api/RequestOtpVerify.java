package com.kyb.hack36.kyb.otp_verify.api;

import com.kyb.hack36.kyb.helper.Urls;
import com.kyb.hack36.kyb.otp_verify.model.OtpData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by samveg on 30/7/17.
 */

public interface RequestOtpVerify {

    @FormUrlEncoded
    @POST(Urls.REQUEST_VERIFY)
    Call<OtpData> getJson(@Field("otp") String otp, @Field("mobile") String mobile, @Field("token") String access_token);
}
