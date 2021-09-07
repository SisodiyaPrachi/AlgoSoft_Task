package com.cashmyproperty.app.View.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AreaMeasurement {

    @SerializedName("measurment")
    @Expose
    private String measurment;

    public String getMeasurment() {
        return measurment;
    }

    public void setMeasurment(String measurment) {
        this.measurment = measurment;
    }

    public AreaMeasurement withMeasurment(String measurment) {
        this.measurment = measurment;
        return this;
    }

}
