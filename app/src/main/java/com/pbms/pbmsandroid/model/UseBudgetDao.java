package com.pbms.pbmsandroid.model;

import com.google.gson.annotations.SerializedName;

public class UseBudgetDao {
    @SerializedName("id")
    private String usRsId;

    @SerializedName("rs_level")
    private int usRsLevel;

    @SerializedName("name")
    private String usRsName;

    @SerializedName("spend_budget")
    private float usRsSpend;

    public String getUsRsId() {
        return usRsId;
    }

    public void setUsRsId(String usRsId) {
        this.usRsId = usRsId;
    }

    public int getUsRsLevel() {
        return usRsLevel;
    }

    public void setUsRsLevel(int usRsLevel) {
        this.usRsLevel = usRsLevel;
    }

    public String getUsRsName() {
        return usRsName;
    }

    public void setUsRsName(String usRsName) {
        this.usRsName = usRsName;
    }

    public float getUsRsSpend() {
        return usRsSpend;
    }

    public void setUsRsSpend(float usRsSpend) {
        this.usRsSpend = usRsSpend;
    }
}
