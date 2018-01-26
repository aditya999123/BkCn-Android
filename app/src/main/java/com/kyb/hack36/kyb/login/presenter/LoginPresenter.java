package com.kyb.hack36.kyb.login.presenter;


import com.kyb.hack36.kyb.login.LoginCallback;
import com.kyb.hack36.kyb.login.model.LoginData;
import com.kyb.hack36.kyb.login.model.RetrofitProvider;
import com.kyb.hack36.kyb.login.view.LoginView;

public class LoginPresenter implements LoginPresenterImpl {

    private RetrofitProvider retrofitProvider;
    private LoginView login;

    public LoginPresenter(LoginView login,RetrofitProvider retrofitProvider) {
        this.retrofitProvider = retrofitProvider;
        this.login = login;
    }

    @Override
    public void getLoginData(String name,String mobile,String email) {

        login.showProgressBar(true);
        retrofitProvider.loginData(name,mobile,email,new LoginCallback() {
            @Override
            public void onLoginSuccess(LoginData loginResponse) {
                if(loginResponse.isSuccess()) {
                    login.showProgressBar(false);
                    login.showLoginStatus(loginResponse);
                }
                else{
                    login.showProgressBar(false);
                    login.showError(loginResponse.getMessage());
                }
            }
            @Override
            public void onLoginFailure(String error) {
                login.showError("Sorry!!Something went wrong");
                login.showProgressBar(false);
                login.checkNetwork();
            }
        });
    }
}
