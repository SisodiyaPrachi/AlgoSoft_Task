package com.cashmyproperty.app.View.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatabycategoryResponse {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private DatabycatResult result;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public DatabycategoryResponse withSuccess(Integer success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DatabycategoryResponse withMessage(String message) {
        this.message = message;
        return this;
    }

    public DatabycatResult getResult() {
        return result;
    }

    public void setResult(DatabycatResult result) {
        this.result = result;
    }

    public DatabycategoryResponse withResult(DatabycatResult result) {
        this.result = result;
        return this;
    }

}
