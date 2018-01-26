package com.kyb.hack36.kyb.otp_verify.presenter;

public interface OtpVerifyPresenter {

    void otpData(String otp, String mobile, String access_token);
    void onDestroy();
}
