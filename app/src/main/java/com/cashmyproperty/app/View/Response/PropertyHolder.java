package com.cashmyproperty.app.View.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PropertyHolder {

    @SerializedName("property_holder_name")
    @Expose
    private String propertyHolderName;
    @SerializedName("property_holder_slug")
    @Expose
    private String propertyHolderSlug;
    @SerializedName("property_holder_web_image_alt")
    @Expose
    private String propertyHolderWebImageAlt;
    @SerializedName("property_holder_app_image_alt")
    @Expose
    private String propertyHolderAppImageAlt;
    @SerializedName("property_holder_id")
    @Expose
    private Long propertyHolderId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("_id")
    @Expose
    private Id__1 id;

    public String getPropertyHolderName() {
        return propertyHolderName;
    }

    public void setPropertyHolderName(String propertyHolderName) {
        this.propertyHolderName = propertyHolderName;
    }

    public PropertyHolder withPropertyHolderName(String propertyHolderName) {
        this.propertyHolderName = propertyHolderName;
        return this;
    }

    public String getPropertyHolderSlug() {
        return propertyHolderSlug;
    }

    public void setPropertyHolderSlug(String propertyHolderSlug) {
        this.propertyHolderSlug = propertyHolderSlug;
    }

    public PropertyHolder withPropertyHolderSlug(String propertyHolderSlug) {
        this.propertyHolderSlug = propertyHolderSlug;
        return this;
    }

    public String getPropertyHolderWebImageAlt() {
        return propertyHolderWebImageAlt;
    }

    public void setPropertyHolderWebImageAlt(String propertyHolderWebImageAlt) {
        this.propertyHolderWebImageAlt = propertyHolderWebImageAlt;
    }

    public PropertyHolder withPropertyHolderWebImageAlt(String propertyHolderWebImageAlt) {
        this.propertyHolderWebImageAlt = propertyHolderWebImageAlt;
        return this;
    }

    public String getPropertyHolderAppImageAlt() {
        return propertyHolderAppImageAlt;
    }

    public void setPropertyHolderAppImageAlt(String propertyHolderAppImageAlt) {
        this.propertyHolderAppImageAlt = propertyHolderAppImageAlt;
    }

    public PropertyHolder withPropertyHolderAppImageAlt(String propertyHolderAppImageAlt) {
        this.propertyHolderAppImageAlt = propertyHolderAppImageAlt;
        return this;
    }

    public Long getPropertyHolderId() {
        return propertyHolderId;
    }

    public void setPropertyHolderId(Long propertyHolderId) {
        this.propertyHolderId = propertyHolderId;
    }

    public PropertyHolder withPropertyHolderId(Long propertyHolderId) {
        this.propertyHolderId = propertyHolderId;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PropertyHolder withStatus(String status) {
        this.status = status;
        return this;
    }

    public Id__1 getId() {
        return id;
    }

    public void setId(Id__1 id) {
        this.id = id;
    }

    public PropertyHolder withId(Id__1 id) {
        this.id = id;
        return this;
    }


}
