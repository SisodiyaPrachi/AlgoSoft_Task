package com.cashmyproperty.app.View.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SignUpResult {

    @SerializedName("usersData")
    @Expose
    private SellerData usersData;

    public SellerData getUsersData() {
        return usersData;
    }

    public void setUsersData(SellerData usersData) {
        this.usersData = usersData;
    }

    public SignUpResult withUsersData(SellerData usersData) {
        this.usersData = usersData;
        return this;
    }

    private List<KindOfProperty> kindOfProperty = null;

    public List<KindOfProperty> getKindOfProperty() {
        return kindOfProperty;
    }

    public void setKindOfProperty(List<KindOfProperty> kindOfProperty) {
        this.kindOfProperty = kindOfProperty;
    }

    public SignUpResult withKindOfProperty(List<KindOfProperty> kindOfProperty) {
        this.kindOfProperty = kindOfProperty;
        return this;
    }

    @SerializedName("propertyData")
    @Expose
    private PropertyData propertyData;

    public PropertyData getPropertyData() {
        return propertyData;
    }

    public void setPropertyData(PropertyData propertyData) {
        this.propertyData = propertyData;
    }

    public SignUpResult withPropertyData(PropertyData propertyData) {
        this.propertyData = propertyData;
        return this;
    }

    @SerializedName("locationData")
    @Expose
    private List<LocationDatum> locationData = null;
    @SerializedName("propertyHolder")
    @Expose
    private List<PropertyHolder> propertyHolder = null;
    @SerializedName("areaMeasurement")
    @Expose
    private List<AreaMeasurement> areaMeasurement = null;

    public List<LocationDatum> getLocationData() {
        return locationData;
    }

    public void setLocationData(List<LocationDatum> locationData) {
        this.locationData = locationData;
    }

    public SignUpResult withLocationData(List<LocationDatum> locationData) {
        this.locationData = locationData;
        return this;
    }

    public List<PropertyHolder> getPropertyHolder() {
        return propertyHolder;
    }

    public void setPropertyHolder(List<PropertyHolder> propertyHolder) {
        this.propertyHolder = propertyHolder;
    }

    public SignUpResult withPropertyHolder(List<PropertyHolder> propertyHolder) {
        this.propertyHolder = propertyHolder;
        return this;
    }

    public List<AreaMeasurement> getAreaMeasurement() {
        return areaMeasurement;
    }

    public void setAreaMeasurement(List<AreaMeasurement> areaMeasurement) {
        this.areaMeasurement = areaMeasurement;
    }

    public SignUpResult withAreaMeasurement(List<AreaMeasurement> areaMeasurement) {
        this.areaMeasurement = areaMeasurement;
        return this;
    }

    @SerializedName("firstPropertyData")
    @Expose
    private FirstPropertyData firstPropertyData;
    @SerializedName("restPropertyData")
    @Expose
    private List<RestPropertyDatum> restPropertyData = null;

    public FirstPropertyData getFirstPropertyData() {
        return firstPropertyData;
    }

    public void setFirstPropertyData(FirstPropertyData firstPropertyData) {
        this.firstPropertyData = firstPropertyData;
    }

    public SignUpResult withFirstPropertyData(FirstPropertyData firstPropertyData) {
        this.firstPropertyData = firstPropertyData;
        return this;
    }

    public List<RestPropertyDatum> getRestPropertyData() {
        return restPropertyData;
    }

    public void setRestPropertyData(List<RestPropertyDatum> restPropertyData) {
        this.restPropertyData = restPropertyData;
    }

    public SignUpResult withRestPropertyData(List<RestPropertyDatum> restPropertyData) {
        this.restPropertyData = restPropertyData;
        return this;

    }

    @SerializedName("propertyDetailsData")
    @Expose
    private PropertyDetailsData propertyDetailsData;
    @SerializedName("startAmount")
    @Expose
    private String startAmount;
    @SerializedName("lastBidAmount")
    @Expose
    private String lastBidAmount;

    public PropertyDetailsData getPropertyDetailsData() {
        return propertyDetailsData;
    }

    public void setPropertyDetailsData(PropertyDetailsData propertyDetailsData) {
        this.propertyDetailsData = propertyDetailsData;
    }

    public SignUpResult withPropertyDetailsData(PropertyDetailsData propertyDetailsData) {
        this.propertyDetailsData = propertyDetailsData;
        return this;
    }




    @SerializedName("categoryData")
    @Expose
    private List<CategoryDatum> categoryData = null;

    public List<CategoryDatum> getCategoryData() {
        return categoryData;
    }

    public void setCategoryData(List<CategoryDatum> categoryData) {
        this.categoryData = categoryData;
    }

    public SignUpResult withCategoryData(List<CategoryDatum> categoryData) {
        this.categoryData = categoryData;
        return this;
    }

    @SerializedName("termsConditionData")
    @Expose
    private TermsConditionData termsConditionData;

    public TermsConditionData getTermsConditionData() {
        return termsConditionData;
    }

    public void setTermsConditionData(TermsConditionData termsConditionData) {
        this.termsConditionData = termsConditionData;
    }

    public SignUpResult withTermsConditionData(TermsConditionData termsConditionData) {
        this.termsConditionData = termsConditionData;
        return this;
    }

}
