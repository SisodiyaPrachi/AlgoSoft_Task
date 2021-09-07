package com.cashmyproperty.app.View.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SellerData {

    @SerializedName("users_token")
    @Expose
    private String usersToken;
    @SerializedName("users_id")
    @Expose
    private String usersId;
    @SerializedName("users_name")
    @Expose
    private String usersName;
    @SerializedName("users_sequence_id")
    @Expose
    private String usersSequenceId;
    @SerializedName("users_email")
    @Expose
    private String usersEmail;
    @SerializedName("users_mobile")
    @Expose
    private String usersMobile;
    @SerializedName("email_verification_code")
    @Expose
    private Integer emailVerificationCode;
    @SerializedName("mobile_verification_code")
    @Expose
    private String mobileVerificationCode;
    @SerializedName("users_type")
    @Expose
    private String usersType;
    @SerializedName("users_image")
    @Expose
    private String usersImage;
    @SerializedName("rera_id_number")
    @Expose
    private String reraIdNumber;
    @SerializedName("emirates_id_password")
    @Expose
    private String emiratesIdPassword;
    @SerializedName("verification_code")
    @Expose
    private Integer verificationCode;
    @SerializedName("users_mobile_verified")
    @Expose
    private String usersMobileVerified;
    @SerializedName("account_verified")
    @Expose
    private String accountVerified;
    @SerializedName("users_email_verified")
    @Expose
    private String usersEmailVerified;

    public String getUsersToken() {
        return usersToken;
    }

    public void setUsersToken(String usersToken) {
        this.usersToken = usersToken;
    }

    public SellerData withUsersToken(String usersToken) {
        this.usersToken = usersToken;
        return this;
    }

    public String getUsersId() {
        return usersId;
    }

    public void setUsersId(String usersId) {
        this.usersId = usersId;
    }

    public SellerData withUsersId(String usersId) {
        this.usersId = usersId;
        return this;
    }

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }

    public SellerData withUsersName(String usersName) {
        this.usersName = usersName;
        return this;
    }

    public String getUsersEmail() {
        return usersEmail;
    }

    public void setUsersEmail(String usersEmail) {
        this.usersEmail = usersEmail;
    }

    public SellerData withUsersEmail(String usersEmail) {
        this.usersEmail = usersEmail;
        return this;
    }

    public String getUsersMobile() {
        return usersMobile;
    }

    public void setUsersMobile(String usersMobile) {
        this.usersMobile = usersMobile;
    }

    public SellerData withUsersMobile(String usersMobile) {
        this.usersMobile = usersMobile;
        return this;
    }

    public Integer getEmailVerificationCode() {
        return emailVerificationCode;
    }

    public void setEmailVerificationCode(Integer emailVerificationCode) {
        this.emailVerificationCode = emailVerificationCode;
    }

    public SellerData withEmailVerificationCode(Integer emailVerificationCode) {
        this.emailVerificationCode = emailVerificationCode;
        return this;
    }

    public String getMobileVerificationCode() {
        return mobileVerificationCode;
    }

    public void setMobileVerificationCode(String mobileVerificationCode) {
        this.mobileVerificationCode = mobileVerificationCode;
    }

    public SellerData withMobileVerificationCode(String mobileVerificationCode) {
        this.mobileVerificationCode = mobileVerificationCode;
        return this;
    }

    public String getUsersType() {
        return usersType;
    }

    public void setUsersType(String usersType) {
        this.usersType = usersType;
    }

    public SellerData withUsersType(String usersType) {
        this.usersType = usersType;
        return this;
    }

    public String getUsersImage() {
        return usersImage;
    }

    public void setUsersImage(String usersImage) {
        this.usersImage = usersImage;
    }

    public SellerData withUsersImage(String usersImage) {
        this.usersImage = usersImage;
        return this;
    }

    public String getReraIdNumber() {
        return reraIdNumber;
    }

    public void setReraIdNumber(String reraIdNumber) {
        this.reraIdNumber = reraIdNumber;
    }

    public SellerData withReraIdNumber(String reraIdNumber) {
        this.reraIdNumber = reraIdNumber;
        return this;
    }

    public String getEmiratesIdPassword() {
        return emiratesIdPassword;
    }

    public void setEmiratesIdPassword(String emiratesIdPassword) {
        this.emiratesIdPassword = emiratesIdPassword;
    }

    public SellerData withEmiratesIdPassword(String emiratesIdPassword) {
        this.emiratesIdPassword = emiratesIdPassword;
        return this;
    }

    public Integer getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(Integer verificationCode) {
        this.verificationCode = verificationCode;
    }

    public SellerData withVerificationCode(Integer verificationCode) {
        this.verificationCode = verificationCode;
        return this;
    }

    public String getUsersEmailVerified() {
        return usersEmailVerified;
    }

    public void setUsersEmailVerified(String usersEmailVerified) {
        this.usersEmailVerified = usersEmailVerified;
    }

    public SellerData withUsersEmailVerified(String usersEmailVerified) {
        this.usersEmailVerified = usersEmailVerified;
        return this;
    }

    public String getAccountVerified() {
        return accountVerified;
    }

    public void setAccountVerified(String accountVerified) {
        this.accountVerified = accountVerified;
    }

    public SellerData withAccountVerified(String accountVerified) {
        this.accountVerified = accountVerified;
        return this;
    }

    public String getUsersMobileVerified() {
        return usersMobileVerified;
    }

    public void setUsersMobileVerified(String usersMobileVerified) {
        this.usersMobileVerified = usersMobileVerified;
    }

    public SellerData withUsersMobileVerified(String usersMobileVerified) {
        this.usersMobileVerified = usersMobileVerified;
        return this;
    }
    public String getUsersSequenceId() {
        return usersSequenceId;
    }

    public void setUsersSequenceId(String usersSequenceId) {
        this.usersSequenceId = usersSequenceId;
    }

    public SellerData withUsersSequenceId(String usersSequenceId) {
        this.usersSequenceId = usersSequenceId;
        return this;
    }

}
