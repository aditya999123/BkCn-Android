package com.kyb.hack36.kyb.login;


import com.kyb.hack36.kyb.login.model.LoginData;


public interface LoginCallback {

    void onLoginSuccess(LoginData loginResponse);
    void onLoginFailure(String error);

}
