package com.kyb.hack36.kyb.Home.api;

import com.kyb.hack36.kyb.Home.model.QrData;
import com.kyb.hack36.kyb.helper.Keys;
import com.kyb.hack36.kyb.helper.Urls;
import com.kyb.hack36.kyb.login.model.LoginData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by iket on 27/1/18.
 */

public interface QrRequest {
    @FormUrlEncoded
    @POST(Urls.REQUEST_QR_DATA)
    Call<QrData> getQR(@Field(Keys.KEY_PRODUCT_ID) String prod_id, @Field("pk") String pk);

    @FormUrlEncoded
    @POST(Urls.REQUEST_QR_DATA)
    Call<QrData> sendQR(@Field(Keys.KEY_PRODUCT_ID) String prod_id, @Field("pk") String pk,@Field("receiver") String receiver);
}
