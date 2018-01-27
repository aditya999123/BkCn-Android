package com.kyb.hack36.kyb.Home.presenter;

/**
 * Created by iket on 27/1/18.
 */

public interface QrPresenterInterface {
    void getProductData(String prod_id,String pk);
    void transfer(String prod_id,String pk,String receiver);
    void make_txn(String prod_id,String pk,String receiver);
}
