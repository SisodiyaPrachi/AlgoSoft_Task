package com.cashmyproperty.app.View.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("categoryData")
    @Expose
    private CategoryData categoryData;
    @SerializedName("subCategoryData")
    @Expose
    private List<SubCategoryDatum> subCategoryData = null;

    public CategoryData getCategoryData() {
        return categoryData;
    }

    public void setCategoryData(CategoryData categoryData) {
        this.categoryData = categoryData;
    }

    public Result withCategoryData(CategoryData categoryData) {
        this.categoryData = categoryData;
        return this;
    }

    public List<SubCategoryDatum> getSubCategoryData() {
        return subCategoryData;
    }

    public void setSubCategoryData(List<SubCategoryDatum> subCategoryData) {
        this.subCategoryData = subCategoryData;
    }

    public Result withSubCategoryData(List<SubCategoryDatum> subCategoryData) {
        this.subCategoryData = subCategoryData;
        return this;
    }


    @SerializedName("propertyData")
    @Expose
    private List<PropertyDatum> propertyData = null;


    public List<PropertyDatum> getPropertyData() {
        return propertyData;
    }

    public void setPropertyData(List<PropertyDatum> propertyData) {
        this.propertyData = propertyData;
    }

    public Result withPropertyData(List<PropertyDatum> propertyData) {
        this.propertyData = propertyData;
        return this;
    }

    @SerializedName("hometopProperty")
    @Expose
    private List<HometopProperty> hometopProperty = null;
    @SerializedName("auctionclosingProperty")
    @Expose
    private List<AuctionclosingProperty> auctionclosingProperty = null;
    @SerializedName("recommendedProperty")
    @Expose
    private List<RecommendedProperty> recommendedProperty = null;
    @SerializedName("explorepopularArea")
    @Expose
    private List<ExplorepopularArea> explorepopularArea = null;

    public List<HometopProperty> getHometopProperty() {
        return hometopProperty;
    }

    public void setHometopProperty(List<HometopProperty> hometopProperty) {
        this.hometopProperty = hometopProperty;
    }

    public Result withHometopProperty(List<HometopProperty> hometopProperty) {
        this.hometopProperty = hometopProperty;
        return this;
    }

    public List<AuctionclosingProperty> getAuctionclosingProperty() {
        return auctionclosingProperty;
    }

    public void setAuctionclosingProperty(List<AuctionclosingProperty> auctionclosingProperty) {
        this.auctionclosingProperty = auctionclosingProperty;
    }

    public Result withAuctionclosingProperty(List<AuctionclosingProperty> auctionclosingProperty) {
        this.auctionclosingProperty = auctionclosingProperty;
        return this;
    }

    public List<RecommendedProperty> getRecommendedProperty() {
        return recommendedProperty;
    }

    public void setRecommendedProperty(List<RecommendedProperty> recommendedProperty) {
        this.recommendedProperty = recommendedProperty;
    }

    public Result withRecommendedProperty(List<RecommendedProperty> recommendedProperty) {
        this.recommendedProperty = recommendedProperty;
        return this;
    }

    public List<ExplorepopularArea> getExplorepopularArea() {
        return explorepopularArea;
    }

    public void setExplorepopularArea(List<ExplorepopularArea> explorepopularArea) {
        this.explorepopularArea = explorepopularArea;
    }

    public Result withExplorepopularArea(List<ExplorepopularArea> explorepopularArea) {
        this.explorepopularArea = explorepopularArea;
        return this;
    }

    @SerializedName("soldPropertyData")
    @Expose
    private List<SoldPropertyDatum> soldPropertyData = null;

    public List<SoldPropertyDatum> getSoldPropertyData() {
        return soldPropertyData;
    }

    public void setSoldPropertyData(List<SoldPropertyDatum> soldPropertyData) {
        this.soldPropertyData = soldPropertyData;
    }

    public Result withSoldPropertyData(List<SoldPropertyDatum> soldPropertyData) {
        this.soldPropertyData = soldPropertyData;
        return this;
    }

    @SerializedName("SellerSoldPropertyDetails")
    @Expose
    private SellerSoldPropertyDetails sellerSoldPropertyDetails;

    public SellerSoldPropertyDetails getSellerSoldPropertyDetails() {
        return sellerSoldPropertyDetails;
    }

    public void setSellerSoldPropertyDetails(SellerSoldPropertyDetails sellerSoldPropertyDetails) {
        this.sellerSoldPropertyDetails = sellerSoldPropertyDetails;
    }

    public Result withSellerSoldPropertyDetails(SellerSoldPropertyDetails sellerSoldPropertyDetails) {
        this.sellerSoldPropertyDetails = sellerSoldPropertyDetails;
        return this;
    }

    @SerializedName("bidPropertyData")
    @Expose
    private List<BidPropertyDatum> bidPropertyData = null;

    public List<BidPropertyDatum> getBidPropertyData() {
        return bidPropertyData;
    }

    public void setBidPropertyData(List<BidPropertyDatum> bidPropertyData) {
        this.bidPropertyData = bidPropertyData;
    }

    public Result withBidPropertyData(List<BidPropertyDatum> bidPropertyData) {
        this.bidPropertyData = bidPropertyData;
        return this;
    }


    @SerializedName("BuyerBidPropertyDetails")
    @Expose
    private BuyerBidPropertyDetails buyerBidPropertyDetails;

    public BuyerBidPropertyDetails getBuyerBidPropertyDetails() {
        return buyerBidPropertyDetails;
    }

    public void setBuyerBidPropertyDetails(BuyerBidPropertyDetails buyerBidPropertyDetails) {
        this.buyerBidPropertyDetails = buyerBidPropertyDetails;
    }

    public Result withBuyerBidPropertyDetails(BuyerBidPropertyDetails buyerBidPropertyDetails) {
        this.buyerBidPropertyDetails = buyerBidPropertyDetails;
        return this;
    }

    @SerializedName("MypropertyData")
    @Expose
    private List<MypropertyDatum> mypropertyData = null;

    public List<MypropertyDatum> getMypropertyData() {
        return mypropertyData;
    }

    public void setMypropertyData(List<MypropertyDatum> mypropertyData) {
        this.mypropertyData = mypropertyData;
    }

    public Result withMypropertyData(List<MypropertyDatum> mypropertyData) {
        this.mypropertyData = mypropertyData;
        return this;
    }

    @SerializedName("MypropertyDetailData")
    @Expose
    private MypropertyDetailData mypropertyDetailData;

    public MypropertyDetailData getMypropertyDetailData() {
        return mypropertyDetailData;
    }

    public void setMypropertyDetailData(MypropertyDetailData mypropertyDetailData) {
        this.mypropertyDetailData = mypropertyDetailData;
    }

    public Result withMypropertyDetailData(MypropertyDetailData mypropertyDetailData) {
        this.mypropertyDetailData = mypropertyDetailData;
        return this;
    }


    @SerializedName("purchasePropertyData")
    @Expose
    private List<PurchasePropertyDatum> purchasePropertyData = null;

    public List<PurchasePropertyDatum> getPurchasePropertyData() {
        return purchasePropertyData;
    }

    public void setPurchasePropertyData(List<PurchasePropertyDatum> purchasePropertyData) {
        this.purchasePropertyData = purchasePropertyData;
    }

    public Result withPurchasePropertyData(List<PurchasePropertyDatum> purchasePropertyData) {
        this.purchasePropertyData = purchasePropertyData;
        return this;
    }

    @SerializedName("BuyerPurchasePropertyDetails")
    @Expose
    private BuyerPurchasePropertyDetails buyerPurchasePropertyDetails;

    public BuyerPurchasePropertyDetails getBuyerPurchasePropertyDetails() {
        return buyerPurchasePropertyDetails;
    }

    public void setBuyerPurchasePropertyDetails(BuyerPurchasePropertyDetails buyerPurchasePropertyDetails) {
        this.buyerPurchasePropertyDetails = buyerPurchasePropertyDetails;
    }

    public Result withBuyerPurchasePropertyDetails(BuyerPurchasePropertyDetails buyerPurchasePropertyDetails) {
        this.buyerPurchasePropertyDetails = buyerPurchasePropertyDetails;
        return this;
    }

    @SerializedName("BidsubmitData")
    @Expose
    private BidsubmitData bidsubmitData;

    public BidsubmitData getBidsubmitData() {
        return bidsubmitData;
    }

    public void setBidsubmitData(BidsubmitData bidsubmitData) {
        this.bidsubmitData = bidsubmitData;
    }

    public Result withBidsubmitData(BidsubmitData bidsubmitData) {
        this.bidsubmitData = bidsubmitData;
        return this;
    }

    @SerializedName("wishlistData")
    @Expose
    private WishlistData wishlistData;

    public WishlistData getWishlistData() {
        return wishlistData;
    }

    public void setWishlistData(WishlistData wishlistData) {
        this.wishlistData = wishlistData;
    }

    public Result withWishlistData(WishlistData wishlistData) {
        this.wishlistData = wishlistData;
        return this;
    }

    @SerializedName("propertywishlistData")
    @Expose
    private List<PropertywishlistDatum> propertywishlistData = null;

    public List<PropertywishlistDatum> getPropertywishlistData() {
        return propertywishlistData;
    }

    public void setPropertywishlistData(List<PropertywishlistDatum> propertywishlistData) {
        this.propertywishlistData = propertywishlistData;
    }

    public Result withPropertywishlistData(List<PropertywishlistDatum> propertywishlistData) {
        this.propertywishlistData = propertywishlistData;
        return this;
    }

}

