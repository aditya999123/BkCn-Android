package com.kyb.hack36.kyb.login.model;

import com.kyb.hack36.kyb.login.LoginCallback;


public interface ProviderInterface {
    void loginData(String name, String mobile, String email, final LoginCallback loginCallback);
}
