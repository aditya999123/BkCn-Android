package com.kyb.hack36.kyb.Home.model;

import com.kyb.hack36.kyb.Home.HomeCallBack;
import com.kyb.hack36.kyb.Home.api.QrRequest;
import com.kyb.hack36.kyb.helper.Urls;
import com.kyb.hack36.kyb.login.api.RequestLogin;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iket on 27/1/18.
 */

public class Qr_Provider implements QrProviderInterface{

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
    Retrofit retrofit=new Retrofit.Builder().baseUrl(Urls.BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();
    QrRequest qrRequest = retrofit.create(QrRequest.class);

    @Override
    public void getProductData(String prod_id, String pk, final HomeCallBack homeCallBack) {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Call<QrData>call=qrRequest.getQR(prod_id,pk);
        call.enqueue(new Callback<QrData>() {
            @Override
            public void onResponse(Call<QrData> call, Response<QrData> response) {
                homeCallBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<QrData> call, Throwable t) {
                t.printStackTrace();
                homeCallBack.onFailure("No Internet Connection");
            }
        });
    }

    @Override
    public void transfer(String prod_id, String pk, String receiver, final HomeCallBack homeCallBack) {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Call<QrData>call=qrRequest.sendQR(prod_id,pk,receiver);
        call.enqueue(new Callback<QrData>() {
            @Override
            public void onResponse(Call<QrData> call, Response<QrData> response) {
                homeCallBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<QrData> call, Throwable t) {
                t.printStackTrace();
                homeCallBack.onFailure("No Internet Connection");
            }
        });
    }
}
