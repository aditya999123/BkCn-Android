package com.kyb.hack36.kyb.Home;

import com.kyb.hack36.kyb.Home.model.QrData;

/**
 * Created by iket on 27/1/18.
 */

public interface HomeCallBack {
    void onSuccess(QrData qrData);
    void onFailure(String error);
}
