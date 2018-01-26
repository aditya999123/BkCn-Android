package com.kyb.hack36.kyb.otp_verify.model;

public class OtpData {

    private String message;
    private boolean success;
    public OtpData(String message, boolean success)
    {
        this.message=message;
        this.success=success;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
