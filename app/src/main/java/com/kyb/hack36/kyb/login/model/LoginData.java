package com.kyb.hack36.kyb.login.model;


public class LoginData {

    private boolean success;
    private String message, token;
    public LoginData(boolean success, String message, String token)
    {
        this.message=message;
        this.success=success;
        this.token = token;
    }


    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
    public String getToken(){
        return token;
    }
}
