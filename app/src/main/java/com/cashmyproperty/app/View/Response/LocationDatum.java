package com.cashmyproperty.app.View.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LocationDatum {

    @SerializedName("location_name")
    @Expose
    private String locationName;
    @SerializedName("location_slug")
    @Expose
    private String locationSlug;
    @SerializedName("location_web_image")
    @Expose
    private String locationWebImage;
    @SerializedName("location_web_image_alt")
    @Expose
    private String locationWebImageAlt;
    @SerializedName("location_app_image")
    @Expose
    private String locationAppImage;
    @SerializedName("location_app_image_alt")
    @Expose
    private String locationAppImageAlt;
    @SerializedName("location_id")
    @Expose
    private Long locationId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("_id")
    @Expose
    private Id id;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public LocationDatum withLocationName(String locationName) {
        this.locationName = locationName;
        return this;
    }

    public String getLocationSlug() {
        return locationSlug;
    }

    public void setLocationSlug(String locationSlug) {
        this.locationSlug = locationSlug;
    }

    public LocationDatum withLocationSlug(String locationSlug) {
        this.locationSlug = locationSlug;
        return this;
    }

    public String getLocationWebImage() {
        return locationWebImage;
    }

    public void setLocationWebImage(String locationWebImage) {
        this.locationWebImage = locationWebImage;
    }

    public LocationDatum withLocationWebImage(String locationWebImage) {
        this.locationWebImage = locationWebImage;
        return this;
    }

    public String getLocationWebImageAlt() {
        return locationWebImageAlt;
    }

    public void setLocationWebImageAlt(String locationWebImageAlt) {
        this.locationWebImageAlt = locationWebImageAlt;
    }

    public LocationDatum withLocationWebImageAlt(String locationWebImageAlt) {
        this.locationWebImageAlt = locationWebImageAlt;
        return this;
    }

    public String getLocationAppImage() {
        return locationAppImage;
    }

    public void setLocationAppImage(String locationAppImage) {
        this.locationAppImage = locationAppImage;
    }

    public LocationDatum withLocationAppImage(String locationAppImage) {
        this.locationAppImage = locationAppImage;
        return this;
    }

    public String getLocationAppImageAlt() {
        return locationAppImageAlt;
    }

    public void setLocationAppImageAlt(String locationAppImageAlt) {
        this.locationAppImageAlt = locationAppImageAlt;
    }

    public LocationDatum withLocationAppImageAlt(String locationAppImageAlt) {
        this.locationAppImageAlt = locationAppImageAlt;
        return this;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public LocationDatum withLocationId(Long locationId) {
        this.locationId = locationId;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocationDatum withStatus(String status) {
        this.status = status;
        return this;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public LocationDatum withId(Id id) {
        this.id = id;
        return this;
    }

}

