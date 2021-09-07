package com.cashmyproperty.app.View.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WishlistData {

    @SerializedName("users_id")
    @Expose
    private Long usersId;
    @SerializedName("property_id")
    @Expose
    private Long propertyId;
    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("creation_date")
    @Expose
    private Integer creationDate;
    @SerializedName("creation_lat")
    @Expose
    private String creationLat;
    @SerializedName("creation_long")
    @Expose
    private String creationLong;
    @SerializedName("wishlist_id")
    @Expose
    private Long wishlistId;

    public Long getUsersId() {
        return usersId;
    }

    public void setUsersId(Long usersId) {
        this.usersId = usersId;
    }

    public WishlistData withUsersId(Long usersId) {
        this.usersId = usersId;
        return this;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public WishlistData withPropertyId(Long propertyId) {
        this.propertyId = propertyId;
        return this;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public WishlistData withDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public Integer getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Integer creationDate) {
        this.creationDate = creationDate;
    }

    public WishlistData withCreationDate(Integer creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getCreationLat() {
        return creationLat;
    }

    public void setCreationLat(String creationLat) {
        this.creationLat = creationLat;
    }

    public WishlistData withCreationLat(String creationLat) {
        this.creationLat = creationLat;
        return this;
    }

    public String getCreationLong() {
        return creationLong;
    }

    public void setCreationLong(String creationLong) {
        this.creationLong = creationLong;
    }

    public WishlistData withCreationLong(String creationLong) {
        this.creationLong = creationLong;
        return this;
    }

    public Long getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Long wishlistId) {
        this.wishlistId = wishlistId;
    }

    public WishlistData withWishlistId(Long wishlistId) {
        this.wishlistId = wishlistId;
        return this;
    }

}
