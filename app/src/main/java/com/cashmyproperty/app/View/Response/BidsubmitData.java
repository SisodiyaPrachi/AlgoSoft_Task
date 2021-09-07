package com.cashmyproperty.app.View.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BidsubmitData {

    @SerializedName("property_bid_id")
    @Expose
    private Long propertyBidId;
    @SerializedName("property_id")
    @Expose
    private Long propertyId;
    @SerializedName("buyer_id")
    @Expose
    private Long buyerId;
    @SerializedName("expected_price")
    @Expose
    private Integer expectedPrice;
    @SerializedName("last_bid_amount")
    @Expose
    private Integer lastBidAmount;
    @SerializedName("current_bid_amount")
    @Expose
    private Integer currentBidAmount;
    @SerializedName("current_bid_datetime")
    @Expose
    private Integer currentBidDatetime;
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

    public Long getPropertyBidId() {
        return propertyBidId;
    }

    public void setPropertyBidId(Long propertyBidId) {
        this.propertyBidId = propertyBidId;
    }

    public BidsubmitData withPropertyBidId(Long propertyBidId) {
        this.propertyBidId = propertyBidId;
        return this;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public BidsubmitData withPropertyId(Long propertyId) {
        this.propertyId = propertyId;
        return this;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public BidsubmitData withBuyerId(Long buyerId) {
        this.buyerId = buyerId;
        return this;
    }

    public Integer getExpectedPrice() {
        return expectedPrice;
    }

    public void setExpectedPrice(Integer expectedPrice) {
        this.expectedPrice = expectedPrice;
    }

    public BidsubmitData withExpectedPrice(Integer expectedPrice) {
        this.expectedPrice = expectedPrice;
        return this;
    }

    public Integer getLastBidAmount() {
        return lastBidAmount;
    }

    public void setLastBidAmount(Integer lastBidAmount) {
        this.lastBidAmount = lastBidAmount;
    }

    public BidsubmitData withLastBidAmount(Integer lastBidAmount) {
        this.lastBidAmount = lastBidAmount;
        return this;
    }

    public Integer getCurrentBidAmount() {
        return currentBidAmount;
    }

    public void setCurrentBidAmount(Integer currentBidAmount) {
        this.currentBidAmount = currentBidAmount;
    }

    public BidsubmitData withCurrentBidAmount(Integer currentBidAmount) {
        this.currentBidAmount = currentBidAmount;
        return this;
    }

    public Integer getCurrentBidDatetime() {
        return currentBidDatetime;
    }

    public void setCurrentBidDatetime(Integer currentBidDatetime) {
        this.currentBidDatetime = currentBidDatetime;
    }

    public BidsubmitData withCurrentBidDatetime(Integer currentBidDatetime) {
        this.currentBidDatetime = currentBidDatetime;
        return this;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public BidsubmitData withDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public Integer getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Integer creationDate) {
        this.creationDate = creationDate;
    }

    public BidsubmitData withCreationDate(Integer creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getCreationLat() {
        return creationLat;
    }

    public void setCreationLat(String creationLat) {
        this.creationLat = creationLat;
    }

    public BidsubmitData withCreationLat(String creationLat) {
        this.creationLat = creationLat;
        return this;
    }

    public String getCreationLong() {
        return creationLong;
    }

    public void setCreationLong(String creationLong) {
        this.creationLong = creationLong;
    }

    public BidsubmitData withCreationLong(String creationLong) {
        this.creationLong = creationLong;
        return this;
    }

}

