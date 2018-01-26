package com.kyb.hack36.kyb.otp_verify.view;


import com.kyb.hack36.kyb.otp_verify.model.OtpData;

public interface OtpView {

    void showProgressBar(boolean show);
    void showMessage(String message);
    void OtpStatus(OtpData otpData);
    void checkNetwork();
    void verify_bttn_clickable();
}
