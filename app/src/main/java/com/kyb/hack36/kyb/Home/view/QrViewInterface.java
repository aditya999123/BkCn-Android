package com.kyb.hack36.kyb.Home.view;

import com.kyb.hack36.kyb.Home.model.QrData;

/**
 * Created by iket on 27/1/18.
 */

public interface QrViewInterface {
    void showProgressBAr(Boolean bool);
    void user_authenticated(QrData qrData);
    void viewer(QrData qrData);
    void display(QrData qrData);
    void showError(String error);
}
