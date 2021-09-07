package com.cashmyproperty.app.View.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TermsConditionData {
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("heading")
    @Expose
    private String heading;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TermsConditionData withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public TermsConditionData withHeading(String heading) {
        this.heading = heading;
        return this;
    }
}
