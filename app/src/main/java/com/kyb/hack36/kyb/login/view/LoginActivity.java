package com.kyb.hack36.kyb.login.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.kyb.hack36.kyb.Home.view.Home;
import com.kyb.hack36.kyb.R;
import com.kyb.hack36.kyb.helper.NetworkUtils;
import com.kyb.hack36.kyb.helper.SharedPrefs;
import com.kyb.hack36.kyb.login.model.LoginData;
import com.kyb.hack36.kyb.login.model.RetrofitProvider;
import com.kyb.hack36.kyb.login.presenter.LoginPresenter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private EditText editTextMobile, passwordEditText;
    private ProgressBar progressBar;
    public String mobile,password;
    private LoginPresenter loginPresenter;
    private SharedPrefs sharedPrefs;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPrefs = new SharedPrefs(this);
        initialise();

    }

    public void initialise() {
        editTextMobile = (EditText) findViewById(R.id.input_mobile);

        passwordEditText = (EditText) findViewById(R.id.input_email);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        editTextMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() == 10) {
                    hideKeyboard();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void proceed(View v) {

        mobile = editTextMobile.getText().toString();
        password = passwordEditText.getText().toString();
        password = getHash(password);
        Log.d("pk",password);

        if (mobile.isEmpty()  || password.isEmpty()) {
            showProgressBar(false);
            showError("Fields cannot be empty");
        }
        if(mobile.length()!=10){
            Toast.makeText(LoginActivity.this, "YOU HAVE ENTERED AN INCORRECT MOBILE NUMBER!",
                    Toast.LENGTH_LONG).show();
        }
        else {
            loginPresenter= new LoginPresenter(LoginActivity.this,new RetrofitProvider());
            loginPresenter.getLoginData(mobile, password);
            hideKeyboard();
        }

    }

    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showLoginStatus(LoginData loginData) {
            editTextMobile.setVisibility(View.GONE);
            sharedPrefs.setMobile(mobile);
            sharedPrefs.setKeyPublicKey(password);
            sharedPrefs.setAccessToken(loginData.getToken());
        Intent in=new Intent(LoginActivity.this, Home.class);
        startActivity(in);
        finish();
    }

    @Override
    public void showError(String message) {
        password = passwordEditText.getText().toString();
        password = getHash(password);
        Log.d("pk",password);

        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void checkNetwork() {
        if(!NetworkUtils.isNetworkAvailable(this)){
            dialog = new Dialog(this);
            dialog.setContentView(R.layout.activity_rules__dialog_box);
            Button btn = (Button) dialog.findViewById(R.id.dialog_button);
            TextView rules5 = (TextView) dialog.findViewById(R.id.rules5);
            btn.setText("Retry");
            rules5.setText("No internet connection.Please try again.");
            dialog.setTitle("Connectivity Failed");
            dialog.setCancelable(false);
            dialog.show();
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    loginPresenter= new LoginPresenter(LoginActivity.this,new RetrofitProvider());
                    loginPresenter.getLoginData(mobile, password);
                    dialog.dismiss();
                }
            });
        }
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    String getHash(String input)
    {
        String password =input;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return input;
        }
        md.update(password.getBytes());
        byte byteData[] = md.digest();
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
        for (int i=0;i<byteData.length;i++) {
            String hex=Integer.toHexString(0xff & byteData[i]);
            if(hex.length()==1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }


}
