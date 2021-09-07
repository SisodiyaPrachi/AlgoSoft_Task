package com.cashmyproperty.app.View.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Id {

    @SerializedName("$id")
    @Expose
    private String $id;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public Id with$id(String $id) {
        this.$id = $id;
        return this;

    }
}
