package com.kyb.hack36.kyb.login.model;

import com.kyb.hack36.kyb.login.LoginCallback;


public interface ProviderInterface {
    void loginData(String mobile, String public_key, final LoginCallback loginCallback);
}
