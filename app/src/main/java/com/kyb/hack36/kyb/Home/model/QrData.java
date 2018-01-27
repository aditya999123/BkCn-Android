package com.kyb.hack36.kyb.Home.model;

/**
 * Created by iket on 27/1/18.
 */

public class QrData {
    private String manufacturer;
    private String owner,message;
    private Boolean success;

    public QrData(String manufacturer, String owner, String message, Boolean success) {
        this.manufacturer = manufacturer;
        this.owner = owner;
        this.message = message;
        this.success = success;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getOwner() {
        return owner;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getSuccess() {
        return success;
    }
}
