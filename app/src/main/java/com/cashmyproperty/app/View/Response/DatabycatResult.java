package com.cashmyproperty.app.View.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DatabycatResult {
    @SerializedName("categoryData")
    @Expose
    private CategoryData categoryData;
    @SerializedName("subCategoryData")
    @Expose
    private SubCategoryData subCategoryData;
    @SerializedName("propertyData")
    @Expose
    private List<PropertyDatum> propertyData = null;

    public CategoryData getCategoryData() {
        return categoryData;
    }

    public void setCategoryData(CategoryData categoryData) {
        this.categoryData = categoryData;
    }

    public DatabycatResult withCategoryData(CategoryData categoryData) {
        this.categoryData = categoryData;
        return this;
    }

    public SubCategoryData getSubCategoryData() {
        return subCategoryData;
    }

    public void setSubCategoryData(SubCategoryData subCategoryData) {
        this.subCategoryData = subCategoryData;
    }

    public DatabycatResult withSubCategoryData(SubCategoryData subCategoryData) {
        this.subCategoryData = subCategoryData;
        return this;
    }

    public List<PropertyDatum> getPropertyData() {
        return propertyData;
    }

    public void setPropertyData(List<PropertyDatum> propertyData) {
        this.propertyData = propertyData;
    }

    public DatabycatResult withPropertyData(List<PropertyDatum> propertyData) {
        this.propertyData = propertyData;
        return this;
    }

}
