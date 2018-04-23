package com.pbms.pbmsandroid.model;

import com.google.gson.annotations.SerializedName;

public class BudgetYear {

    @SerializedName("bgyId")
    private String bgyId;

    @SerializedName("bgyTitle")
    private String bgyTitle;

    @SerializedName("bgyActive")
    private String bgyActive;

    public String getBgyId() {
        return bgyId;
    }

    public void setBgyId(String bgyId) {
        this.bgyId = bgyId;
    }

    public String getBgyTitle() {
        return bgyTitle;
    }

    public void setBgyTitle(String bgyTitle) {
        this.bgyTitle = bgyTitle;
    }

    public String getBgyActive() {
        return bgyActive;
    }

    public void setBgyActive(String bgyActive) {
        this.bgyActive = bgyActive;
    }
}
