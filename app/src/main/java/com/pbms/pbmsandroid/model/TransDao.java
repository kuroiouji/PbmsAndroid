package com.pbms.pbmsandroid.model;

import com.google.gson.annotations.SerializedName;

public class TransDao {
    @SerializedName("status")
    private String status;

    @SerializedName("str")
    private String str;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
