package com.cashmyproperty.app.View.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {
    @SerializedName("image_id")
    @Expose
    private Long imageId;
    @SerializedName("property_id")
    @Expose
    private Long propertyId;
    @SerializedName("property_image")
    @Expose
    private String propertyImage;
    @SerializedName("creation_date")
    @Expose
    private Integer creationDate;
    @SerializedName("_id")
    @Expose
    private Id id;

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Image withImageId(Long imageId) {
        this.imageId = imageId;
        return this;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public Image withPropertyId(Long propertyId) {
        this.propertyId = propertyId;
        return this;
    }

    public String getPropertyImage() {
        return propertyImage;
    }

    public void setPropertyImage(String propertyImage) {
        this.propertyImage = propertyImage;
    }

    public Image withPropertyImage(String propertyImage) {
        this.propertyImage = propertyImage;
        return this;
    }

    public Integer getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Integer creationDate) {
        this.creationDate = creationDate;
    }

    public Image withCreationDate(Integer creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Image withId(Id id) {
        this.id = id;
        return this;
    }
}
