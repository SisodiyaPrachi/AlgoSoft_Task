package com.cashmyproperty.app.View.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpResponse {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private SignUpResult result;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public SignUpResponse withSuccess(Integer success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SignUpResponse withMessage(String message) {
        this.message = message;
        return this;
    }

    public SignUpResult getResult() {
        return result;
    }

    public void setResult(SignUpResult result) {
        this.result = result;
    }

    public SignUpResponse withResult(SignUpResult result) {
        this.result = result;
        return this;
    }

}
