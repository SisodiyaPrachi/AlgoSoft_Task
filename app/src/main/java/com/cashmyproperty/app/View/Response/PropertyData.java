package com.cashmyproperty.app.View.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PropertyData {

    @SerializedName("property_id")
    @Expose
    private Long propertyId;

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public PropertyData withPropertyId(Long propertyId) {
        this.propertyId = propertyId;
        return this;
    }
}
