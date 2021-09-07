package com.cashmyproperty.app.View.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryData {

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
    private Long categoryId;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryData withCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public String getCategorySlug() {
        return categorySlug;
    }

    public void setCategorySlug(String categorySlug) {
        this.categorySlug = categorySlug;
    }

    public CategoryData withCategorySlug(String categorySlug) {
        this.categorySlug = categorySlug;
        return this;
    }

    public String getCategoryWebImageAlt() {
        return categoryWebImageAlt;
    }

    public void setCategoryWebImageAlt(String categoryWebImageAlt) {
        this.categoryWebImageAlt = categoryWebImageAlt;
    }

    public CategoryData withCategoryWebImageAlt(String categoryWebImageAlt) {
        this.categoryWebImageAlt = categoryWebImageAlt;
        return this;
    }

    public String getCategoryAppImageAlt() {
        return categoryAppImageAlt;
    }

    public void setCategoryAppImageAlt(String categoryAppImageAlt) {
        this.categoryAppImageAlt = categoryAppImageAlt;
    }

    public CategoryData withCategoryAppImageAlt(String categoryAppImageAlt) {
        this.categoryAppImageAlt = categoryAppImageAlt;
        return this;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public CategoryData withCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CategoryData withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getCategoryAppImage() {
        return categoryAppImage;
    }

    public void setCategoryAppImage(String categoryAppImage) {
        this.categoryAppImage = categoryAppImage;
    }

    public CategoryData withCategoryAppImage(String categoryAppImage) {
        this.categoryAppImage = categoryAppImage;
        return this;
    }

    public String getCategoryWebImage() {
        return categoryWebImage;
    }

    public void setCategoryWebImage(String categoryWebImage) {
        this.categoryWebImage = categoryWebImage;
    }

    public CategoryData withCategoryWebImage(String categoryWebImage) {
        this.categoryWebImage = categoryWebImage;
        return this;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public CategoryData withId(Id id) {
        this.id = id;
        return this;
    }

}
