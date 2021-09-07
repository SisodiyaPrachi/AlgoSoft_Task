package com.cashmyproperty.app.View.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryDatum {

    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("category_slug")
    @Expose
    private String categorySlug;
    @SerializedName("category_web_image_alt")
    @Expose
    private String categoryWebImageAlt;
    @SerializedName("category_app_image_alt")
    @Expose
    private String categoryAppImageAlt;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("category_app_image")
    @Expose
    private String categoryAppImage;
    @SerializedName("category_web_image")
    @Expose
    private String categoryWebImage;
    @SerializedName("_id")
    @Expose
    private Id id;
    @SerializedName("property_count")
    @Expose
    private Integer propertyCount;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryDatum withCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public String getCategorySlug() {
        return categorySlug;
    }

    public void setCategorySlug(String categorySlug) {
        this.categorySlug = categorySlug;
    }

    public CategoryDatum withCategorySlug(String categorySlug) {
        this.categorySlug = categorySlug;
        return this;
    }

    public String getCategoryWebImageAlt() {
        return categoryWebImageAlt;
    }

    public void setCategoryWebImageAlt(String categoryWebImageAlt) {
        this.categoryWebImageAlt = categoryWebImageAlt;
    }

    public CategoryDatum withCategoryWebImageAlt(String categoryWebImageAlt) {
        this.categoryWebImageAlt = categoryWebImageAlt;
        return this;
    }

    public String getCategoryAppImageAlt() {
        return categoryAppImageAlt;
    }

    public void setCategoryAppImageAlt(String categoryAppImageAlt) {
        this.categoryAppImageAlt = categoryAppImageAlt;
    }

    public CategoryDatum withCategoryAppImageAlt(String categoryAppImageAlt) {
        this.categoryAppImageAlt = categoryAppImageAlt;
        return this;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public CategoryDatum withCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CategoryDatum withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getCategoryAppImage() {
        return categoryAppImage;
    }

    public void setCategoryAppImage(String categoryAppImage) {
        this.categoryAppImage = categoryAppImage;
    }

    public CategoryDatum withCategoryAppImage(String categoryAppImage) {
        this.categoryAppImage = categoryAppImage;
        return this;
    }

    public String getCategoryWebImage() {
        return categoryWebImage;
    }

    public void setCategoryWebImage(String categoryWebImage) {
        this.categoryWebImage = categoryWebImage;
    }

    public CategoryDatum withCategoryWebImage(String categoryWebImage) {
        this.categoryWebImage = categoryWebImage;
        return this;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public CategoryDatum withId(Id id) {
        this.id = id;
        return this;
    }

    public Integer getPropertyCount() {
        return propertyCount;
    }

    public void setPropertyCount(Integer propertyCount) {
        this.propertyCount = propertyCount;
    }

    public CategoryDatum withPropertyCount(Integer propertyCount) {
        this.propertyCount = propertyCount;
        return this;
    }

}

