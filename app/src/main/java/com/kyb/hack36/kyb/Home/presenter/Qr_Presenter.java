package com.kyb.hack36.kyb.Home.presenter;

import com.kyb.hack36.kyb.Home.HomeCallBack;
import com.kyb.hack36.kyb.Home.model.QrData;
import com.kyb.hack36.kyb.Home.model.QrProviderInterface;
import com.kyb.hack36.kyb.Home.view.QrViewInterface;

/**
 * Created by iket on 27/1/18.
 */

public class Qr_Presenter implements QrPresenterInterface{
    private QrViewInterface qrViewInterface;
    private QrProviderInterface qrProviderInterface;

    public Qr_Presenter(QrViewInterface qrViewInterface, QrProviderInterface qrProviderInterface) {
        this.qrViewInterface = qrViewInterface;
        this.qrProviderInterface = qrProviderInterface;
    }

    @Override
    public void getProductData(String prod_id, String pk) {
        qrViewInterface.showProgressBAr(true);
        qrProviderInterface.getProductData(prod_id, pk, new HomeCallBack() {
            @Override
            public void onSuccess(QrData qrData) {
                qrViewInterface.viewer(qrData);
                qrViewInterface.showProgressBAr(false);
            }

            @Override
            public void onFailure(String error) {
                qrViewInterface.showProgressBAr(false);
                qrViewInterface.showError(error);
            }
        });
    }

    @Override
    public void transfer(String prod_id, String pk, String receiver) {
        qrViewInterface.showProgressBAr(true);
        qrProviderInterface.getProductData(prod_id, pk, new HomeCallBack() {
            @Override
            public void onSuccess(QrData qrData) {
//                qrViewInterface.viewer(qrData);
                qrViewInterface.showProgressBAr(false);
                if(qrData.getSuccess())
                    qrViewInterface.user_authenticated(qrData);
                else
                    qrViewInterface.display(qrData);
            }

            @Override
            public void onFailure(String error) {
                qrViewInterface.showProgressBAr(false);
                qrViewInterface.showError(error);
            }
        });
    }

    @Override
    public void make_txn(String prod_id, String pk, String receiver) {
        qrViewInterface.showProgressBAr(true);
        qrProviderInterface.transfer(prod_id, pk, receiver, new HomeCallBack() {
            @Override
            public void onSuccess(QrData qrData) {
                qrViewInterface.showError(qrData.getMessage());
                qrViewInterface.showProgressBAr(false);
            }

            @Override
            public void onFailure(String error) {
                qrViewInterface.showProgressBAr(false);
                qrViewInterface.showError(error);
            }

        });
    }
}
