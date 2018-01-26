package com.kyb.hack36.kyb.login.view;


import com.kyb.hack36.kyb.login.model.LoginData;

public interface LoginView {

    void showProgressBar(boolean show);
    void showLoginStatus(LoginData loginDataResponse);
    void showError(String message);
    void checkNetwork();
}
