package com.cashmyproperty.app.View.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCategoryData {
    @SerializedName("sub_category_name")
    @Expose
    private String subCategoryName;
    @SerializedName("sub_category_slug")
    @Expose
    private String subCategorySlug;
    @SerializedName("sub_category_web_image_alt")
    @Expose
    private String subCategoryWebImageAlt;
    @SerializedName("sub_category_app_image_alt")
    @Expose
    private String subCategoryAppImageAlt;
    @SerializedName("sub_category_id")
    @Expose
    private Long subCategoryId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("sub_category_app_image")
    @Expose
    private String subCategoryAppImage;
    @SerializedName("sub_category_web_image")
    @Expose
    private String subCategoryWebImage;
    @SerializedName("_id")
    @Expose
    private Id__1 id;

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public SubCategoryData withSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
        return this;
    }

    public String getSubCategorySlug() {
        return subCategorySlug;
    }

    public void setSubCategorySlug(String subCategorySlug) {
        this.subCategorySlug = subCategorySlug;
    }

    public SubCategoryData withSubCategorySlug(String subCategorySlug) {
        this.subCategorySlug = subCategorySlug;
        return this;
    }

    public String getSubCategoryWebImageAlt() {
        return subCategoryWebImageAlt;
    }

    public void setSubCategoryWebImageAlt(String subCategoryWebImageAlt) {
        this.subCategoryWebImageAlt = subCategoryWebImageAlt;
    }

    public SubCategoryData withSubCategoryWebImageAlt(String subCategoryWebImageAlt) {
        this.subCategoryWebImageAlt = subCategoryWebImageAlt;
        return this;
    }

    public String getSubCategoryAppImageAlt() {
        return subCategoryAppImageAlt;
    }

    public void setSubCategoryAppImageAlt(String subCategoryAppImageAlt) {
        this.subCategoryAppImageAlt = subCategoryAppImageAlt;
    }

    public SubCategoryData withSubCategoryAppImageAlt(String subCategoryAppImageAlt) {
        this.subCategoryAppImageAlt = subCategoryAppImageAlt;
        return this;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public SubCategoryData withSubCategoryId(Long subCategoryId) {
        this.subCategoryId = subCategoryId;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SubCategoryData withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getSubCategoryAppImage() {
        return subCategoryAppImage;
    }

    public void setSubCategoryAppImage(String subCategoryAppImage) {
        this.subCategoryAppImage = subCategoryAppImage;
    }

    public SubCategoryData withSubCategoryAppImage(String subCategoryAppImage) {
        this.subCategoryAppImage = subCategoryAppImage;
        return this;
    }

    public String getSubCategoryWebImage() {
        return subCategoryWebImage;
    }

    public void setSubCategoryWebImage(String subCategoryWebImage) {
        this.subCategoryWebImage = subCategoryWebImage;
    }

    public SubCategoryData withSubCategoryWebImage(String subCategoryWebImage) {
        this.subCategoryWebImage = subCategoryWebImage;
        return this;
    }

    public Id__1 getId() {
        return id;
    }

    public void setId(Id__1 id) {
        this.id = id;
    }

    public SubCategoryData withId(Id__1 id) {
        this.id = id;
        return this;
    }

}
