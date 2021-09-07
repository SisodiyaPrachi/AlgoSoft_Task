package com.cashmyproperty.app.View.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class KindOfProperty {

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
    @SerializedName("_id")
    @Expose
    private Id id;
    @SerializedName("propertyType")
    @Expose
    private ArrayList<PropertyType> propertyType = null;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public KindOfProperty withCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public String getCategorySlug() {
        return categorySlug;
    }

    public void setCategorySlug(String categorySlug) {
        this.categorySlug = categorySlug;
    }

    public KindOfProperty withCategorySlug(String categorySlug) {
        this.categorySlug = categorySlug;
        return this;
    }

    public String getCategoryWebImageAlt() {
        return categoryWebImageAlt;
    }

    public void setCategoryWebImageAlt(String categoryWebImageAlt) {
        this.categoryWebImageAlt = categoryWebImageAlt;
    }

    public KindOfProperty withCategoryWebImageAlt(String categoryWebImageAlt) {
        this.categoryWebImageAlt = categoryWebImageAlt;
        return this;
    }

    public String getCategoryAppImageAlt() {
        return categoryAppImageAlt;
    }

    public void setCategoryAppImageAlt(String categoryAppImageAlt) {
        this.categoryAppImageAlt = categoryAppImageAlt;
    }

    public KindOfProperty withCategoryAppImageAlt(String categoryAppImageAlt) {
        this.categoryAppImageAlt = categoryAppImageAlt;
        return this;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public KindOfProperty withCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public KindOfProperty withStatus(String status) {
        this.status = status;
        return this;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public KindOfProperty withId(Id id) {
        this.id = id;
        return this;
    }

    public ArrayList<PropertyType> getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(ArrayList<PropertyType> propertyType) {
        this.propertyType = propertyType;
    }

    public KindOfProperty withPropertyType(ArrayList<PropertyType> propertyType) {
        this.propertyType = propertyType;
        return this;
    }

    /*public class PropertyType {

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
        @SerializedName("_id")
        @Expose
        private Id__1 id;

        public String getSubCategoryName() {
            return subCategoryName;
        }

        public void setSubCategoryName(String subCategoryName) {
            this.subCategoryName = subCategoryName;
        }

        public PropertyType withSubCategoryName(String subCategoryName) {
            this.subCategoryName = subCategoryName;
            return this;
        }

        public String getSubCategorySlug() {
            return subCategorySlug;
        }

        public void setSubCategorySlug(String subCategorySlug) {
            this.subCategorySlug = subCategorySlug;
        }

        public PropertyType withSubCategorySlug(String subCategorySlug) {
            this.subCategorySlug = subCategorySlug;
            return this;
        }

        public String getSubCategoryWebImageAlt() {
            return subCategoryWebImageAlt;
        }

        public void setSubCategoryWebImageAlt(String subCategoryWebImageAlt) {
            this.subCategoryWebImageAlt = subCategoryWebImageAlt;
        }

        public PropertyType withSubCategoryWebImageAlt(String subCategoryWebImageAlt) {
            this.subCategoryWebImageAlt = subCategoryWebImageAlt;
            return this;
        }

        public String getSubCategoryAppImageAlt() {
            return subCategoryAppImageAlt;
        }

        public void setSubCategoryAppImageAlt(String subCategoryAppImageAlt) {
            this.subCategoryAppImageAlt = subCategoryAppImageAlt;
        }

        public PropertyType withSubCategoryAppImageAlt(String subCategoryAppImageAlt) {
            this.subCategoryAppImageAlt = subCategoryAppImageAlt;
            return this;
        }

        public Long getSubCategoryId() {
            return subCategoryId;
        }

        public void setSubCategoryId(Long subCategoryId) {
            this.subCategoryId = subCategoryId;
        }

        public PropertyType withSubCategoryId(Long subCategoryId) {
            this.subCategoryId = subCategoryId;
            return this;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public PropertyType withStatus(String status) {
            this.status = status;
            return this;
        }

        public Id__1 getId() {
            return id;
        }

        public void setId(Id__1 id) {
            this.id = id;
        }

        public PropertyType withId(Id__1 id) {
            this.id = id;
            return this;
        }

    }*/
}
