package com.pbms.pbmsandroid.model;

import com.google.gson.annotations.SerializedName;

public class StatusDao {

    @SerializedName("stId")
    private String stId;

    @SerializedName("stCode")
    private String stCode;

    @SerializedName("stName")
    private String stName;

    @SerializedName("stColor")
    private String stColor;

    @SerializedName("stActive")
    private String stActive;

    public String getStId() {
        return stId;
    }

    public void setStId(String stId) {
        this.stId = stId;
    }

    public String getStCode() {
        return stCode;
    }

    public void setStCode(String stCode) {
        this.stCode = stCode;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getStColor() {
        return stColor;
    }

    public void setStColor(String stColor) {
        this.stColor = stColor;
    }

    public String getStActive() {
        return stActive;
    }

    public void setStActive(String stActive) {
        this.stActive = stActive;
    }
}
