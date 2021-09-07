package com.cashmyproperty.app.View.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecommendedProperty {

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
    @SerializedName("kind_of_property_id")
    @Expose
    private Long kindOfPropertyId;
    @SerializedName("kind_of_property_name")
    @Expose
    private String kindOfPropertyName;
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
    private List<Image__2> images = null;
    @SerializedName("lastBidAmount")
    @Expose
    private String lastBidAmount;

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public RecommendedProperty withPropertyId(Long propertyId) {
        this.propertyId = propertyId;
        return this;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public RecommendedProperty withSellerId(Long sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    public String getPropertySequenceId() {
        return propertySequenceId;
    }

    public void setPropertySequenceId(String propertySequenceId) {
        this.propertySequenceId = propertySequenceId;
    }

    public RecommendedProperty withPropertySequenceId(String propertySequenceId) {
        this.propertySequenceId = propertySequenceId;
        return this;
    }

    public Long getKindOfProperty() {
        return kindOfProperty;
    }

    public void setKindOfProperty(Long kindOfProperty) {
        this.kindOfProperty = kindOfProperty;
    }

    public RecommendedProperty withKindOfProperty(Long kindOfProperty) {
        this.kindOfProperty = kindOfProperty;
        return this;
    }

    public Long getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(Long propertyType) {
        this.propertyType = propertyType;
    }

    public RecommendedProperty withPropertyType(Long propertyType) {
        this.propertyType = propertyType;
        return this;
    }

    public Integer getCompletePropertyStep() {
        return completePropertyStep;
    }

    public void setCompletePropertyStep(Integer completePropertyStep) {
        this.completePropertyStep = completePropertyStep;
    }

    public RecommendedProperty withCompletePropertyStep(Integer completePropertyStep) {
        this.completePropertyStep = completePropertyStep;
        return this;
    }

    public String getPropertyAssigned() {
        return propertyAssigned;
    }

    public void setPropertyAssigned(String propertyAssigned) {
        this.propertyAssigned = propertyAssigned;
    }

    public RecommendedProperty withPropertyAssigned(String propertyAssigned) {
        this.propertyAssigned = propertyAssigned;
        return this;
    }

    public String getBasicDetailsVerify() {
        return basicDetailsVerify;
    }

    public void setBasicDetailsVerify(String basicDetailsVerify) {
        this.basicDetailsVerify = basicDetailsVerify;
    }

    public RecommendedProperty withBasicDetailsVerify(String basicDetailsVerify) {
        this.basicDetailsVerify = basicDetailsVerify;
        return this;
    }

    public String getPropertyDetailsVerify() {
        return propertyDetailsVerify;
    }

    public void setPropertyDetailsVerify(String propertyDetailsVerify) {
        this.propertyDetailsVerify = propertyDetailsVerify;
    }

    public RecommendedProperty withPropertyDetailsVerify(String propertyDetailsVerify) {
        this.propertyDetailsVerify = propertyDetailsVerify;
        return this;
    }

    public String getPropertyImageVerify() {
        return propertyImageVerify;
    }

    public void setPropertyImageVerify(String propertyImageVerify) {
        this.propertyImageVerify = propertyImageVerify;
    }

    public RecommendedProperty withPropertyImageVerify(String propertyImageVerify) {
        this.propertyImageVerify = propertyImageVerify;
        return this;
    }

    public String getPropertyVerified() {
        return propertyVerified;
    }

    public void setPropertyVerified(String propertyVerified) {
        this.propertyVerified = propertyVerified;
    }

    public RecommendedProperty withPropertyVerified(String propertyVerified) {
        this.propertyVerified = propertyVerified;
        return this;
    }

    public Integer getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Integer creationDate) {
        this.creationDate = creationDate;
    }

    public RecommendedProperty withCreationDate(Integer creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public RecommendedProperty withAddress(String address) {
        this.address = address;
        return this;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public RecommendedProperty withAreaType(String areaType) {
        this.areaType = areaType;
        return this;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public RecommendedProperty withCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
        return this;
    }

    public String getEidOrPassport() {
        return eidOrPassport;
    }

    public void setEidOrPassport(String eidOrPassport) {
        this.eidOrPassport = eidOrPassport;
    }

    public RecommendedProperty withEidOrPassport(String eidOrPassport) {
        this.eidOrPassport = eidOrPassport;
        return this;
    }

    public Long getLocation() {
        return location;
    }

    public void setLocation(Long location) {
        this.location = location;
    }

    public RecommendedProperty withLocation(Long location) {
        this.location = location;
        return this;
    }

    public Integer getNoOfBed() {
        return noOfBed;
    }

    public void setNoOfBed(Integer noOfBed) {
        this.noOfBed = noOfBed;
    }

    public RecommendedProperty withNoOfBed(Integer noOfBed) {
        this.noOfBed = noOfBed;
        return this;
    }

    public Long getPropertyHolder() {
        return propertyHolder;
    }

    public void setPropertyHolder(Long propertyHolder) {
        this.propertyHolder = propertyHolder;
    }

    public RecommendedProperty withPropertyHolder(Long propertyHolder) {
        this.propertyHolder = propertyHolder;
        return this;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public RecommendedProperty withPropertyName(String propertyName) {
        this.propertyName = propertyName;
        return this;
    }

    public String getTitleDeed() {
        return titleDeed;
    }

    public void setTitleDeed(String titleDeed) {
        this.titleDeed = titleDeed;
    }

    public RecommendedProperty withTitleDeed(String titleDeed) {
        this.titleDeed = titleDeed;
        return this;
    }

    public Integer getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(Integer totalArea) {
        this.totalArea = totalArea;
    }

    public RecommendedProperty withTotalArea(Integer totalArea) {
        this.totalArea = totalArea;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RecommendedProperty withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPropertyNoc() {
        return propertyNoc;
    }

    public void setPropertyNoc(String propertyNoc) {
        this.propertyNoc = propertyNoc;
    }

    public RecommendedProperty withPropertyNoc(String propertyNoc) {
        this.propertyNoc = propertyNoc;
        return this;
    }

    public Long getKindOfPropertyId() {
        return kindOfPropertyId;
    }

    public void setKindOfPropertyId(Long kindOfPropertyId) {
        this.kindOfPropertyId = kindOfPropertyId;
    }

    public RecommendedProperty withKindOfPropertyId(Long kindOfPropertyId) {
        this.kindOfPropertyId = kindOfPropertyId;
        return this;
    }

    public String getKindOfPropertyName() {
        return kindOfPropertyName;
    }

    public void setKindOfPropertyName(String kindOfPropertyName) {
        this.kindOfPropertyName = kindOfPropertyName;
    }

    public RecommendedProperty withKindOfPropertyName(String kindOfPropertyName) {
        this.kindOfPropertyName = kindOfPropertyName;
        return this;
    }

    public Long getPropertyTypeId() {
        return propertyTypeId;
    }

    public void setPropertyTypeId(Long propertyTypeId) {
        this.propertyTypeId = propertyTypeId;
    }

    public RecommendedProperty withPropertyTypeId(Long propertyTypeId) {
        this.propertyTypeId = propertyTypeId;
        return this;
    }

    public String getPropertyTypeName() {
        return propertyTypeName;
    }

    public void setPropertyTypeName(String propertyTypeName) {
        this.propertyTypeName = propertyTypeName;
    }

    public RecommendedProperty withPropertyTypeName(String propertyTypeName) {
        this.propertyTypeName = propertyTypeName;
        return this;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public RecommendedProperty withLocationId(Long locationId) {
        this.locationId = locationId;
        return this;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public RecommendedProperty withLocationName(String locationName) {
        this.locationName = locationName;
        return this;
    }

    public Long getPropertyHolderId() {
        return propertyHolderId;
    }

    public void setPropertyHolderId(Long propertyHolderId) {
        this.propertyHolderId = propertyHolderId;
    }

    public RecommendedProperty withPropertyHolderId(Long propertyHolderId) {
        this.propertyHolderId = propertyHolderId;
        return this;
    }

    public String getPropertyHolderName() {
        return propertyHolderName;
    }

    public void setPropertyHolderName(String propertyHolderName) {
        this.propertyHolderName = propertyHolderName;
    }

    public RecommendedProperty withPropertyHolderName(String propertyHolderName) {
        this.propertyHolderName = propertyHolderName;
        return this;
    }

    public List<Image__2> getImages() {
        return images;
    }

    public void setImages(List<Image__2> images) {
        this.images = images;
    }

    public RecommendedProperty withImages(List<Image__2> images) {
        this.images = images;
        return this;
    }

    public String getLastBidAmount() {
        return lastBidAmount;
    }

    public void setLastBidAmount(String lastBidAmount) {
        this.lastBidAmount = lastBidAmount;
    }

    public RecommendedProperty withLastBidAmount(String lastBidAmount) {
        this.lastBidAmount = lastBidAmount;
        return this;
    }

}
