package com.cashmyproperty.app.View.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BidPropertyDatum {

    @SerializedName("property_id")
    @Expose
    private Long propertyId;
    @SerializedName("seller_id")
    @Expose
    private Long sellerId;
    @SerializedName("property_sequence_id")
    @Expose
    private String propertySequenceId;
    @SerializedName("property_name")
    @Expose
    private String propertyName;
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
    private List<Image> images = null;
    @SerializedName("startAmount")
    @Expose
    private String startAmount;
    @SerializedName("currentBiding")
    @Expose
    private String currentBiding;

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public BidPropertyDatum withPropertyId(Long propertyId) {
        this.propertyId = propertyId;
        return this;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public BidPropertyDatum withSellerId(Long sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    public String getPropertySequenceId() {
        return propertySequenceId;
    }

    public void setPropertySequenceId(String propertySequenceId) {
        this.propertySequenceId = propertySequenceId;
    }

    public BidPropertyDatum withPropertySequenceId(String propertySequenceId) {
        this.propertySequenceId = propertySequenceId;
        return this;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public BidPropertyDatum withPropertyName(String propertyName) {
        this.propertyName = propertyName;
        return this;
    }

    public Long getKindOfProperty() {
        return kindOfProperty;
    }

    public void setKindOfProperty(Long kindOfProperty) {
        this.kindOfProperty = kindOfProperty;
    }

    public BidPropertyDatum withKindOfProperty(Long kindOfProperty) {
        this.kindOfProperty = kindOfProperty;
        return this;
    }

    public Long getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(Long propertyType) {
        this.propertyType = propertyType;
    }

    public BidPropertyDatum withPropertyType(Long propertyType) {
        this.propertyType = propertyType;
        return this;
    }

    public Integer getCompletePropertyStep() {
        return completePropertyStep;
    }

    public void setCompletePropertyStep(Integer completePropertyStep) {
        this.completePropertyStep = completePropertyStep;
    }

    public BidPropertyDatum withCompletePropertyStep(Integer completePropertyStep) {
        this.completePropertyStep = completePropertyStep;
        return this;
    }

    public String getPropertyAssigned() {
        return propertyAssigned;
    }

    public void setPropertyAssigned(String propertyAssigned) {
        this.propertyAssigned = propertyAssigned;
    }

    public BidPropertyDatum withPropertyAssigned(String propertyAssigned) {
        this.propertyAssigned = propertyAssigned;
        return this;
    }

    public String getBasicDetailsVerify() {
        return basicDetailsVerify;
    }

    public void setBasicDetailsVerify(String basicDetailsVerify) {
        this.basicDetailsVerify = basicDetailsVerify;
    }

    public BidPropertyDatum withBasicDetailsVerify(String basicDetailsVerify) {
        this.basicDetailsVerify = basicDetailsVerify;
        return this;
    }

    public String getPropertyDetailsVerify() {
        return propertyDetailsVerify;
    }

    public void setPropertyDetailsVerify(String propertyDetailsVerify) {
        this.propertyDetailsVerify = propertyDetailsVerify;
    }

    public BidPropertyDatum withPropertyDetailsVerify(String propertyDetailsVerify) {
        this.propertyDetailsVerify = propertyDetailsVerify;
        return this;
    }

    public String getPropertyImageVerify() {
        return propertyImageVerify;
    }

    public void setPropertyImageVerify(String propertyImageVerify) {
        this.propertyImageVerify = propertyImageVerify;
    }

    public BidPropertyDatum withPropertyImageVerify(String propertyImageVerify) {
        this.propertyImageVerify = propertyImageVerify;
        return this;
    }

    public String getPropertyVerified() {
        return propertyVerified;
    }

    public void setPropertyVerified(String propertyVerified) {
        this.propertyVerified = propertyVerified;
    }

    public BidPropertyDatum withPropertyVerified(String propertyVerified) {
        this.propertyVerified = propertyVerified;
        return this;
    }

    public Integer getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Integer creationDate) {
        this.creationDate = creationDate;
    }

    public BidPropertyDatum withCreationDate(Integer creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BidPropertyDatum withAddress(String address) {
        this.address = address;
        return this;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public BidPropertyDatum withAreaType(String areaType) {
        this.areaType = areaType;
        return this;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public BidPropertyDatum withCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
        return this;
    }

    public String getEidOrPassport() {
        return eidOrPassport;
    }

    public void setEidOrPassport(String eidOrPassport) {
        this.eidOrPassport = eidOrPassport;
    }

    public BidPropertyDatum withEidOrPassport(String eidOrPassport) {
        this.eidOrPassport = eidOrPassport;
        return this;
    }

    public Long getLocation() {
        return location;
    }

    public void setLocation(Long location) {
        this.location = location;
    }

    public BidPropertyDatum withLocation(Long location) {
        this.location = location;
        return this;
    }

    public Integer getNoOfBed() {
        return noOfBed;
    }

    public void setNoOfBed(Integer noOfBed) {
        this.noOfBed = noOfBed;
    }

    public BidPropertyDatum withNoOfBed(Integer noOfBed) {
        this.noOfBed = noOfBed;
        return this;
    }

    public Long getPropertyHolder() {
        return propertyHolder;
    }

    public void setPropertyHolder(Long propertyHolder) {
        this.propertyHolder = propertyHolder;
    }

    public BidPropertyDatum withPropertyHolder(Long propertyHolder) {
        this.propertyHolder = propertyHolder;
        return this;
    }

    public String getTitleDeed() {
        return titleDeed;
    }

    public void setTitleDeed(String titleDeed) {
        this.titleDeed = titleDeed;
    }

    public BidPropertyDatum withTitleDeed(String titleDeed) {
        this.titleDeed = titleDeed;
        return this;
    }

    public Integer getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(Integer totalArea) {
        this.totalArea = totalArea;
    }

    public BidPropertyDatum withTotalArea(Integer totalArea) {
        this.totalArea = totalArea;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BidPropertyDatum withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPropertyNoc() {
        return propertyNoc;
    }

    public void setPropertyNoc(String propertyNoc) {
        this.propertyNoc = propertyNoc;
    }

    public BidPropertyDatum withPropertyNoc(String propertyNoc) {
        this.propertyNoc = propertyNoc;
        return this;
    }

    public Long getKindOfPropertyId() {
        return kindOfPropertyId;
    }

    public void setKindOfPropertyId(Long kindOfPropertyId) {
        this.kindOfPropertyId = kindOfPropertyId;
    }

    public BidPropertyDatum withKindOfPropertyId(Long kindOfPropertyId) {
        this.kindOfPropertyId = kindOfPropertyId;
        return this;
    }

    public String getKindOfPropertyName() {
        return kindOfPropertyName;
    }

    public void setKindOfPropertyName(String kindOfPropertyName) {
        this.kindOfPropertyName = kindOfPropertyName;
    }

    public BidPropertyDatum withKindOfPropertyName(String kindOfPropertyName) {
        this.kindOfPropertyName = kindOfPropertyName;
        return this;
    }

    public Long getPropertyTypeId() {
        return propertyTypeId;
    }

    public void setPropertyTypeId(Long propertyTypeId) {
        this.propertyTypeId = propertyTypeId;
    }

    public BidPropertyDatum withPropertyTypeId(Long propertyTypeId) {
        this.propertyTypeId = propertyTypeId;
        return this;
    }

    public String getPropertyTypeName() {
        return propertyTypeName;
    }

    public void setPropertyTypeName(String propertyTypeName) {
        this.propertyTypeName = propertyTypeName;
    }

    public BidPropertyDatum withPropertyTypeName(String propertyTypeName) {
        this.propertyTypeName = propertyTypeName;
        return this;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public BidPropertyDatum withLocationId(Long locationId) {
        this.locationId = locationId;
        return this;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public BidPropertyDatum withLocationName(String locationName) {
        this.locationName = locationName;
        return this;
    }

    public Long getPropertyHolderId() {
        return propertyHolderId;
    }

    public void setPropertyHolderId(Long propertyHolderId) {
        this.propertyHolderId = propertyHolderId;
    }

    public BidPropertyDatum withPropertyHolderId(Long propertyHolderId) {
        this.propertyHolderId = propertyHolderId;
        return this;
    }

    public String getPropertyHolderName() {
        return propertyHolderName;
    }

    public void setPropertyHolderName(String propertyHolderName) {
        this.propertyHolderName = propertyHolderName;
    }

    public BidPropertyDatum withPropertyHolderName(String propertyHolderName) {
        this.propertyHolderName = propertyHolderName;
        return this;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public BidPropertyDatum withImages(List<Image> images) {
        this.images = images;
        return this;
    }

    public String getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(String startAmount) {
        this.startAmount = startAmount;
    }

    public BidPropertyDatum withStartAmount(String startAmount) {
        this.startAmount = startAmount;
        return this;
    }

    public String getCurrentBiding() {
        return currentBiding;
    }

    public void setCurrentBiding(String currentBiding) {
        this.currentBiding = currentBiding;
    }

    public BidPropertyDatum withCurrentBiding(String currentBiding) {
        this.currentBiding = currentBiding;
        return this;
    }
}
