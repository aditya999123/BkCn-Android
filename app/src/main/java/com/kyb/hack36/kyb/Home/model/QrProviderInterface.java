package com.kyb.hack36.kyb.Home.model;

import com.kyb.hack36.kyb.Home.HomeCallBack;

/**
 * Created by iket on 27/1/18.
 */

public interface QrProviderInterface {
    void getProductData(String prod_id, String pk, HomeCallBack homeCallBack);
    void transfer(String prod_id,String pk,String receiver, HomeCallBack homeCallBack);
}
