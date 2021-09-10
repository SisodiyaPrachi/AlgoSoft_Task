package com.cashmyproperty.app.View.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RestPropertyDatum {
    @SerializedName("property_id")
    @Expose
    private Long propertyId;
    @SerializedName("seller_id")
    @Expose
    private Long sellerId;
    @SerializedName("property_sequence_id")
    @Expose
    private String propertySequenceId;
    @SerializedName("kind_of_property")
    @Expose
    private Long kindOfProperty;
    @SerializedName("property_type")
    @Expose
    private Long propertyType;
    @SerializedName("complete_property_step")
    @Expose
    private Integer completePropertyStep;
    @SerializedName("property_assigned")
    @Expose
    private String propertyAssigned;
    @SerializedName("basic_details_verify")
    @Expose
    private String basicDetailsVerify;
    @SerializedName("property_details_verify")
    @Expose
    private String propertyDetailsVerify;
    @SerializedName("property_image_verify")
    @Expose
    private String propertyImageVerify;
    @SerializedName("property_verified")
    @Expose
    private String propertyVerified;
    @SerializedName("creation_date")
    @Expose
    private Integer creationDate;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("area_type")
    @Expose
    private String areaType;
    @SerializedName("current_location")
    @Expose
    private String currentLocation;
    @SerializedName("eid_or_passport")
    @Expose
    private String eidOrPassport;
    @SerializedName("location")
    @Expose
    private Long location;
    @SerializedName("no_of_bed")
    @Expose
    private Integer noOfBed;
    @SerializedName("property_holder")
    @Expose
    private Long propertyHolder;
    @SerializedName("property_name")
    @Expose
    private String propertyName;
    @SerializedName("title_deed")
    @Expose
    private String titleDeed;
    @SerializedName("total_area")
    @Expose
    private Integer totalArea;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("property_noc")
    @Expose
    private String propertyNoc;
    @SerializedName("auction_start_bid_amount")
    @Expose
    private Integer auctionStartBidAmount;
    @SerializedName("dewa_no")
    @Expose
    private String dewaNo;
    @SerializedName("expected_price")
    @Expose
    private String expectedPrice;
    @SerializedName("gas_no")
    @Expose
    private String gasNo;
    @SerializedName("auction_end_date")
    @Expose
    private Integer auctionEndDate;
    @SerializedName("auction_start_date")
    @Expose
    private Integer auctionStartDate;
    @SerializedName("property_verified_date")
    @Expose
    private Integer propertyVerifiedDate;
    @SerializedName("current_bid_amount")
    @Expose
    private Integer currentBidAmount;
    @SerializedName("current_bid_seller_id")
    @Expose
    private Long currentBidSellerId;
    @SerializedName("last_bid_amount")
    @Expose
    private Integer lastBidAmount;
    @SerializedName("last_bid_seller_id")
    @Expose
    private Long lastBidSellerId;
    @SerializedName("kind_of_property_id")
    @Expose
    private Long kindOfPropertyId;
    @SerializedName("kind_of_property_name")
    @Expose
    private String kindOfPropertyName;
    @SerializedName("users_sequence_id")
    @Expose
    private String usersSequenceId;
    @SerializedName("property_type_id")
    @Expose
    private Long propertyTypeId;
    @SerializedName("property_type_name")
    @Expose
    private String propertyTypeName;
    @SerializedName("location_id")
    @Expose
    private Long locationId;
    @SerializedName("location_name")
    @Expose
    private String locationName;
    @SerializedName("property_holder_id")
    @Expose
    private Long propertyHolderId;
    @SerializedName("property_holder_name")
    @Expose
    private String propertyHolderName;
    @SerializedName("images")
    @Expose
    private List<Image__1> images = null;
    @SerializedName("lastBid")
    @Expose
    private Integer lastBid;
    @SerializedName("exact_date")
    @Expose
    private String exactDate;

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getPropertySequenceId() {
        return propertySequenceId;
    }

    public void setPropertySequenceId(String propertySequenceId) {
        this.propertySequenceId = propertySequenceId;
    }

    public Long getKindOfProperty() {
        return kindOfProperty;
    }

    public void setKindOfProperty(Long kindOfProperty) {
        this.kindOfProperty = kindOfProperty;
    }

    public Long getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(Long propertyType) {
        this.propertyType = propertyType;
    }

    public Integer getCompletePropertyStep() {
        return completePropertyStep;
    }

    public void setCompletePropertyStep(Integer completePropertyStep) {
        this.completePropertyStep = completePropertyStep;
    }

    public String getPropertyAssigned() {
        return propertyAssigned;
    }

    public void setPropertyAssigned(String propertyAssigned) {
        this.propertyAssigned = propertyAssigned;
    }

    public String getBasicDetailsVerify() {
        return basicDetailsVerify;
    }

    public void setBasicDetailsVerify(String basicDetailsVerify) {
        this.basicDetailsVerify = basicDetailsVerify;
    }

    public String getPropertyDetailsVerify() {
        return propertyDetailsVerify;
    }

    public void setPropertyDetailsVerify(String propertyDetailsVerify) {
        this.propertyDetailsVerify = propertyDetailsVerify;
    }

    public String getPropertyImageVerify() {
        return propertyImageVerify;
    }

    public void setPropertyImageVerify(String propertyImageVerify) {
        this.propertyImageVerify = propertyImageVerify;
    }

    public String getPropertyVerified() {
        return propertyVerified;
    }

    public void setPropertyVerified(String propertyVerified) {
        this.propertyVerified = propertyVerified;
    }

    public Integer getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Integer creationDate) {
        this.creationDate = creationDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getEidOrPassport() {
        return eidOrPassport;
    }

    public void setEidOrPassport(String eidOrPassport) {
        this.eidOrPassport = eidOrPassport;
    }

    public Long getLocation() {
        return location;
    }

    public void setLocation(Long location) {
        this.location = location;
    }

    public Integer getNoOfBed() {
        return noOfBed;
    }

    public void setNoOfBed(Integer noOfBed) {
        this.noOfBed = noOfBed;
    }

    public Long getPropertyHolder() {
        return propertyHolder;
    }

    public void setPropertyHolder(Long propertyHolder) {
        this.propertyHolder = propertyHolder;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getTitleDeed() {
        return titleDeed;
    }

    public void setTitleDeed(String titleDeed) {
        this.titleDeed = titleDeed;
    }

    public Integer getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(Integer totalArea) {
        this.totalArea = totalArea;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPropertyNoc() {
        return propertyNoc;
    }

    public void setPropertyNoc(String propertyNoc) {
        this.propertyNoc = propertyNoc;
    }

    public Integer getAuctionStartBidAmount() {
        return auctionStartBidAmount;
    }

    public void setAuctionStartBidAmount(Integer auctionStartBidAmount) {
        this.auctionStartBidAmount = auctionStartBidAmount;
    }

    public String getDewaNo() {
        return dewaNo;
    }

    public void setDewaNo(String dewaNo) {
        this.dewaNo = dewaNo;
    }

    public String getExpectedPrice() {
        return expectedPrice;
    }

    public void setExpectedPrice(String expectedPrice) {
        this.expectedPrice = expectedPrice;
    }

    public String getGasNo() {
        return gasNo;
    }

    public void setGasNo(String gasNo) {
        this.gasNo = gasNo;
    }

    public Integer getAuctionEndDate() {
        return auctionEndDate;
    }

    public void setAuctionEndDate(Integer auctionEndDate) {
        this.auctionEndDate = auctionEndDate;
    }

    public Integer getAuctionStartDate() {
        return auctionStartDate;
    }

    public void setAuctionStartDate(Integer auctionStartDate) {
        this.auctionStartDate = auctionStartDate;
    }

    public Integer getPropertyVerifiedDate() {
        return propertyVerifiedDate;
    }

    public void setPropertyVerifiedDate(Integer propertyVerifiedDate) {
        this.propertyVerifiedDate = propertyVerifiedDate;
    }

    public Integer getCurrentBidAmount() {
        return currentBidAmount;
    }

    public void setCurrentBidAmount(Integer currentBidAmount) {
        this.currentBidAmount = currentBidAmount;
    }

    public Long getCurrentBidSellerId() {
        return currentBidSellerId;
    }

    public void setCurrentBidSellerId(Long currentBidSellerId) {
        this.currentBidSellerId = currentBidSellerId;
    }

    public Integer getLastBidAmount() {
        return lastBidAmount;
    }

    public void setLastBidAmount(Integer lastBidAmount) {
        this.lastBidAmount = lastBidAmount;
    }

    public Long getLastBidSellerId() {
        return lastBidSellerId;
    }

    public void setLastBidSellerId(Long lastBidSellerId) {
        this.lastBidSellerId = lastBidSellerId;
    }

    public Long getKindOfPropertyId() {
        return kindOfPropertyId;
    }

    public void setKindOfPropertyId(Long kindOfPropertyId) {
        this.kindOfPropertyId = kindOfPropertyId;
    }

    public String getKindOfPropertyName() {
        return kindOfPropertyName;
    }

    public void setKindOfPropertyName(String kindOfPropertyName) {
        this.kindOfPropertyName = kindOfPropertyName;
    }

    public String getUsersSequenceId() {
        return usersSequenceId;
    }

    public void setUsersSequenceId(String usersSequenceId) {
        this.usersSequenceId = usersSequenceId;
    }

    public Long getPropertyTypeId() {
        return propertyTypeId;
    }

    public void setPropertyTypeId(Long propertyTypeId) {
        this.propertyTypeId = propertyTypeId;
    }

    public String getPropertyTypeName() {
        return propertyTypeName;
    }

    public void setPropertyTypeName(String propertyTypeName) {
        this.propertyTypeName = propertyTypeName;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Long getPropertyHolderId() {
        return propertyHolderId;
    }

    public void setPropertyHolderId(Long propertyHolderId) {
        this.propertyHolderId = propertyHolderId;
    }

    public String getPropertyHolderName() {
        return propertyHolderName;
    }

    public void setPropertyHolderName(String propertyHolderName) {
        this.propertyHolderName = propertyHolderName;
    }

    public List<Image__1> getImages() {
        return images;
    }

    public void setImages(List<Image__1> images) {
        this.images = images;
    }

    public Integer getLastBid() {
        return lastBid;
    }

    public void setLastBid(Integer lastBid) {
        this.lastBid = lastBid;
    }

    public String getExactDate() {
        return exactDate;
    }

    public void setExactDate(String exactDate) {
        this.exactDate = exactDate;
    }

}
