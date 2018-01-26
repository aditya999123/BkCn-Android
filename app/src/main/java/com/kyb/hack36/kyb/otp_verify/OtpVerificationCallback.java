package com.kyb.hack36.kyb.otp_verify;


import com.kyb.hack36.kyb.otp_verify.model.OtpData;

public interface OtpVerificationCallback {

    void onOtpVerified(OtpData otpData);
    void onFailure(String error);
}
