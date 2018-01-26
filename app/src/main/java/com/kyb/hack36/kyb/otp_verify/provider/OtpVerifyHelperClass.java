package com.kyb.hack36.kyb.otp_verify.provider;


import com.kyb.hack36.kyb.otp_verify.OtpVerificationCallback;

public interface OtpVerifyHelperClass {

    void getOtpResponse(String otp, String mobile, String access_token, OtpVerificationCallback otpVerificationCallback);
    void onDestroy();
}
