package com.pbms.pbmsandroid.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProjectDao {

    @SerializedName("pjId")
    private String pjId;

    @SerializedName("pjCode")
    private String pjCode;

    @SerializedName("pjName")
    private String pjName;

    @SerializedName("pjBgyId")
    private String pjBgyId;

    @SerializedName("pjStId")
    private String pjStId;

    @SerializedName("pjOrigin")
    private String pjOrigin;

    @SerializedName("type")
    private String type;

    @SerializedName("pjSpend")
    private String pjSpend;

    public ProjectDao(String pjId, String pjCode, String pjName, String pjBgyId, String pjStId, String pjOrigin, String type, String pjSpend) {
        this.pjId = pjId;
        this.pjCode = pjCode;
        this.pjName = pjName;
        this.pjBgyId = pjBgyId;
        this.pjStId = pjStId;
        this.pjOrigin = pjOrigin;
        this.type = type;
        this.pjSpend = pjSpend;
    }

    public String getPjId() {
        return pjId;
    }

    public void setPjId(String pjId) {
        this.pjId = pjId;
    }

    public String getPjCode() {
        return pjCode;
    }

    public void setPjCode(String pjCode) {
        this.pjCode = pjCode;
    }

    public String getPjName() {
        return pjName;
    }

    public void setPjName(String pjName) {
        this.pjName = pjName;
    }

    public String getPjBgyId() {
        return pjBgyId;
    }

    public void setPjBgyId(String pjBgyId) {
        this.pjBgyId = pjBgyId;
    }

    public String getPjStId() {
        return pjStId;
    }

    public void setPjStId(String pjStId) {
        this.pjStId = pjStId;
    }

    public String getPjOrigin() {
        return pjOrigin;
    }

    public void setPjOrigin(String pjOrigin) {
        this.pjOrigin = pjOrigin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPjSpend() {
        return pjSpend;
    }

    public void setPjSpend(String pjSpend) {
        this.pjSpend = pjSpend;
    }
}
